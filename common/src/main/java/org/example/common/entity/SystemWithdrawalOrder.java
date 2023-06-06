package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SystemWithdrawalOrder {
    /**
     * 代付ID
     */
    @TableId(type = IdType.AUTO)
    private Long foId;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 参考编号
     */
    private String reference;

    /**
     * to用户名称
     */
    private String toMan;

    /**
     * to银行
     */
    private String toBank;

    /**
     * to银行账户
     */
    private String toCardNumber;

    /**
     * 用户发起金额
     */
    private BigDecimal requestAmount;

    /**
     * 实际作用金额
     */
    private BigDecimal paidAmount;

    /**
     * 系统手续费
     */
    private BigDecimal rate;

    /**
     * 启用由商户支付银行手续费
     */
    private Integer payBankFee;

    /**
     * 银行手续费
     */
    private BigDecimal bankFee;

    /**
     * 商户支付银行手续费
     */
    private BigDecimal bankFeeMerchant;

    /**
     * 余额 (from)
     */
    private BigDecimal balance;

    /**
     * 交易前余额 (from)
     */
    private BigDecimal preBalance;

    /**
     * 讯银余额 (from)
     */
    private BigDecimal balanceXy;

    /**
     * 讯银交易前余额 (from)
     */
    private BigDecimal preBalanceXy;

    /**
     * 商户余额
     */
    private BigDecimal merchantBalance;

    /**
     * 订单完成时间
     */
    private LocalDateTime completedAt;

    /**
     * 币别
     */
    private String currency;

    /**
     * "根据 FO110 去判斷
     * 状态 1.Pending–等待机器人跑
     *      2.Processing–机器人正在跑
     *      3.Completed–成功交易
     *      4.Manual process–失败后的手动操作
     *      人工可操作成成功或失败（如果交易试成功的）
     *      5.Failed–失败, 不可更改状态
     */
    private Integer status;

    /**
     * 状态明细 1: 余额不足, 2:无卡
     */
    private Integer statusDescription;

    /**
     * 手动操作状态
     * 1. reject BS200
     */
    private Integer action;

    /**
     * 备注
     */
    private String note;

    /**
     * 强制显示子单新增按钮
     */
    private boolean forceSubManual;

    /**
     * 审核账户名称
     *      0 预设(不需审核)
     *      1 待财务审核
     *      2 待商户审核
     *      3 拒绝
     *      4 通过
     */
    private Integer confirmAccname;

    /**
     * 检查人员(由商户确认)
     */
    private String checkedMan;

    /**
     * 检查时间(由商户确认)
     */
    private LocalDateTime checkedAt;

    /**
     * 审核人员
     */
    private String confirmedMan;

    /**
     * 审核时间
     */
    private LocalDateTime confirmedAt;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 更新人员
     */
    private String updater;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 当地创建时间
     */
    private LocalDateTime localCreatedAt;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * MN100ID
     */
    @TableField("MN100_ID")
    private Long MN100Id;
}
