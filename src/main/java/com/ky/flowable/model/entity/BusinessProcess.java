package com.ky.flowable.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务流程关联表
 * @TableName WF_BUSINESS_PROCESS
 */
@TableName(value ="WF_BUSINESS_PROCESS")
@Data
public class BusinessProcess implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 业务唯一标识
     */
    private String businessKey;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 发起人ID
     */
    private String startUserId;

    /**
     * 发起时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 当前任务
     */
    private String currentTask;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}