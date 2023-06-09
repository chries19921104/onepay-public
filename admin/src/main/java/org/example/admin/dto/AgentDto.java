package org.example.admin.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "接受前端代理列表数据")
public class AgentDto {

    @JsonProperty("agent_ID")
    private Long agentId;

    /**
     * 代理ID
     */
    @JsonProperty("display_id")
    private String displayId;
    /**
     * 1 总代, 2 代理
     */
    private Integer identity;

    /**
     * 所属总代
     */
    @JsonProperty("belong_id")
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
    @JsonProperty("full_name")
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
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页显示条数
     */
    private Integer rp;


}
