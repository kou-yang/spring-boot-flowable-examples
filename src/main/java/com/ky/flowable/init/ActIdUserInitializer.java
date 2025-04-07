package com.ky.flowable.init;

import com.ky.flowable.constant.ProcessConstant;
import com.ky.flowable.model.entity.SysUser;
import com.ky.flowable.service.SysUserService;
import com.ky.flowable.util.CollStreamUtils;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-07 14:38
 */
@Component
@RequiredArgsConstructor
public class ActIdUserInitializer implements CommandLineRunner {

    private final IdentityService identityService;
    private final SysUserService sysUserService;

    @Override
    public void run(String... args) {
        // 查询系统用户
        List<SysUser> sysUserList = sysUserService.list();
        // 查询 flowable 用户
        List<User> userList = identityService.createUserQuery().list();
        List<String> userIdList = CollStreamUtils.toList(userList, User::getId);
        // 查询默认组
        Group defaultGroup = identityService.createGroupQuery().groupId(ProcessConstant.DEFAULT_GROUP).singleResult();
        // 查询默认组的用户
        List<User> list = identityService.createUserQuery().memberOfGroup(defaultGroup.getId()).list();
        List<String> defaultGroupUserIdList = CollStreamUtils.toList(list, User::getId);

        // 更新 flowable 用户
        sysUserList.forEach(sysUser -> {
            String userId = sysUser.getId().toString();
            // 不存在则新增用户
            if (!userIdList.contains(userId)) {
                // 新增用户
                User user = identityService.newUser(userId);
                user.setFirstName(sysUser.getUsername());
//                user.setLastName(sysUser.getUsername());
                user.setEmail(sysUser.getEmail());
                user.setPassword(sysUser.getPassword());
                identityService.saveUser(user);
            }
            // 不存在则分配默认组
            if (!defaultGroupUserIdList.contains(userId)) {
                // 分配默认组
                identityService.createMembership(userId, defaultGroup.getId());
            }
        });
    }
}
