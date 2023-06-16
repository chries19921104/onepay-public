package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankCardCreateDto {

    @JsonProperty("ATM_card_number")
    private String atmCardNumber;

    @JsonProperty("ATM_pin")
    private String atmPin;

    @JsonProperty("BK100_ID")
    private Long cardId;

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    @JsonProperty("account_code")
    private String accountCode;

    @JsonProperty("beginning_balance")
    private String beginningBalance;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("daily_limit")
    private BigDecimal dailyDisbursementLimit;

    @JsonProperty("email")
    private String email;

    @JsonProperty("healthy_balance")
    private BigDecimal healthyBalance;

    @JsonProperty("identity")
    private String identity;//1

    @JsonProperty("m_pin")
    private String mPin;

    @JsonProperty("max_balance")
    private BigDecimal dailyCollectionLimit;

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

    @JsonProperty("name")
    private String account;

    @JsonProperty("note")
    private String note;

    @JsonProperty("number")
    private String bankNumber;

    @JsonProperty("password")
    private String password;

    @JsonProperty("pg_model")
    private int pgModel; //取消字段

    @JsonProperty("phone_ip")
    private String phoneIp;

    @JsonProperty("promptpay_code")
    private String promptpayCode;

    @JsonProperty("pw_transfer")
    private String pwTransfer;

    @JsonProperty("qr_pay_code")
    private String qrPayCode;

    @JsonProperty("secure_answer")
    private String secureAnswer;

    @JsonProperty("sim")
    private String phoneNumber;

    @JsonProperty("sim_credit_expired")
    private String simCreditExpired;

    @JsonProperty("sim_opt")
    private Integer simOpt;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("vnd_app_password")
    private String vndAppPassword;

    @JsonProperty("vnd_otp")
    private String vndOtp;

    @JsonProperty("vnd_payment_method")
    private String vndPaymentMethod;

    @JsonProperty("withdraw_pin")
    private String withdrawPin;

    @JsonProperty("youtap_pin")
    private String youtapPin;//1
}
