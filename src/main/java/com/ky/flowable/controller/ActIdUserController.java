package com.ky.flowable.controller;

import com.ky.flowable.common.Result;
import com.ky.flowable.common.ResultUtils;
import com.ky.flowable.model.dto.ActIdUserCreateDTO;
import com.ky.flowable.model.dto.ActIdUserUpdateDTO;
import com.ky.flowable.model.request.PrimaryKeyRequest;
import com.ky.flowable.service.act.ActIdUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-01 17:04
 */
@Api(tags = "ACT-用户")
@RestController
@RequestMapping("/actId/user")
@RequiredArgsConstructor
public class ActIdUserController {

    private final IdentityService identityService;
    private final ActIdUserService actIdUserService;

    @ApiOperation("新增")
    @PostMapping("/create")
    public Result<?> create(@Validated @RequestBody ActIdUserCreateDTO dto) {
        actIdUserService.create(dto);
        return ResultUtils.success();
    }

    @ApiOperation("修改")
    @PostMapping("/update")
    public Result<?> update(@Validated @RequestBody ActIdUserUpdateDTO dto) {
        actIdUserService.update(dto);
        return ResultUtils.success();
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public Result<?> delete(@Validated @RequestBody PrimaryKeyRequest request) {
        identityService.deleteUser(request.getId());
        return ResultUtils.success();
    }

    @ApiOperation("查询全部")
    @GetMapping("/all")
    public Result<?> all() {
        List<User> list = identityService.createUserQuery().list();
        return ResultUtils.success(list);
    }
}
