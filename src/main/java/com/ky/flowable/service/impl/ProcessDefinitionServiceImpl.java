package com.ky.flowable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ky.flowable.mapper.ProcessDefinitionMapper;
import com.ky.flowable.model.entity.ProcessDefinition;
import com.ky.flowable.service.ProcessDefinitionService;
import org.springframework.stereotype.Service;

/**
* @author kouyang
* @description 针对表【WF_PROCESS_DEFINITION(流程定义扩展表)】的数据库操作Service实现
* @createDate 2025-03-31 15:05:18
*/
@Service
public class ProcessDefinitionServiceImpl extends ServiceImpl<ProcessDefinitionMapper, ProcessDefinition>
    implements ProcessDefinitionService{

}




