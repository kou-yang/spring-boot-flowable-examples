package com.ky.flowable.util;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.*;
import java.util.function.Function;

/**
 * @author ky
 * @description
 * @date 2024-05-04 23:48
 */
public class TreeUtils {

    private static final String DEFAULT_ID_KEY = "id";
    private static final String DEFAULT_PARENT_ID_KEY = "parentId";
    private static final String DEFAULT_WEIGHT_KEY = "orderNum";
    private static final String DEFAULT_NAME_KEY = "name";
    private static final String DEFAULT_CHILDREN_KEY = "children";
    private static final Integer DEFAULT_DEEP = null;
    private static final String DEFAULT_ROOT_ID = "0";

    /**
     * 默认配置
     */
    private static final TreeNodeConfig DEFAULT_CONFIG = createConfig(DEFAULT_ID_KEY, DEFAULT_PARENT_ID_KEY, DEFAULT_WEIGHT_KEY, DEFAULT_NAME_KEY, DEFAULT_CHILDREN_KEY, DEFAULT_DEEP);

    public static <T> List<Tree<String>> translate(List<T> list) {
        return translate(list, DEFAULT_ROOT_ID);
    }

    public static <T, E> List<Tree<E>> translate(List<T> list, E rootId) {
        return translate(list, rootId, DEFAULT_CONFIG);
    }

    public static <T, E> List<Tree<E>> translate(List<T> list, E rootId, TreeNodeConfig config) {
        if (CollectionUtils.isNotEmpty(list)) {
            return TreeUtil.build(list, rootId, config, (object, tree) -> {
                Map<String, T> map = BeanMap.create(object);
                for (Map.Entry<String, T> entry : map.entrySet()) {
                    tree.putExtra(entry.getKey(), entry.getValue());
                }
            });
        }
        return Collections.emptyList();
    }

    public static TreeNodeConfig createConfig(String idKey, String parentIdKey, String weighKey, String nameKey, String childrenKey, Integer deep) {
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey(StringUtils.isBlank(idKey) ? DEFAULT_ID_KEY : idKey)
                .setParentIdKey(StringUtils.isBlank(parentIdKey) ? DEFAULT_PARENT_ID_KEY : parentIdKey)
                .setWeightKey(StringUtils.isBlank(weighKey) ? DEFAULT_WEIGHT_KEY : weighKey)
                .setNameKey(StringUtils.isBlank(nameKey) ? DEFAULT_NAME_KEY : nameKey)
                .setChildrenKey(StringUtils.isBlank(childrenKey) ? DEFAULT_CHILDREN_KEY : childrenKey)
                .setDeep(Objects.isNull(deep) ? DEFAULT_DEEP : deep);
        return config;
    }

    /**
     * 将列表转换为树形结构（默认子节点名称为 "children"）
     *
     * @param list           原始列表
     * @param idGetter       获取节点ID的函数
     * @param parentIdGetter 获取父节点ID的函数
     * @param <T>            节点类型
     * @param <K>            ID类型
     * @return 树形结构的根节点列表
     */
    public static <T, K> List<T> translate(List<T> list, Function<T, K> idGetter, Function<T, K> parentIdGetter) {
        return translate(list, idGetter, parentIdGetter, new DefaultChildrenSetter<>());
    }

    /**
     * 将列表转换为树形结构
     *
     * @param list           原始列表
     * @param idGetter       获取节点ID的函数
     * @param parentIdGetter 获取父节点ID的函数
     * @param childrenSetter 设置子节点的函数
     * @param <T>            节点类型
     * @param <K>            ID类型
     * @return 树形结构的根节点列表
     */
    public static <T, K> List<T> translate(List<T> list, Function<T, K> idGetter, Function<T, K> parentIdGetter, ChildrenSetter<T> childrenSetter) {
        // 创建一个 Map，用于存储每个节点的引用
        Map<K, T> nodeMap = CollStreamUtils.toMap(list, idGetter, Function.identity());
        // 存储根节点
        List<T> rootNodes = new ArrayList<>();
        // 构建树形结构
        for (T node : list) {
            K parentId = parentIdGetter.apply(node);
            if (Objects.isNull(parentId) || !nodeMap.containsKey(parentId)) {
                // 如果 parentId 为 null 或者 parentId 不在 Map 中，说明是根节点
                rootNodes.add(node);
            } else {
                // 否则，找到其父节点，并将其添加到父节点的 children 列表中
                T parentNode = nodeMap.get(parentId);
                if (Objects.nonNull(parentNode)) {
                    childrenSetter.setChildren(parentNode, node);
                }
            }
        }
        return rootNodes;
    }

    /**
     * 设置子节点的函数式接口
     */
    @FunctionalInterface
    public interface ChildrenSetter<T> {
        void setChildren(T parent, T child);
    }

    /**
     * 默认的 ChildrenSetter 实现
     */
    private static class DefaultChildrenSetter<T> implements ChildrenSetter<T> {
        @Override
        public void setChildren(T parent, T child) {
            try {
                // 通过反射获取父节点的 children 列表
                List<T> children = (List<T>) parent.getClass()
                        .getMethod("getChildren")
                        .invoke(parent);
                if (Objects.isNull(children)) {
                    children = new ArrayList<>();
                    parent.getClass()
                            .getMethod("setChildren", List.class)
                            .invoke(parent, children);
                }
                children.add(child);
            } catch (Exception e) {
                throw new RuntimeException("Failed to set children for parent node", e);
            }
        }
    }
}

