package org.example.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SystemDepositOrderVo {

    /**
     * 银行备注 BC100里的
     */
    @JsonProperty("bank_note")
    private String bankNote;

    /**
     * 系统银行卡列表id
     */
    @JsonProperty("BC100_ID")
    private Long bankCardId;

    /**
     * 充值订单表id
     */
    @JsonProperty("FI100_ID")
    private Long fiId;

    /**
     * 订单通知记录内容 通过foreign_key fi_id 和 模型类：充值 FI 下发 FX 代付 FO
     */
    @JsonProperty("ON100_content")
    private String on100Content;

    /**
     * 订单通知记录结果 订单通知记录表 on100
     */
    @JsonProperty("ON100_result")
    private String result;

    @JsonProperty("ON100_updated_at")
    private LocalDateTime noticeRecordUpdatedAt;

    /**
     * 	系统商户表id
     */
    @JsonProperty("SH100_ID")
    private Long merchantId;

    /**
     * 商户代码 系统商户表
     */
    @JsonProperty("SH100_code")
    private String code;

    /**
     * 充值QRpay费率% 系统商户表
     */
    @JsonProperty("SH100_tr_qr_rate")
    private BigDecimal trQrRate;

    /**
     * 充值费率% 系统商户表
     */
    @JsonProperty("SH100_tr_rate")
    private BigDecimal trRate;

    /**
     * SMS Monitor短信监控id
     */
    @JsonProperty("SM100_ID")
    private Long smId;

    /**
     * 短信监控 比对到的时间
     */
    @JsonProperty("SM100_pairing_time")
    private LocalDateTime smPairingTime;

    /**
     * 虚拟对账单主键id
     */
    @JsonProperty("VB100_ID")
    private Long vbId;

    /**
     * 虚拟对账单 比对到的时间
     */
    @JsonProperty("VB100_pairing_time")
    private LocalDateTime vbPairingTime;

    @JsonProperty("VPN100_ID")
    private Long altVpnId;

    /**
     * 通知状态
     */
    @JsonProperty("action")
    private Integer action;

    /**
     * 拼接主键id D-ddmmyy + fi1100
     */
    @JsonProperty("alt_id")
    private String altId;

    /**
     * 我们的单号
     */
    @JsonProperty("command_id")
    private String commandId;

    /**
     * 订单完成时间
     */
    @JsonProperty("completed_at")
    private LocalDateTime completedAt;

    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * from银行
     */
    @JsonProperty("from_bank")
    private String fromBank;

    /**
     * from银行账户
     */
    @JsonProperty("from_card_number")
    private String fromCardNumber;

    /**
     * TODO isCallbackable 先暂时为空
     */
    @JsonProperty("is_callbackable")
    private boolean isCallbackable;

    /**
     * TODO isErrurl 成功为false ll100根据commitId查询 还没有实体类
     */
    @JsonProperty("is_errurl")
    private boolean isErrurl;

    /**
     * 0:未登录,1:已输入帐号密码登录
     */
    @JsonProperty("is_log_in")
    private Integer isLogIn;

    /**
     * 0:未输入OTP,1:已输入OTP
     */
    @JsonProperty("is_otp_in")
    private Integer isOtpIn;

    /**
     * TODO isRunMon BC100表里的 没找到去查一下原表看不是字段漏了，先空着
     */
    @JsonProperty("is_runMon")
    private boolean isRunMon;

    /**
     * 0:未进入tuna,1:已进入tuna
     */
    @JsonProperty("is_tuna_in")
    private Integer isTunaIn;

    /**
     * 当地创建时间
     */
    @JsonProperty("local_updated_at")
    private LocalDateTime localUpdatedAt;

    /**
     * 损失金额
     */
    @JsonProperty("loss_amount")
    private BigDecimal lossAmount;

    /**
     * 信息，讯银回传当前状态（文字）
     */
    @JsonProperty("message")
    private String message;

    /**
     * 用户备注
     */
    @JsonProperty("note")
    private String note;

    /**
     * 异步通知地址
     */
    @JsonProperty("notify_url")
    private String notifyUrl;

    /**
     * 用户原始提交金额
     */
    @JsonProperty("order_amount")
    private BigDecimal orderAmount;

    /**
     * 实际作用金额
     */
    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    /**
     * 比对方式，1: 虚拟对账单, 2: 短信对比
     */
    @JsonProperty("pairing_by")
    private Integer pairingBy;

    /**
     * 对帐用唯一码
     */
    @JsonProperty("postscript")
    private String postscript;

    /**
     *  promptpayCode BC100里的
     */
    @JsonProperty("promptpay_code")
    private String promptpayCode;

    /**
     * 系统手续费
     */
    @JsonProperty("rate")
    private BigDecimal rate;

    /**
     * 参考编号，商户订单号
     */
    @JsonProperty("reference")
    private String reference;

    /**
     * 用户发起金额
     */
    @JsonProperty("request_amount")
    private BigDecimal requestAmount;

    /**
     * 讯银回传状态
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 状态明细 2:无卡
     */
    @JsonProperty("status_description")
    private Integer statusDescription;

    /**
     * 讯银步骤
     */
    @JsonProperty("step")
    private String step;

    /**
     * toAccountCode BC100里的
     */
    @JsonProperty("to_account_code")
    private String accountCode;

    /**
     * system_support_third_payment 表中没有driver字段 没什么用
     */
    @JsonProperty("tpi100_driver")
    private String tpi100Driver;

    /**
     * 第三方串接ID
     */
    @JsonProperty("tpi100_id")
    private Long tpi100Id;

    /**
     * 交易模式 1.Bank网银 2.QR Code二维码 3.Crypto加密
     */
    @JsonProperty("txn_mode")
    private Integer txnMode;

    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    /**
     * vndOtp BC100里的
     */
    @JsonProperty("vnd_otp")
    private String vndOtp;

    private BigDecimal successMoney;
    private BigDecimal failMoney;
    private Integer successCount;
    private Integer failCount;

    private BigDecimal bankFee;

}
