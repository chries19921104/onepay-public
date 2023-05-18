package org.example.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.example.common.entity.Admins;

import java.util.List;

@Data
@ApiModel(value = "接受前端管理员数据")
public class AdminsDTO{
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
    private String token;
    private List<String> currency;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 超级管理员, 此用户不允许被修改及移除
     */
    private Integer superAdmin;

}
