package org.example.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class BankCardAllVo {
    @JsonProperty("ATM_card_number")
    private String atmCardNumber;

    @JsonProperty("ATM_pin")
    private String atmPin;

    @JsonProperty("BC100_ID")
    private Long cardId;

    @JsonProperty("BK100_ID")
    private Long bankId;

    @JsonProperty("BK100_name")
    private String bankName;

    @JsonProperty("D_I_warning")
    private Boolean DIWarning;

    @JsonProperty("D_O_warning")
    private Boolean DOWarning;

    @JsonProperty("D_Remaining_Limit_I")
    private Integer DRemainingLimitI;

    @JsonProperty("D_Remaining_Limit_O")
    private Integer DRemainingLimitO;

    @JsonProperty("ET_hold_balance")
    private Integer ETHoldBalance;

    @JsonProperty("FI_hold_balance")
    private Integer FIHoldBalance;

    @JsonProperty("FO_hold_balance")
    private Integer FOHoldBalance;

    @JsonProperty("FX_hold_balance")
    private Integer FXHoldBalance;

    @JsonProperty("M_I_warning")
    private Boolean MIWarning;

    @JsonProperty("M_O_warning")
    private Boolean MOWarning;

    @JsonProperty("M_Remaining_Limit_I")
    private Integer MRemainingLimitI;

    @JsonProperty("M_Remaining_Limit_O")
    private Integer MRemainingLimitO;

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    @JsonProperty("PG100_name")
    private String groupName;

    @JsonProperty("Remaining_Number_of_Times")
    private Integer RemainingNumberOfTimes;

    @JsonProperty("TR_in_hold_balance")
    private Integer TRInHoldBalance;

    @JsonProperty("TR_out_hold_balance")
    private Integer TROutHoldBalance;

    @JsonProperty("Times_warning")
    private Boolean TimesWarning;

    @JsonProperty("VPN100_ID")
    private Integer VPN100ID;

    @JsonProperty("account_code")
    private String accountCode;

    @JsonProperty("balance_lower_warning")
    private Boolean balanceLowerWarning;

    @JsonProperty("balance_upper_warning")
    private Boolean balanceUpperWarning;

    @JsonProperty("beginning_balance")
    private Double beginningBalance;

    @JsonProperty("beginning_balance_xy")
    private Double beginningBalanceXY;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("check_account")
    private Boolean checkAccount;

    @JsonProperty("command_id")
    private String commandId;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("created_man")
    private String creator;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("daily_limit")
    private Integer dailyLimit;

    @JsonProperty("email")
    private String email;

    @JsonProperty("for_testing")
    private Boolean forTesting;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("healthy_balance")
    private BigDecimal healthyBalance;

    @JsonProperty("hold_at")
    private LocalDateTime holdAt;

    @JsonProperty("is_errurl_opencard")
    private Integer isErrurlOpencard;

    @JsonProperty("is_promptpay_code")
    private Integer isPromptpayCode;

    @JsonProperty("is_qr_pay_code")
    private Integer isQrPayCode;

    @JsonProperty("loss")
    private Integer loss;

    @JsonProperty("m_pin")
    private String mPin;

    @JsonProperty("max_balance")
    private Integer maxBalance;

    @JsonProperty("max_balance_ratio")
    private Integer maxBalanceRatio;

    @JsonProperty("message")
    private String message;

    @JsonProperty("min_balance_percentage")
    private BigDecimal minBalancePercentage;

    @JsonProperty("min_reserved_amount")
    private BigDecimal minReservedAmount;

    @JsonProperty("mobile_id")
    private String mobileId;

    @JsonProperty("mobile_name")
    private String mobileName;

    @JsonProperty("mode")
    private Integer mode;

    @JsonProperty("month_limit")
    private Integer monthLimit;

    @JsonProperty("month_treshold")
    private Long monthTreshold;

    @JsonProperty("msg_opencard")
    private String msgOpencard;

    @JsonProperty("name")
    private String userName;

    @JsonProperty("note")
    private String note;

    @JsonProperty("number")
    private String bankNumber;

    @JsonProperty("opencard")
    private String opencard;

    @JsonProperty("pg_model")
    private Integer pgModel;

    @JsonProperty("phone_ip")
    private String phoneIp;

    @JsonProperty("pre_VPN100_ID")
    private String preVPN100ID;

    @JsonProperty("promptpay_code")
    private String promptpayCode;

    @JsonProperty("pw_transfer")
    private String pwTransfer;

    @JsonProperty("qr_pay_code")
    private String qrPayCode;

    @JsonProperty("remaining_balance")
    private Integer remainingBalance;

    @JsonProperty("secure_answer")
    private String secureAnswer;

    @JsonProperty("sim")
    private String sim;

    @JsonProperty("sim_credit_expired")
    private LocalDateTime simCreditExpired;

    @JsonProperty("sim_opt")
    private Integer simOpt;

    @JsonProperty("statement")
    private Statement statement;

    @JsonProperty("status")
    private Long status;

    @JsonProperty("status_preset")
    private Long statusPreset;

    @JsonProperty("step")
    private String step;

    @JsonProperty("step_history")
    private String stepHistory;

    @JsonProperty("step_opencard")
    private String stepOpencard;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @JsonProperty("updated_man")
    private String updater;

    @JsonProperty("vnd_app_password")
    private String vndAppPassword;

    @JsonProperty("vnd_otp")
    private String vndOtp;

    @JsonProperty("vnd_payment_method")
    private String vndPaymentMethod;

    @JsonProperty("vpn_name")
    private String vpnName;

    @JsonProperty("withdraw_pin")
    private String withdrawPin;
}
