package com.ky.flowable.service.act.impl;

import com.ky.flowable.exception.BusinessException;
import com.ky.flowable.exception.CommonErrorEnum;
import com.ky.flowable.model.dto.ActIdUserCreateDTO;
import com.ky.flowable.model.dto.ActIdUserUpdateDTO;
import com.ky.flowable.service.act.ActIdUserService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-02 14:57
 */
@Service
@RequiredArgsConstructor
public class ActIdUserServiceImpl implements ActIdUserService {

    private final IdentityService identityService;

    @Override
    public void create(ActIdUserCreateDTO dto) {
        User user = identityService.newUser(dto.getUserId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        identityService.saveUser(user);
    }

    @Override
    public void update(ActIdUserUpdateDTO dto) {
        User user = identityService.createUserQuery().userId(dto.getUserId()).singleResult();
        if (Objects.isNull(user)) {
            throw new BusinessException(CommonErrorEnum.PARAMS_ERROR, "用户不存在");
        }
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        identityService.saveUser(user);
    }
}
