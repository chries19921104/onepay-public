package org.example.agent.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryReportDataPo {

    @JsonProperty("SH100_ID")
    private Long merchantId;

    @JsonProperty("SH100_code")
    private String code;

    @JsonProperty("SH100_name")
    private String name;

    @JsonProperty("agent_id")
    private Long agentId;

    @JsonProperty("agent_name")
    private String agentName;

    @JsonProperty("SH100_agent_id")
    private Long sh100AgentId;

    @JsonProperty("SH100_agent_name")
    private String sh100AgentName;

    @JsonProperty("SH100_top_agent_id")
    private Long topAgentId;

    @JsonProperty("SH100_top_agent_name")
    private String topAgentName;

    private String currency;

    @JsonProperty("monthly_adjustment")
    private BigDecimal adjustment;

    @JsonProperty("FI_BANK_CARD_balance")
    private BigDecimal fiVnpayBankCardBalance;

    @JsonProperty("FI_MOMO_QR_balance")
    private BigDecimal fiVnpayMomoQrBalance;

    @JsonProperty("FI_RCGCARD_PC_balance")
    private BigDecimal fiVnpayRcgcardPcBalance;

    @JsonProperty("FI_RCGCARD_ZING_balance")
    private BigDecimal fiVnpayRcgcardZingBalance;

    @JsonProperty("FI_VIETTEL_FIX_balance")
    private BigDecimal fiVnpayViettelFixBalance;

    @JsonProperty("FI_VIETTEL_QR_balance")
    private BigDecimal fiVnpayViettelQrBalance;

    @JsonProperty("FI_ZALO_QR_balance")
    private BigDecimal fiVnpayZaloQrBalance;

    @JsonProperty("FI_bank_balance")
    private BigDecimal fiBankBalance;

    @JsonProperty("FI_qrpay_balance")
    private BigDecimal fiQrpayBalance;

    @JsonProperty("FI_truewallet_balance")
    private BigDecimal fiTruewalletBalance;

    @JsonProperty("FO_balance")
    private BigDecimal foBalance;

    @JsonProperty("loss_amount")
    private BigDecimal lossAmount;

    @JsonProperty("markup")
    private BigDecimal totalMarkup;

    @JsonProperty("rebate")
    private BigDecimal totalRebate;

    private BigDecimal total;

    @JsonProperty("total_fee")
    private BigDecimal totalFee;


}
