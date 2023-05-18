package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* <p>
* system_admin 实体类
* </p>
*
* @author zhangmi
* @since 2023-05-16 13:12:54
*/
@Getter
@Setter
@TableName("system_admin")
@ApiModel(value = "管理员对象")
public class Admins implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 账户名
    */
    private String username;

    /**
    * 角色ID
    */
    @JsonProperty("role_id")
    private Integer roleId;

    /**
    * 登录密码
    */
    private String password;

    /**
    * 全称
    */
    @JsonProperty("full_name")
    private String fullName;

    /**
    * 状态
    */
    private Integer status;

    /**
    * 超级管理员, 此用户不允许被修改及移除
    */
    private Integer superAdmin;

    /**
    * 隐藏管理员
    */
    private Integer hidden;

    /**
    * 是否为测试账号
    */
    private Integer isTester;

    /**
    * 币种
    */
    private String currency;

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

    /**
    * 盐
    */
    private String param;


}
