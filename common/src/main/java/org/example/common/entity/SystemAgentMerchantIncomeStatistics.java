package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemAgentMerchantIncomeStatistics {

    @TableId(type = IdType.AUTO)
    private Long sId;
    private Long merchantId;
    private Date date;
    private BigDecimal opBalance;
    private BigDecimal fiBankBalance;
    private BigDecimal fiBankFees;
    private BigDecimal fiBankCount;
    private BigDecimal fiQrpayBalance;
    private BigDecimal fiQrpayFees;
    private BigDecimal fiQrpayCount;
    private BigDecimal fiTruewalletBalance;
    private BigDecimal fiTruewalletFees;
    private BigDecimal fiTruewalletCount;
    private BigDecimal fiLocalbankBalance;
    private BigDecimal fiLocalbankFees;
    private BigDecimal fiLocalbankCount;
    private BigDecimal fiVnpayZaloQrBalance;
    private BigDecimal fiVnpayZaloQrFees;
    private BigDecimal fiVnpayZaloQrCount;
    private BigDecimal fiVnpayMomoQrBalance;
    private BigDecimal fiVnpayMomoQrFees;
    private BigDecimal fiVnpayMomoQrCount;
    private BigDecimal fiVnpayViettelQrBalance;
    private BigDecimal fiVnpayViettelQrFees;
    private BigDecimal fiVnpayViettelQrCount;
    private BigDecimal fiVnpayViettelFixBalance;
    private BigDecimal fiVnpayViettelFixFees;
    private BigDecimal fiVnpayViettelFixCount;
    private BigDecimal fiVnpayBankQrBalance;
    private BigDecimal fiVnpayBankQrFees;
    private BigDecimal fiVnpayBankQrCount;
    private BigDecimal fiVnpayBankCardBalance;
    private BigDecimal fiVnpayBankCardFees;
    private BigDecimal fiVnpayBankCardCount;
    private BigDecimal fiVnpayRcgcardPcBalance;
    private BigDecimal fiVnpayRcgcardPcFees;
    private BigDecimal fiVnpayRcgcardPcCount;
    private BigDecimal fiVnpayRcgcardZingBalance;
    private BigDecimal fiVnpayRcgcardZingFees;
    private BigDecimal fiVnpayRcgcardZingCount;
    private BigDecimal foBalance;
    private BigDecimal foFees;
    private BigDecimal foBankFeeMerchant;
    private BigDecimal foCount;
    private BigDecimal fxBalance;
    private BigDecimal fxFees;
    private BigDecimal fxCount;
    private BigDecimal fxVnpayBalance;
    private BigDecimal fxVnpayFees;
    private BigDecimal fxVnpayCount;
    private BigDecimal ajDebit;
    private BigDecimal ajDebitCount;
    private BigDecimal ajCredit;
    private BigDecimal ajCreditCount;
    private BigDecimal lastBalance;
    private BigDecimal settledFi;
    private BigDecimal fiBankMarkupRate;
    private BigDecimal fiQrpayMarkupRate;
    private BigDecimal fiTruewalletxMarkupRate;
    private BigDecimal fiLocalbankMarkupRate;
    private BigDecimal foMarkupRate;
    private BigDecimal fiBankRebate;
    private BigDecimal fiQrpayRebate;
    private BigDecimal fiTruewalletRebate;
    private BigDecimal fiLocalbankRebate;
    private BigDecimal foRebate;
    private BigDecimal revenue;
    private BigDecimal lossAmount;
    private String creator;
    private String updater;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
