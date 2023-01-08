package com.yiran.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user table
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * name of the user
     */
    private String username;

    /**
     * user account

     */
    private String userAccount;

    /**
     * user avatar
     */
    private String avatarUrl;

    /**
     * user gender

     */
    private Integer gender;

    /**
     * user passward

     */
    private String userPassward;

    /**
     * phone
     */
    private String phone;

    /**
     * user email
     */
    private String email;

    /**
     * user status (0 means account is activated)
     */
    private Integer userStatus;

    /**
     * create time

     */
    private Date createTime;

    /**
     * update time
     */
    private Date updateTime;

    /**
     * whether the user has been delete
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}