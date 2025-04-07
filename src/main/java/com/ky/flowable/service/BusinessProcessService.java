package com.ky.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ky.flowable.model.entity.BusinessProcess;

/**
* @author kouyang
* @description 针对表【WF_BUSINESS_PROCESS(业务流程关联表)】的数据库操作Service
* @createDate 2025-03-31 14:39:17
*/
public interface BusinessProcessService extends IService<BusinessProcess> {

    /**
     * 根据流程实例id查询
     * @param processInstanceId
     * @return
     */
    BusinessProcess getByProcessInstanceId(String processInstanceId);
}
