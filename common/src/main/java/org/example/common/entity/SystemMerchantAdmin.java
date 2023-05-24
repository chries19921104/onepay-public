package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* system_merchant_admin 实体类
* </p>
*
* @author Administrator
* @since 2023-05-22 11:19:34
*/
@Getter
@Setter
@TableName("system_merchant_admin")
public class SystemMerchantAdmin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @TableId
    private Long id;

    /**
    * 商户ID
    */
    private Long merchantId;

    /**
    * 商户后台登录账号
    */
    private String username;

    /**
    * 角色ID
    */
    private Integer roleId;

    /**
    * 登录密码
    */
    private String password;

    /**
    * 全称
    */
    private String fullName;

    /**
    * 状态  0-停用 1-启用
    */
    private Integer status;

    /**
    * 超级管理员, 此用户不允许被修改及移除
    */
    private Integer superAdmin;

    /**
    * 登录token
    */
    private String rememberToken;

    private String totpSecret;

    /**
    * 创建人员
    */
    private String creator;

    /**
    * 更新人员
    */
    private String updater;

    /**
    * 创建时间
    */
    private LocalDateTime createdAt;

    /**
    * 更新时间
    */
    private LocalDateTime updatedAt;


}
