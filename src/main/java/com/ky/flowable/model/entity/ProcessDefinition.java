package com.ky.flowable.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 流程定义扩展表
 * @TableName WF_PROCESS_DEFINITION
 */
@TableName(value ="WF_PROCESS_DEFINITION")
@Data
public class ProcessDefinition implements Serializable {
    /**
     * 流程定义ID
     */
    @TableId
    private String id;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程KEY
     */
    private String key;

    /**
     * 流程分类
     */
    private String category;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 描述
     */
    private String description;

    /**
     * 关联表单ID
     */
    private String formId;

    /**
     * 是否激活
     */
    private Integer isActive;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}