package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SystemWithdrawalOrderVo {

    /**
     * 本表id
     */
    @JsonProperty("FO100_ID")
    private Long foId;

    /**
     * 订单通知记录内容 通过foreign_key fo_id 和 模型类：充值 FI 下发 FX 代付 FO
     */
    @JsonProperty("ON100_content")
    private String noticeContent;

    /**
     * 订单通知记录结果 订单通知记录表 on100
     */
    @JsonProperty("ON100_result")
    private String noticeResult;

    /**
     * 订单通知记录更新时间
     */
    @JsonProperty("ON100_updated_at")
    private LocalDateTime noticeRecordUpdatedAt;

    /**
     * 系统商户表id
     */
    @JsonProperty("SH100_ID")
    private int mechantId;

    /**
     * 商户代码
     */
    @JsonProperty("SH100_code")
    private String mechantCode;

    /**
     * 代付费率 商户表
     */
    @JsonProperty("SH100_wd_rate")
    private int sh100WdRate;

    /**
     * 审核账户相关 需要子代付表的全部status都为7 4 5 6才为true
     */
    @JsonProperty("acc_confirm_deniable")
    private boolean accConfirmDeniable;

    /**
     * 审核账户名称
     */
    @JsonProperty("acc_name_for_confirm")
    private List<String> accNameForConfirm;

    /**
     * 手动操作状态
     */
    @JsonProperty("action")
    private String action;

    /**
     * 拼接主键id
     */
    @JsonProperty("alt_id")
    private String altId;

    /**
     * 银行手续费
     */
    @JsonProperty("bank_fee")
    private BigDecimal bankFee;

    /**
     * 检查时间(由商户确认)
     */
    @JsonProperty("checked_at")
    private LocalDateTime checkedAt;

    /**
     * 检查人员(由商户确认)
     */
    @JsonProperty("checked_man")
    private String checkedMan;

    /**
     * 订单完成时间
     */
    @JsonProperty("completed_at")
    private LocalDateTime completedAt;

    /**
     * 审核账户名称
     */
    @JsonProperty("confirm_accname")
    private int confirmAccname;

    /**
     * 审核时间
     */
    @JsonProperty("confirmed_at")
    private LocalDateTime confirmedAt;

    /**
     * 审核人员
     */
    @JsonProperty("confirmed_man")
    private String confirmedMan;

    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * 手动的时候才会有回调方法为true 即status为4
     */
    @JsonProperty("is_callbackable")
    private boolean isCallbackable;

    /**
     * 备注
     */
    @JsonProperty("note")
    private String note;

    /**
     * 异步通知地址
     */
    @JsonProperty("notify_url")
    private String notifyUrl;

    /**
     * 实际作用金额
     */
    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    /**
     * 启用由商户支付银行手续费
     */
    @JsonProperty("pay_bank_fee")
    private Integer payBankFee;

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
     * 拒绝按钮 1 4 就是true 6 8 查询子代付先去重如果剩余结果为1条，则查询代付为1，子代付为1则为true或者代付为2子代付为4或5则也为true
     */
    @JsonProperty("reject_btn")
    private boolean rejectBtn;

    /**
     * 用户发起金额
     */
    @JsonProperty("request_amount")
    private BigDecimal requestAmount;

    /**
     * 状态
     */
    @JsonProperty("status")
    private int status;

    /**
     * 状态明细 1: 余额不足, 2:无卡
     */
    @JsonProperty("status_description")
    private int statusDescription;

    /**
     * to银行
     */
    @JsonProperty("to_bank")
    private String toBank;

    /**
     * to银行账户
     */
    @JsonProperty("to_card_number")
    private String toCardNumber;

    /**
     * to用户名称
     */
    @JsonProperty("to_man")
    private String toMan;

    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    /**
     * from银行code
     */
    private String bankCode;

    /**
     * 账号
     */
    private String bankNumber;
}
