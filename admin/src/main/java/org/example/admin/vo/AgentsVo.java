package org.example.admin.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "返回代理数据")
public class AgentsVo {
    @TableId
    private Long agentId;

    /**
     * 代理ID
     */
    private String displayId;

    /**
     * 1 总代, 2 代理
     */
    private Integer identity;

    /**
     * 所属总代
     */
    @JsonProperty(value = "belong_id")
    private Long belongId;

    /**
     * 登录账号
     */
    private String username;
    /**
     * 登录密码 hash_password加密
     */
//    private String password;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 商户总数
     */
    @JsonProperty(value = "merchant_count")
    private Long merchantCount;

    /**
     * 状态 0-停用 1-启用
     */
    private Integer status;

    /**
     * 是否为测试代理 0-不是 1-是
     */
    @JsonProperty(value = "is_tester")
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
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;
}
