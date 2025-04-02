package com.ky.flowable.service.act.impl;

import com.ky.flowable.exception.BusinessException;
import com.ky.flowable.exception.CommonErrorEnum;
import com.ky.flowable.model.dto.ActIdGroupCreateDTO;
import com.ky.flowable.model.dto.ActIdGroupUpdateDTO;
import com.ky.flowable.service.act.ActIdGroupService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-02 14:47
 */
@Service
@RequiredArgsConstructor
public class ActIdGroupServiceImpl implements ActIdGroupService {

    private final IdentityService identityService;

    @Override
    public void create(ActIdGroupCreateDTO dto) {
        Group group = identityService.newGroup(dto.getGroupId());
        group.setName(dto.getName());
        group.setType(dto.getType());
        identityService.saveGroup(group);
    }

    @Override
    public void update(ActIdGroupUpdateDTO dto) {
        Group group = identityService.createGroupQuery().groupId(dto.getGroupId()).singleResult();
        if (Objects.isNull(group)) {
            throw new BusinessException(CommonErrorEnum.PARAMS_ERROR, "组不存在");
        }
        group.setName(dto.getName());
        group.setType(dto.getType());
        identityService.saveGroup(group);
    }
}
