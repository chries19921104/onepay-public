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
     * 银行卡表id
     */
    @JsonProperty("BC100_ID")
    private Long bankCardId;

    /**
     * 银行卡表
     */
    @JsonProperty("BC100_mobile_name")
    private String bankCardMobileName;

    /**
     * 代付表id
     */
    @JsonProperty("FO100_ID")
    private Long foId;

    @JsonProperty("FO110_ID")
    private Long subFoId;

    @JsonProperty("FO110_PREID")
    private Long foPreId;

    /**
     * 商户表id
     */
    @JsonProperty("SH100_ID")
    private Long mechantId;

    /**
     * 商户表
     */
    @JsonProperty("SH100_wd_rate")
    private Integer mechantWdRate;

    /**
     * 虚拟银行对账单id
     */
    @JsonProperty("VB100_ID")
    private Long vbId;

    @JsonProperty("action")
    private String action;

    /**
     * 拼接主键id
     */
    @JsonProperty("alt_id")
    private String altId;

    @JsonProperty("bank_fee")
    private Integer bankFee;

    @JsonProperty("bank_fee_tpi")
    private String bankFeeTpi;

    /**
     * fo100 controller中的字段
     */
    @JsonProperty("check_btn")
    private Integer checkBtn;

    @JsonProperty("command_id")
    private String commandId;

    /**
     * 代付表 审核账户名称
     */
    @JsonProperty("confirm_accname")
    private Integer confirmAccname;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String createdMan;

    @JsonProperty("currency")
    private String currency;

    /**
     * 银行卡表 账户代码
     */
    @JsonProperty("from_account_code")
    private String fromAccountCode;

    @JsonProperty("is_entry")
    private Integer isEntry;

    /**
     * fo100 controller中的字段 是否有错误图片地址
     */
    @JsonProperty("is_errurl")
    private Boolean isErrurl;

    /**
     * 是否点选过按钮
     */
    @JsonProperty("is_press_button")
    private Boolean isPressButton;

    /**
     * fo100 controller中的字段
     */
    @JsonProperty("is_runMon")
    private Boolean isRunMon;

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
     * 过去子代付id拼接
     */
    @JsonProperty("prev_alt_id")
    private String prevAltId;

    @JsonProperty("rate")
    private String rate;

    /**
     * 代付表 参考编号
     */
    @JsonProperty("reference")
    private String reference;

    @JsonProperty("request_amount")
    private Integer requestAmount;

    /**
     * TODO 未知
     */
    @JsonProperty("retry_btn")
    private Boolean retryBtn;

    @JsonProperty("retry_times")
    private Integer retryTimes;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("step")
    private String step;

    @JsonProperty("tpi100_driver")
    private String tpi100Driver;

    @JsonProperty("tpi100_id")
    private Long thirdId;

    @JsonProperty("txn_mode")
    private Integer txnMode;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_man")
    private String updatedMan;

    @JsonProperty("vnd_otp")
    private String vndOtp;

    @JsonProperty("vnd_payment_method")
    private String vndPaymentMethod;

    /**
     * 代付表状态
     */
    private Integer foStatus;
}
