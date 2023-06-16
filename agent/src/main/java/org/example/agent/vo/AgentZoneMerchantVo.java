package org.example.agent.vo;

import lombok.Data;

@Data
public class AgentZoneMerchantVo {
    private Long merchantId;
    private Long agentId;
    private String name;
    private String code;
    private String currency;
}
