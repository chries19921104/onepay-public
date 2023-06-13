package org.example.agent.bo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用日报业务总计功能
 */
@Getter
@Setter
public class TotalBalanceBo {


    //银行充值
    private BigDecimal FI_bank_balance;

    //qrpay充值
    private BigDecimal FI_qrpay_balance;

    //TrueWallet充值
    private BigDecimal FI_truewallet_balance;


    //代付
    private BigDecimal FO_balance;


    //VNPAY Zalo 扫码 充值
    private BigDecimal FI_ZALO_QR_balance;

    //VNPAY momo 扫码 充值
    private BigDecimal FI_MOMO_QR_balance;

    //VNPAY Viettel 扫码 充值
    private BigDecimal FI_VIETTEL_QR_balance;

    //VNPAY Viettel 固码扫码 充值
    private BigDecimal FI_VIETTEL_FIX_balance;

    //VNPAY 银行卡转账 充值
    private BigDecimal FI_BANK_CARD_balance;

    //VNPAY 充值卡 充值
    private BigDecimal FI_RCGCARD_PC_balance;

    //VNPAY 充值卡 Zing 充值
    private BigDecimal FI_RCGCARD_ZING_balance;

    public TotalBalanceBo() {
        this.FI_bank_balance = new BigDecimal(0);
        this.FI_qrpay_balance = new BigDecimal(0);
        this.FI_truewallet_balance = new BigDecimal(0);
        this.FO_balance = new BigDecimal(0);
        this.FI_ZALO_QR_balance = new BigDecimal(0);
        this.FI_MOMO_QR_balance = new BigDecimal(0);
        this.FI_VIETTEL_QR_balance = new BigDecimal(0);
        this.FI_VIETTEL_FIX_balance = new BigDecimal(0);
        this.FI_BANK_CARD_balance = new BigDecimal(0);
        this.FI_RCGCARD_PC_balance = new BigDecimal(0);
        this.FI_RCGCARD_ZING_balance = new BigDecimal(0);
    }

    public TotalBalanceBo(BigDecimal FI_bank_balance, BigDecimal FI_qrpay_balance, BigDecimal FI_truewallet_balance, BigDecimal FO_balance, BigDecimal FI_ZALO_QR_balance, BigDecimal FI_MOMO_QR_balance, BigDecimal FI_VIETTEL_QR_balance, BigDecimal FI_VIETTEL_FIX_balance, BigDecimal FI_BANK_CARD_balance, BigDecimal FI_RCGCARD_PC_balance, BigDecimal FI_RCGCARD_ZING_balance) {
        this.FI_bank_balance = FI_bank_balance;
        this.FI_qrpay_balance = FI_qrpay_balance;
        this.FI_truewallet_balance = FI_truewallet_balance;
        this.FO_balance = FO_balance;
        this.FI_ZALO_QR_balance = FI_ZALO_QR_balance;
        this.FI_MOMO_QR_balance = FI_MOMO_QR_balance;
        this.FI_VIETTEL_QR_balance = FI_VIETTEL_QR_balance;
        this.FI_VIETTEL_FIX_balance = FI_VIETTEL_FIX_balance;
        this.FI_BANK_CARD_balance = FI_BANK_CARD_balance;
        this.FI_RCGCARD_PC_balance = FI_RCGCARD_PC_balance;
        this.FI_RCGCARD_ZING_balance = FI_RCGCARD_ZING_balance;
    }
}
