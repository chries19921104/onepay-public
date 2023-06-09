package org.example.agent.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用于接收数据库日报数据
 */
@Data
public class DailyReportPo {


    // 日期
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date date;
    //银行充值
    @JsonProperty("FI_bank_balance")
    private BigDecimal fiBankBalance;
    //银行充值交易费
    @JsonProperty("FI_bank_fees")
    private BigDecimal fiBankFees;

    //qrpay充值
    @JsonProperty("FI_qrpay_balance")
    private BigDecimal fiQrpayBalance;

    //qrpay充值交易费
    @JsonProperty("FI_qrpay_fees")
    private BigDecimal FiQrpayFees;

    //TrueWallet充值
    @JsonProperty("FI_truewallet_balance")
    private BigDecimal fiTruewalletBalance;
    //Truewallet充值交易费
    @JsonProperty("FI_truewallet_fees")
    private BigDecimal fiTruewalletFees;
    //损失金额
    @JsonProperty("loss_amount")
    private BigDecimal lossAmount;
    //代付
    @JsonProperty("FO_balance")
    private BigDecimal foBalance;
    //代付交易费
    @JsonProperty("FO_fees")
    private BigDecimal foFees;
    //银行充值抽成
    @JsonProperty("FI_bank_markup_rate")
    private BigDecimal fiBankMarkupRate;
    //qr充值抽成
    @JsonProperty("FI_qrpay_markup_rate")
    private BigDecimal fiQrpayMarkupRate;
    //truewallet充值抽成
    @JsonProperty("FI_truewallet_markup_rate")
    private BigDecimal fiTruewalletMarkupRate;
    //代付抽成
    @JsonProperty("FO_markup_rate")
    private BigDecimal foMarkupRate;
    //VNPAY Zalo 扫码 充值
    @JsonProperty("FI_ZALO_QR_balance")
    private BigDecimal fiVnpayZaloQrBalance;
    //VNPAY Zalo 扫码 充值交易费
    @JsonProperty("FI_ZALO_QR_fees")
    private BigDecimal fiVnpayZaloQrFees;
    //VNPAY momo 扫码 充值
    @JsonProperty("FI_MOMO_QR_balance")
    private BigDecimal fiVnpayMomoQrBalance;
    //VNPAY momo 扫码 充值交易费
    @JsonProperty("FI_MOMO_QR_fees")
    private BigDecimal fiVnpayMomoQrFees;
    //VNPAY Viettel 扫码 充值
    @JsonProperty("FI_VIETTEL_QR_balance")
    private BigDecimal fiVnpayViettelQrBalance;
    //VNPAY Viettel 扫码 充值交易费
    @JsonProperty("FI_VIETTEL_QR_fees")
    private BigDecimal fiVnpayViettelQrFees;
    //VNPAY Viettel 固码扫码 充值
    @JsonProperty("FI_VIETTEL_FIX_balance")
    private BigDecimal fiVnpayViettelFixBalance;
    //VNPAY Viettel 固码扫码 充值交易费
    @JsonProperty("FI_VIETTEL_FIX_fees")
    private BigDecimal fiVnpayViettelFixFees;
    //VNPAY 银行卡转账 充值
    @JsonProperty("FI_BANK_CARD_balance")
    private BigDecimal fiVnpayBankCardBalance;
    //VNPAY 银行卡转账 充值交易费
    @JsonProperty("FI_BANK_CARD_fees")
    private BigDecimal fiVnpayBankCardFees;
    //VNPAY 充值卡 充值
    @JsonProperty("FI_RCGCARD_PC_balance")
    private BigDecimal fiVnpayRcgcardPcBalance;
    //VNPAY 充值卡 充值交易费
    @JsonProperty("FI_RCGCARD_PC_fees")
    private BigDecimal fiVnpayRcgcardPcFees;
    //VNPAY 充值卡 Zing 充值
    @JsonProperty("FI_RCGCARD_ZING_balance")
    private BigDecimal fiVnpayRcgcardZingBalance;
    //VNPAY 充值卡 Zing 充值交易费
    @JsonProperty("FI_RCGCARD_ZING_fees")
    private BigDecimal fiVnpayRcgcardZingFees;

    //总抽成
    @JsonProperty("total_markup")
    private BigDecimal totalMarkup;
}
