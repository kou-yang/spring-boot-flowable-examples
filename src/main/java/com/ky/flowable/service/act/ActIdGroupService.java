package com.ky.flowable.service.act;

import com.ky.flowable.model.dto.ActIdGroupCreateDTO;
import com.ky.flowable.model.dto.ActIdGroupUpdateDTO;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-02 14:47
 */
public interface ActIdGroupService {

    /**
     * 新增
     * @param dto
     */
    void create(ActIdGroupCreateDTO dto);

    /**
     * 修改
     * @param dto
     */
    void update(ActIdGroupUpdateDTO dto);
}
