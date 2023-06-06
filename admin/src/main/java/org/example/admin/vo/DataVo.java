package org.example.admin.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataVo {
    private BigDecimal todaySuccessBankMoney;
    private BigDecimal todaySuccessQRMoney;
    private BigDecimal todaySuccessTWMoney;
    private BigDecimal todaySuccessBDBankMoney;
    private BigDecimal todaySuccessDFMoney;
    private BigDecimal todaySuccessXFMoney;
    private BigDecimal todaySuccessXNMoney;


    private int todaySuccessBankCount;
    private int todaySuccessQRCount;
    private int todaySuccessTWCount;
    private int todaySuccessBDBankCount;
    private int todaySuccessDFCount;
    private int todaySuccessXFCount;
    private int todaySuccessXNCount;



    private BigDecimal yesterdaySuccessBankMoney;
    private BigDecimal yesterdaySuccessQRMoney;
    private BigDecimal yesterdaySuccessTWMoney;
    private BigDecimal yesterdaySuccessBDBankMoney;
    private BigDecimal yesterdaySuccessDFMoney;
    private BigDecimal yesterdaySuccessXFMoney;
    private BigDecimal yesterdaySuccessXNMoney;


    private int yesterdaySuccessBankCount;
    private int yesterdaySuccessQRCount;
    private int yesterdaySuccessTWCount;
    private int yesterdaySuccessBDBankCount;
    private int yesterdaySuccessDFCount;
    private int yesterdaySuccessXFCount;
    private int yesterdaySuccessXNCount;



    private BigDecimal todayFailBankMoney;
    private BigDecimal todayFailQRMoney;
    private BigDecimal todayFailTWMoney;
    private BigDecimal todayFailBDBankMoney;
    private BigDecimal todayFailDFMoney;
    private BigDecimal todayFailXFMoney;
    private BigDecimal todayFailXNMoney;


    private int todayFailBankCount;
    private int todayFailQRCount;
    private int todayFailTWCount;
    private int todayFailBDBankCount;
    private int todayFailDFCount;
    private int todayFailXFCount;
    private int todayFailXNCount;



    private BigDecimal yesterdayFailBankMoney;
    private BigDecimal yesterdayFailQRMoney;
    private BigDecimal yesterdayFailTWMoney;
    private BigDecimal yesterdayFailBDBankMoney;
    private BigDecimal yesterdayFailDFMoney;
    private BigDecimal yesterdayFailXFMoney;
    private BigDecimal yesterdayFailXNMoney;


    private int yesterdayFailBankCount;
    private int yesterdayFailQRCount;
    private int yesterdayFailTWCount;
    private int yesterdayFailBDBankCount;
    private int yesterdayFailDFCount;
    private int yesterdayFailXFCount;
    private int yesterdayFailXNCount;


    private BigDecimal todayBankIncome;
    private BigDecimal todayQRIncome;
    private BigDecimal todayTWIncome;
    private BigDecimal todayBDBankIncome;
    private BigDecimal todayDFIncome;
    private BigDecimal todayXFIncome;
    private BigDecimal todayXNIncome;

    private BigDecimal yesterdayBankIncome;
    private BigDecimal yesterdayQRIncome;
    private BigDecimal yesterdayTWIncome;
    private BigDecimal yesterdayBDBankIncome;
    private BigDecimal yesterdayDFIncome;
    private BigDecimal yesterdayXFIncome;
    private BigDecimal yesterdayXNIncome;

    private BigDecimal thisMonthBankIncome;
    private BigDecimal thisMonthQRIncome;
    private BigDecimal thisMonthTWIncome;
    private BigDecimal thisMonthBDBankIncome;
    private BigDecimal thisMonthDFIncome;
    private BigDecimal thisMonthXFIncome;
    private BigDecimal thisMonthXNIncome;

    private BigDecimal lastMonthBankIncome;
    private BigDecimal lastMonthQRIncome;
    private BigDecimal lastMonthTWIncome;
    private BigDecimal lastMonthBDBankIncome;
    private BigDecimal lastMonthDFIncome;
    private BigDecimal lastMonthXFIncome;
    private BigDecimal lastMonthXNIncome;

    private BigDecimal todayMerchantCommission;
    private BigDecimal yesterdayMerchantCommission;


    private BigDecimal todayMyCommission;
    private BigDecimal yesterdayMyCommission;

    private BigDecimal todayJSCommission;
    private BigDecimal yesterdayJSCommission;
    private BigDecimal todayNZCommission;
    private BigDecimal yesterdayNZCommission;
    private BigDecimal todayWZCommission;
    private BigDecimal yesterdayWZCommission;

    private BigDecimal todayQRLoss;
    private int todayQRCount;
    private BigDecimal thisQRLoss;
    private int thisQRCount;
    private BigDecimal lastQRLoss;
    private int lastQRCount;
    private BigDecimal allQRLoss;
    private int allQRCount;

    private BigDecimal todayTrueLoss;
    private int todayTrueCount;
    private BigDecimal thisTrueLoss;
    private int thisTrueCount;
    private BigDecimal lastTrueLoss;
    private int lastTrueCount;
    private BigDecimal allTrueLoss;
    private int allTrueCount;

    private int todayMerchantRegister;
    private int yesterdayMerchantRegister;

    private int successMerchantExamine;
    private int failMerchantExamine;

    private BigDecimal todayFreezeLoss;
    private int todayFreezeCount;
    private BigDecimal allFreezeLoss;
    private int allFreezeCount;


}
