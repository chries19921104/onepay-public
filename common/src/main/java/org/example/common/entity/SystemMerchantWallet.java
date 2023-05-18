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
* system_merchant_wallet 实体类
* </p>
*
* @author Administrator
* @since 2023-05-17 15:05:27
*/
@Getter
@Setter
@TableName("system_merchant_wallet")
public class SystemMerchantWallet implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商户钱包ID
    */
    @TableId
    private Long walletId;

    /**
    * 商户ID
    */
    private Long merchantId;

    /**
    * 账户余额
    */
    private BigDecimal currentBalance;

    /**
    * 可用余额
    */
    private BigDecimal availableBalance;

    /**
    * 冻结金额
    */
    private BigDecimal holdBalance;

    /**
    * 主单冻结金额
    */
    private BigDecimal holdBalanceMain;

    /**
    * 等待入款金额
    */
    private BigDecimal depositOutstandingBalance;

    /**
    * 今日转帐费用
    */
    private BigDecimal todayTrFee;

    /**
    * 最后一次完成的交易单号
    */
    private String lastTransaction;

    /**
    * 资金锁,0:正常,1:锁住
    */
    private Integer isLocked;

    /**
    * 资金锁action
    */
    private String lockAction;

    /**
    * 调整单冻结金额
    */
    private BigDecimal frozenBalance;

    /**
    * USDT余额
    */
    private BigDecimal usdtBalance;

    /**
    * USDT冻结金额
    */
    private BigDecimal usdtFrozenBalance;

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
