package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel(description = "返回商户卡群数据")
public class MerchantByAgentByGroupVo {

    /**
     * 商户ID
     */
    @TableId
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
    private Long cardGroupId;

    /**
     * 代理id
     */
    @JsonProperty("agent_id")
    private Long agentId;
}
