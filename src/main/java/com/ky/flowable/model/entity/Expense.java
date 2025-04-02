package com.ky.flowable.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 报销申请表
 * @TableName BIZ_EXPENSE
 */
@TableName(value ="BIZ_EXPENSE")
@Data
public class Expense implements Serializable {
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
     * 报销金额
     */
    private BigDecimal amount;

    /**
     * 报销类型
     */
    private String expenseType;

    /**
     * 报销说明
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}