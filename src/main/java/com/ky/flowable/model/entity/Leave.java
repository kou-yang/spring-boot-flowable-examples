package com.ky.flowable.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 请假申请表
 * @TableName BIZ_LEAVE
 */
@TableName(value ="BIZ_LEAVE")
@Data
public class Leave implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 申请人ID
     */
    private String userId;

    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}