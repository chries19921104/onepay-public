package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SystemVirtualBankStatement {

    /**
     * 虚拟银行ID
     */
    @TableId(type = IdType.AUTO)
    private Long vbId;

    /**
     * 当地创建时间
     */
    private LocalDateTime localCreatedAt;

    /**
     * 商户ID
     */
    private Long merchantId;

    /**
     * 银行ID
     */
    private Long bankId;

    /**
     * 银行卡ID
     */
    private Integer bankCardId;

    /**
     * 交易时间
     */
    private LocalDateTime transactionDate;

    /**
     * 对账排序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 描述
     */
    private String description;

    /**
     * 参考编号
     */
    private String reference;

    /**
     * 币种
     */
    private String currency;

    /**
     * 转出
     */
    private BigDecimal debit;

    /**
     * 转入
     */
    private BigDecimal credit;

    /**
     * 讯银余额
     */
    @TableField("balance_xy")
    private BigDecimal balanceXY;

    /**
     * 讯银交易前余额
     */
    @TableField("pre_balance_xy")
    private BigDecimal preBalanceXY;

    /**
     * 银行手续费
     */
    private BigDecimal bankFee;

    /**
     * 我们的单号
     */
    private String commandId;

    /**
     *
     1.Deposit  充值
     2.Withdraw 代付
     3.Settlement 下发
     4.Internal transfer  内转
     5.Bank charge 银行手续费
     6.Bank interest 银行利息
     7.Unclaimed fund 无法辩别的收入
     8.Unknown transfer 无法辨别的支出
     9.DOUBLE PAYOUT
     10.SPMC
     11.OPENCARD
     12.AJ FI
     13.AJ FO
     14.AJ TOPUP
     15.AJ BALANCE
     16.TECH TEST
     17.BIMC
     19.MA REBATE
     20.SIM TOPUP
     21.BANK RENTAL
     22.OTHERS
     24.REFUND FRAUD
     25.REFUND DEPOSIT
     26.INTERNAL TRANSFER
     27.BANK REVERSAL
     */
    private Integer type;

    private String modelClass;

    /**
     * 数据来源模式：1.Bank 2.QR Code
     */
    private Integer txnMode;

    /**
     * 是否作废：1.作废
     */
    @TableField("void")
    private Integer isVoid;

    // 重复账VBS
    private Integer duplicateId;

    /**
     * void 请求者
     */
    private String voidRequester;

    /**
     * 作废批准者
     */
    private String voidApprover;

    /**
     * 作废批准时间
     */
    private LocalDateTime voidApprovedAt;

    /**
     * 作废拒绝者
     */
    private String voidRejector;

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
     * 交易单创建时间
     */
    private LocalDateTime transactionCreatedAt;
}
