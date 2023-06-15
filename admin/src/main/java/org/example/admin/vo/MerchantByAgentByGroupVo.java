package org.example.admin.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "返回商户卡群数据")
public class MerchantByAgentByGroupVo {

    /**
     * 商户ID
     */
    @TableId
    @JsonProperty("SH100_ID")
    private Long merchantId;

    /**
     * 商户名称
     */
    private String name;

    /**
     * 商户代码
     */
    private String code;

    /**
     * 币别 currency
     */
    private String currency;

    /**
     * 卡群
     */
    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    /**
     * 代理id
     */
    @JsonProperty("agent_id")
    private Long agentId;
}
