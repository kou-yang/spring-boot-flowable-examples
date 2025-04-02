package com.ky.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ky.flowable.mapper.BusinessProcessMapper;
import com.ky.flowable.model.entity.BusinessProcess;
import com.ky.flowable.service.BusinessProcessService;
import org.springframework.stereotype.Service;

/**
* @author kouyang
* @description 针对表【WF_BUSINESS_PROCESS(业务流程关联表)】的数据库操作Service实现
* @createDate 2025-03-31 14:39:17
*/
@Service
public class BusinessProcessServiceImpl extends ServiceImpl<BusinessProcessMapper, BusinessProcess>
    implements BusinessProcessService{

}




