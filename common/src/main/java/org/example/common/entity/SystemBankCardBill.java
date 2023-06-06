package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* <p>
* system_bank_card_bill 实体类
* </p>
*
* @author Administrator
* @since 2023-06-05 18:33:14
*/
@Getter
@Setter
@TableName("system_bank_card_bill")
public class SystemBankCardBill implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 银行卡钱包ID
    */
    @TableId
    private Long walletId;

    /**
    * 银行卡ID
    */
    private Long bankCardId;

    /**
    * 账户余额
    */
    private BigDecimal currentBalance;

    /**
    * 账户余额_讯银
    */
    private BigDecimal currentBalanceXy;

    /**
    * FI 冻结金额 (入)
    */
    private BigDecimal fiHoldBalance;

    /**
    * FO 冻结金额 (出)
    */
    private BigDecimal foHoldBalance;

    /**
    * FX 冻结金额 (出)
    */
    private BigDecimal fxHoldBalance;

    /**
    * ET 冻结金额 (出)
    */
    private BigDecimal etHoldBalance;

    /**
    * TR IN 冻结金额 (入)
    */
    private BigDecimal trInHoldBalance;

    /**
    * TR OUT 冻结金额 (出)
    */
    private BigDecimal trOutHoldBalance;

    /**
    * 日流水量 (入)
    */
    private BigDecimal dayFlowIn;

    /**
    * 月流水量 (入)
    */
    private BigDecimal monthFlowIn;

    /**
    * 日流水量 (出)
    */
    private BigDecimal dayFlowOut;

    /**
    * 月流水量 (出)
    */
    private BigDecimal monthFlowOut;

    /**
    * 月成功次数 (入)
    */
    private Long monthCurrentSuccessIn;

    /**
    * 月冻结成功次数 (入)
    */
    private Long monthHoldSuccessIn;

    /**
    * 月成功次数 (出)
    */
    private Long monthCurrentSuccessOut;

    /**
    * 月冻结成功次数 (出)
    */
    private Long monthHoldSuccessOut;

    /**
    * 日成功次数 (入)
    */
    private Long dayCurrentSuccessIn;

    /**
    * 日冻结成功次数 (入)
    */
    private Long dayHoldSuccessIn;

    /**
    * 日成功次数 (出)
    */
    private Long dayCurrentSuccessOut;

    /**
    * 日冻结成功次数 (出)
    */
    private Long dayHoldSuccessOut;

    /**
    * 未知的 VBS 帐数量 (日)
    */
    private Long dayUnknownVbs;

    /**
    * 今日收款
    */
    private BigDecimal totalDayCredit;

    /**
    * 今日支付
    */
    private BigDecimal totalDayDebit;

    /**
    * 负载量
    */
    private Integer usage;

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


}
