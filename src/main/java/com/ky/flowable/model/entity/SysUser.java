package com.ky.flowable.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户表
 * @TableName SYS_USER
 */
@TableName(value ="SYS_USER")
@Data
public class SysUser implements Serializable {
    /**
     * 用户ID，主键自增长
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 用户状态：0-禁用，1-启用
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}