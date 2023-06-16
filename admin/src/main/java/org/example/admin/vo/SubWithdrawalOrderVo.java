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
    /**
     * TODO 银行卡表id
     */
    @JsonProperty("BC100_ID")
    private int bankCardId;

    /**
     * TODO 银行卡表
     */
    @JsonProperty("BC100_mobile_name")
    private String bankCardMobileName;

    /**
     * TODO 代付表id
     */
    @JsonProperty("FO100_ID")
    private int foId;

    @JsonProperty("FO110_ID")
    private int subFoId;

    @JsonProperty("FO110_PREID")
    private String foPreId;

    /**
     * TODO 商户表id
     */
    @JsonProperty("SH100_ID")
    private int mechantId;

    /**
     * TODO 商户表
     */
    @JsonProperty("SH100_wd_rate")
    private int mechantWdRate;

    /**
     * TODO 虚拟银行对账单id
     */
    @JsonProperty("VB100_ID")
    private String vb1Id;

    @JsonProperty("action")
    private String action;

    /**
     * 拼接主键id
     */
    @JsonProperty("alt_id")
    private String altId;

    @JsonProperty("bank_fee")
    private int bankFee;

    @JsonProperty("bank_fee_tpi")
    private String bankFeeTpi;

    /**
     * TODO 未知
     */
    @JsonProperty("check_btn")
    private int checkBtn;

    @JsonProperty("command_id")
    private String commandId;

    /**
     * TODO 未知
     */
    @JsonProperty("confirm_accname")
    private int confirmAccname;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String createdMan;

    @JsonProperty("currency")
    private String currency;

    /**
     * TODO 银行卡表
     */
    @JsonProperty("from_account_code")
    private String fromAccountCode;

    @JsonProperty("is_entry")
    private int isEntry;

    /**
     * TODO 未知
     */
    @JsonProperty("is_errurl")
    private boolean isErrurl;

    /**
     * TODO 未知
     */
    @JsonProperty("is_press_button")
    private boolean isPressButton;

    /**
     * TODO 未知
     */
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

    /**
     * TODO 未知
     */
    @JsonProperty("prev_alt_id")
    private String prevAltId;

    @JsonProperty("rate")
    private String rate;

    /**
     * TODO 未知
     */
    @JsonProperty("reference")
    private String reference;

    @JsonProperty("request_amount")
    private int requestAmount;

    /**
     * TODO 未知
     */
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
