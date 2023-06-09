package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.example.common.entity.SystemBankCardBill;

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
    private String bankName;    //system_bank

    @JsonProperty("D_I_warning")
    private Boolean DIWarning;   //1

    @JsonProperty("D_O_warning")
    private Boolean DOWarning;   //1

    @JsonProperty("D_Remaining_Limit_I")
    private BigDecimal DRemainingLimitI;  //1

    @JsonProperty("D_Remaining_Limit_O")
    private BigDecimal DRemainingLimitO;   //1

    @JsonProperty("D_Remaining_Number_of_Times")//详情里面多的字段  1
    private Integer DRemainingNumberOfTimes;

    @JsonProperty("ET_hold_balance")
    private BigDecimal etHoldBalance;    //system_bank_card_bill

    @JsonProperty("FI_hold_balance")
    private BigDecimal fiHoldBalance;   //system_bank_card_bill

    @JsonProperty("FO_hold_balance")
    private BigDecimal foHoldBalance;   //system_bank_card_bill

    @JsonProperty("FX_hold_balance")
    private BigDecimal fxHoldBalance;   //system_bank_card_bill

    @JsonProperty("M_I_warning")
    private Boolean MIWarning;   //1

    @JsonProperty("M_O_warning")
    private Boolean MOWarning;   //1

    @JsonProperty("M_Remaining_Limit_I")
    private BigDecimal MRemainingLimitI;   //1

    @JsonProperty("M_Remaining_Limit_O")
    private BigDecimal MRemainingLimitO;   //1

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    @JsonProperty("PG100_name")
    private String groupName;  //system_bank_card_group

    @JsonProperty("Remaining_Number_of_Times")
    private Long RemainingNumberOfTimes;  //1

    @JsonProperty("TR_in_hold_balance")
    private BigDecimal trInHoldBalance;  //system_bank_card_bill

    @JsonProperty("TR_out_hold_balance")
    private BigDecimal trOutHoldBalance;  //system_bank_card_bill

    @JsonProperty("Times_warning")
    private Boolean TimesWarning;  //1

    @JsonProperty("VPN100_ID")
    private Long serverId;

    @JsonProperty("account_code")
    private String accountCode;

    @JsonProperty("balance_warning")
    private Boolean balanceWarning;//详情里面多的字段  1

    @JsonProperty("balance_lower_warning")
    private Boolean balanceLowerWarning;  //1

    @JsonProperty("balance_upper_warning")
    private Boolean balanceUpperWarning;  //1

    @JsonProperty("beginning_balance")
    private BigDecimal beginningBalance;

    @JsonProperty("beginning_balance_xy")
    private BigDecimal beginningBalanceXY;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("check_account")
    private Boolean checkAccount;  //1

    @JsonProperty("command_id")
    private String commandId;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("created_man")
    private String creator;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("daily_limit")
    private BigDecimal dailyDisbursementLimit;

    @JsonProperty("email")
    private String email;

    @JsonProperty("for_testing")
    private Boolean forTesting;

    @JsonProperty("full_name")
    private String fullName;  //bank_code+account+bank_number

    @JsonProperty("healthy_balance")
    private BigDecimal healthyBalance;

    @JsonProperty("hold_at")
    private LocalDateTime holdAt;

    @JsonProperty("hold_time")
    private String holdTime;//详情里面多的字段  1

    @JsonProperty("identity")
    private String identity;//详情里面多的字段   1

    @JsonProperty("is_dab_token_card")
    private Boolean isDabTokenCard;//详情里面多的字段  1

    @JsonProperty("last_BC120")
    private String lastBC120;//详情里面多的字段

    @JsonProperty("is_errurl_opencard")
    private Integer isErrurlOpencard;  //1

    @JsonProperty("is_promptpay_code")
    private Integer isPromptpayCode;  //1

    @JsonProperty("is_qr_pay_code")
    private Integer isQrPayCode;  //1

    @JsonProperty("loss")
    private BigDecimal loss;  //1

    @JsonProperty("m_pin")
    private String mPin;

    @JsonProperty("max_balance")
    private BigDecimal maxBalance;  //1

    @JsonProperty("max_balance_ratio")
    private Integer maxBalanceRatio;  //1

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
    private Integer paymentMode;

    @JsonProperty("month_limit")
    private BigDecimal monthDisbursementLimit;

    @JsonProperty("month_treshold")
    private Long monthTreshold;

    @JsonProperty("msg_opencard")
    private Integer msgOpencard;//1

    @JsonProperty("name")
    private String account;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("note")
    private String note;

    @JsonProperty("number")
    private String bankNumber;

    @JsonProperty("opencard")
    private Integer openingStatus ;

    @JsonProperty("pg_model")
    private Integer pgModel;//取消字段

    @JsonProperty("phone_ip")
    private String phoneIp;

    @JsonProperty("pre_VPN100_ID")
    private String preVPN100ID;   //查询时null，修改时传VPN100id的值

    @JsonProperty("promptpay_code")
    private String promptpayCode;

    @JsonProperty("pw_transfer")
    private String pwTransfer;

    @JsonProperty("qr_pay_code")
    private String qrPayCode;

    @JsonProperty("real_balance")
    private BigDecimal realBalance;//详详情里面多的字段   1

    @JsonProperty("remaining_balance")
    private BigDecimal remainingBalance;  //总余额

    @JsonProperty("secure_answer")
    private String secureAnswer;

    @JsonProperty("sim")
    private String phoneNumber;

    @JsonProperty("sim_credit_expired")
    private LocalDateTime simCreditExpired;

    @JsonProperty("sim_opt")
    private Integer simOpt;

    @JsonProperty("statement")
    private Statement statement;  //1

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("status_preset")
    private Long statusPreset;

    @JsonProperty("step")
    private String step;   //stepOpencard

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
    private String vpnName;   //system_server_list

    @JsonProperty("withdraw_pin")
    private String withdrawPin;

    @JsonProperty("youtap_pin")
    private String youtapPin;//详情里面多的字段   1

    @JsonProperty("b_c110")
    private SystemBankCardBill bankCardBill;
}
