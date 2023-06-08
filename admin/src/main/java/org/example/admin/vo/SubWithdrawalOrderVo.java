package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author notch
 * @create 2023-06-08-8:25
 */
@Data
public class SubWithdrawalOrderVo {
    @JsonProperty("BC100_ID")
    private int bc100Id;

    @JsonProperty("BC100_mobile_name")
    private String bc100MobileName;

    @JsonProperty("FO100_ID")
    private int fo100Id;

    @JsonProperty("FO110_ID")
    private int fo110Id;

    @JsonProperty("FO110_PREID")
    private String fo110Preid;

    @JsonProperty("SH100_ID")
    private int sh100Id;

    @JsonProperty("SH100_wd_rate")
    private int sh100WdRate;

    @JsonProperty("VB100_ID")
    private String vb100Id;

    @JsonProperty("action")
    private String action;

    @JsonProperty("alt_id")
    private String altId;

    @JsonProperty("bank_fee")
    private int bankFee;

    @JsonProperty("bank_fee_tpi")
    private String bankFeeTpi;

    @JsonProperty("check_btn")
    private int checkBtn;

    @JsonProperty("command_id")
    private String commandId;

    @JsonProperty("confirm_accname")
    private int confirmAccname;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String createdMan;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("from_account_code")
    private String fromAccountCode;

    @JsonProperty("is_entry")
    private int isEntry;

    @JsonProperty("is_errurl")
    private boolean isErrurl;

    @JsonProperty("is_press_button")
    private boolean isPressButton;

    @JsonProperty("is_runMon")
    private boolean isRunMon;

    @JsonProperty("local_updated_at")
    private String localUpdatedAt;

    @JsonProperty("message")
    private String message;

    @JsonProperty("note")
    private String note;

    @JsonProperty("paid_amount")
    private String paidAmount;

    @JsonProperty("postscript")
    private String postscript;

    @JsonProperty("prev_alt_id")
    private String prevAltId;

    @JsonProperty("rate")
    private String rate;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("request_amount")
    private int requestAmount;

    @JsonProperty("retry_btn")
    private boolean retryBtn;

    @JsonProperty("retry_times")
    private int retryTimes;

    @JsonProperty("status")
    private int status;

    @JsonProperty("step")
    private String step;

    @JsonProperty("tpi100_driver")
    private String tpi100Driver;

    @JsonProperty("tpi100_id")
    private String tpi100Id;

    @JsonProperty("txn_mode")
    private int txnMode;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_man")
    private String updatedMan;

    @JsonProperty("vnd_otp")
    private String vndOtp;

    @JsonProperty("vnd_payment_method")
    private String vndPaymentMethod;
}
