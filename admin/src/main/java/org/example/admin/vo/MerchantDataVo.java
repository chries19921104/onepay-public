package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "用于常用返回数据中的data数据")
public class MerchantDataVo {

    @JsonProperty("PG100_name")
    private String groupName;

    @JsonProperty("SH100_ID")
    private Long merchantId;

    @JsonProperty("agent_display_id")
    private Long agentDisplayId;

    @JsonProperty("agent_full_name")
    private String agentFullName;

    @JsonProperty("available_balance")
    private BigDecimal availableBalance;

    private String code;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private String currency;

    @JsonProperty("current_balance")
    private BigDecimal currentBalance;

    @JsonProperty("deposit_outstanding_balance")
    private BigDecimal depositOutstandingBalance;

    @JsonProperty("frozen_balance")
    private BigDecimal frozenBalance;

    @JsonProperty("hold_balance")
    private BigDecimal holdBalance;

    private String name;

    @JsonProperty("no_qr_adj")
    private Integer noQrAdj;

    @JsonProperty("no_qr_adj_random")
    private boolean noQrAdjRandom;

    @JsonProperty("sett_fee")
    private BigDecimal settFee;

    private Integer status;

    @JsonProperty("today_tr_fee")
    private BigDecimal todayTrFee;

    @JsonProperty("top_agent_id")
    private Long topAgentId;

    @JsonProperty("top_agent_name")
    private String topAgentName;

    @JsonProperty("tr_qr_rate")
    private BigDecimal trQrRate;

    @JsonProperty("tr_rate")
    private BigDecimal trRate;

    @JsonProperty("tr_true_rate")
    private BigDecimal trTrueRate;

    @JsonProperty("use_qr_pairing_code")
    private boolean useQrPairingCode;

    @JsonProperty("wd_rate")
    private BigDecimal wdRate;

    @JsonProperty("wd_rate_amount")
    private BigDecimal wdRateAmount;

    private String website;}
