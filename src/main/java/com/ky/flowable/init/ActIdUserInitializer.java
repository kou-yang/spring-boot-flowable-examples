package com.ky.flowable.init;

import com.ky.flowable.model.entity.SysUser;
import com.ky.flowable.service.SysUserService;
import com.ky.flowable.util.CollStreamUtils;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-07 14:38
 */
@Order(1)
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
        });
    }
}
