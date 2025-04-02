package com.ky.flowable.controller;

import com.ky.flowable.common.Result;
import com.ky.flowable.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-01 17:05
 */
@Api(tags = "ACT-用户组关联")
@RestController
@RequestMapping("/actId/membership")
@RequiredArgsConstructor
public class ActIdMembershipController {

    private final IdentityService identityService;

    @ApiOperation("添加用户组关联")
    @PostMapping("/relate")
    public Result<?> relate(String userId, String groupId) {
        identityService.createMembership(userId, groupId);
        return ResultUtils.success();
    }

    @ApiOperation("移除用户组关联")
    @PostMapping("/remove")
    public Result<?> remove(String userId, String groupId) {
        identityService.deleteMembership(userId, groupId);
        return ResultUtils.success();
    }

    @ApiOperation("查询组成员")
    @GetMapping("/users")
    public Result<?> users(String groupId) {
        List<User> list = identityService.createUserQuery()
                .memberOfGroup(groupId)
                .list();
        return ResultUtils.success(list);
    }

    @ApiOperation("查询用户组")
    @GetMapping("/groups")
    public Result<?> groups(String userId) {
        List<Group> list = identityService.createGroupQuery()
                .groupMember(userId)
                .list();
        return ResultUtils.success(list);
    }
}
