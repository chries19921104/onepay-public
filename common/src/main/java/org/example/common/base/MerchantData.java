package org.example.common.base;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MerchantData {
    private Long merchantId;
    private Long cardGroupId;
    private Long agentDisplayId;
    private String agentFullName;
    private BigDecimal availableBalance;
    private String code;
    private String createdAt;
    private String currency;
    private BigDecimal currentBalance;
    private BigDecimal depositOutstandingBalance;
    private BigDecimal frozenBalance;
    private BigDecimal holdBalance;
    private String name;
    private Integer noQrAdj;
    private boolean noQrAdjRandom;
    private Integer settFee;
    private Integer status;
    private BigDecimal todayTrFee;
    private Long topAgentId;
    private String topAgentName;
    private Integer trQrRate;
    private Integer trRrate;
    private String trTrueRate;
    private boolean useQrPairingCode;
    private Integer wdRate;
    private String wdRateAmount;
    private String website;
}
