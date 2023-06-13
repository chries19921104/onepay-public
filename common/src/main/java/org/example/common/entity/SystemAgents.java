package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* system_agents 实体类
* </p>
*
* @author Administrator
* @since 2023-05-16 18:57:07
*/
@Getter
@Setter
@TableName("system_agents")
@ApiModel(description = "代理实体类")
public class SystemAgents implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Integer IDENTITY_GEN_AGENT = 1; //总代
    private static final Integer IDENTITY_AGENT = 2; //代理

    @TableId
    private Long agentId;

    /**
    * 1 总代, 2 代理
    */
    private Integer identity;

    /**
    * 所属总代
    */
    private Long belongId;

    /**
    * 登录账号
    */
    private String username;

    /**
    * 登录密码 hash_password加密
    */
    private String password;

    /**
    * 全称
    */
    private String fullName;

    /**
    * 商户总数
    */
    private Long merchantCount;

    /**
    * 状态 0-停用 1-启用
    */
    private Integer status;

    /**
    * 是否为测试代理 0-不是 1-是
    */
    private Integer isTester;

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
