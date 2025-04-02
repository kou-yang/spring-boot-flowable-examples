package com.ky.flowable.service.act;

import com.ky.flowable.model.dto.ActIdUserCreateDTO;
import com.ky.flowable.model.dto.ActIdUserUpdateDTO;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-02 14:57
 */
public interface ActIdUserService {

    /**
     * 新增
     * @param dto
     */
    void create(ActIdUserCreateDTO dto);

    /**
     * 修改
     * @param dto
     */
    void update(ActIdUserUpdateDTO dto);
}
