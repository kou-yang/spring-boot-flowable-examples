package com.ky.flowable.init;

import com.ky.flowable.constant.ProcessConstant;
import com.ky.flowable.util.CollStreamUtils;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-10 10:59
 */
@Order(2)
@Component
@RequiredArgsConstructor
public class ActIdUserDefaultGroupInitializer implements CommandLineRunner {

    private final IdentityService identityService;

    @Override
    public void run(String... args) {
        // 查询 flowable 用户
        List<User> userList = identityService.createUserQuery().list();
        List<String> userIdList = CollStreamUtils.toList(userList, User::getId);
        // 查询默认组
        Group defaultGroup = identityService.createGroupQuery().groupId(ProcessConstant.DEFAULT_GROUP).singleResult();
        if (Objects.isNull(defaultGroup)) {
            // 创建默认组
            defaultGroup = identityService.newGroup(ProcessConstant.DEFAULT_GROUP);
            defaultGroup.setName("默认组");
            identityService.saveGroup(defaultGroup);
        }
        // 查询默认组的用户
        List<User> list = identityService.createUserQuery().memberOfGroup(defaultGroup.getId()).list();
        List<String> defaultGroupUserIdList = CollStreamUtils.toList(list, User::getId);
        // 为没有默认组的用户分配默认组
        userIdList.forEach(userId -> {
            if (!defaultGroupUserIdList.contains(userId)) {
                identityService.createMembership(userId, ProcessConstant.DEFAULT_GROUP);
            }
        });
    }
}
