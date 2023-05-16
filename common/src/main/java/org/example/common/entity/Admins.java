package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* <p>
* admins 实体类
* </p>
*
* @author zhangmi
* @since 2023-05-11 16:38:58
*/
@Getter
@Setter
@TableName("admins")
@ApiModel(value = "管理员对象")
public class Admins implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;
    @JsonProperty("role_id")
    private Integer roleId;

    private String password;
    @JsonProperty("full_name")
    private String fullName;

    private Integer status;

    /**
    * 超级管理员, 此用户不允许被修改及移除
    */
    private Integer superAdmin;

    private String rememberToken;

    private String totpSecret;

    /**
    * 延伸权限
    */
    private Integer penguin;

    /**
    * 隐藏管理员
    */
    private Integer hidden;

    private Integer isTester;

    /**
    * 幣別
    */
    private String currency;

    /**
    * 创建人员
    */
    private String createdMan;

    /**
    * 更新人员
    */
    private String updatedMan;

    private Timestamp createdAt;

    private Timestamp updatedAt;
    /**
     * 盐
     */
    private String param;

}
