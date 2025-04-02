package com.ky.flowable.controller;

import com.ky.flowable.common.Result;
import com.ky.flowable.common.ResultUtils;
import com.ky.flowable.model.dto.ActIdGroupCreateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-01 17:05
 */
@Api(tags = "ACT-组")
@RestController
@RequestMapping("/actId/group")
@RequiredArgsConstructor
public class ActIdGroupController {

    private final IdentityService identityService;

    @ApiOperation("新增")
    @PostMapping("/create")
    public Result<?> create(@Validated @RequestBody ActIdGroupCreateDTO dto) {
        Group group = identityService.newGroup(dto.getGroupId());
        group.setName(dto.getName());
        group.setType(dto.getType());
        identityService.saveGroup(group);
        return ResultUtils.success();
    }

    @ApiOperation("查询全部")
    @GetMapping("/all")
    public Result<?> all() {
        List<Group> list = identityService.createGroupQuery().list();
        return ResultUtils.success(list);
    }
}
