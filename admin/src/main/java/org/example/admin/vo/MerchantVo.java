package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.example.admin.dto.BrankDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "返回商户data数据")
public class MerchantVo {
    @JsonProperty("Dapay_enabled")
    private boolean dapayEnabled;

    @JsonProperty("Dapay_services_enable")
    private List<String> dapayServicesEnable;//0

    @JsonProperty("FI_Tpi_driver")
    private String fiTpiDriver;//0

    @JsonProperty("FI_Tpi_services_enable")
    private List<String> fiTpiServicesEnable;//0

    @JsonProperty("FI_bank_max")
    private BigDecimal fiBankMax;

    @JsonProperty("FI_bank_min")
    private BigDecimal fiBankMin;

    @JsonProperty("FI_qrpay_max")
    private BigDecimal fiQrpayMax;

    @JsonProperty("FI_qrpay_min")
    private BigDecimal fiQrpayMin;

    @JsonProperty("FI_true_max")
    private BigDecimal fiTrueMax;

    @JsonProperty("FI_true_min")
    private BigDecimal fiTrueMin;

    @JsonProperty("FO_Tpi_driver")
    private String foTpiDriver;

    @JsonProperty("FO_Tpi_services_enable")
    private List<String> foTpiServicesEnable;//0

    @JsonProperty("FO_max")
    private BigDecimal foMax;

    @JsonProperty("FO_min")
    private BigDecimal foMin;

    @JsonProperty("FX_Tpi_services_enable")
    private List<Integer> fxTpiServicesEnable;//0

    @JsonProperty("FX_max")
    private BigDecimal fxMax;

    @JsonProperty("FX_min")
    private BigDecimal fxMin;

    @JsonProperty("H1Pay_enabled")
    private boolean h1PayEnabled;

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    @JsonProperty("QijiPay_end_time")
    private String qijiPayEndTime;

    @JsonProperty("QijiPay_start_time")
    private String qijiPayStartTime;

    @JsonProperty("SH100_ID")
    private Long merchantId;

    @JsonProperty("VNPAY_enabled")
    private boolean vnpayEnabled;

    @JsonProperty("VNPAY_services_enable")
    private List<String> vnpayServicesEnable;//0

    @JsonProperty("YouktPay_enabled")
    private boolean youktPayEnabled;

    @JsonProperty("agent_display_id")
    private Long agentDisplayId;

    @JsonProperty("agent_full_name")
    private String agentFullName;

    @JsonProperty("agent_id")
    private Long agentId;

    @JsonProperty("api_cn_doc")
    private String apiCnDoc;//0

    @JsonProperty("api_en_doc")
    private String apiEnDoc;//0

    @JsonProperty("available_balance")
    private BigDecimal availableBalance;

    @JsonProperty("check_accname")
    private boolean checkAccName;

    private String code;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String createdMan;

    private String currency;

    @JsonProperty("current_balance")
    private BigDecimal currentBalance;

    @JsonProperty("deposit_outstanding_balance")
    private BigDecimal depositOutstandingBalance;

    private List<String> domains;//0

    private String name;

    @JsonProperty("name4qr")
    private String nameForQR;

    @JsonProperty("no_qr_adj")
    private Integer noQrAdj;

    @JsonProperty("no_qr_adj_random")
    private boolean noQrAdjRandom;

    @JsonProperty("open_bank")
    private List<BrankDto> openBank;

    @JsonProperty("pay_fo_bank_fee")
    private boolean payFoBankFee;

    @JsonProperty("preset_settlement_term")
    private String presetSettlementTerm;//0

    @JsonProperty("sec_code")
    private String secCode;

    @JsonProperty("sett_card_max")
    private Integer settCardMax;

    @JsonProperty("sett_fee")
    private BigDecimal settFee;

    @JsonProperty("settlement_term")
    private Integer settlementTerm;

    private Integer status;

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

    @JsonProperty("tuna_domains")
    private List<String> tunaDomains;//0

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_man")
    private String updatedMan;

    @JsonProperty("use_qr_pairing_code")
    private boolean useQrPairingCode;

    @JsonProperty("user_id")
    private Long userId;

    private String username;

    @JsonProperty("wd_rate")
    private BigDecimal wdRate;

    @JsonProperty("wd_rate_amount")
    private BigDecimal wdRateAmount;

    private String website;

}
