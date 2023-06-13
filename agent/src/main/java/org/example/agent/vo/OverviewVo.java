package org.example.agent.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OverviewVo {

    private BigDecimal revenue;
    private Integer today_ransacation;
    private BigDecimal today_bank_deposit;
    private BigDecimal today_qr_deposit;
    private BigDecimal today_truewallet_deposit;
    private BigDecimal today_withdraw;
    private BigDecimal today_bank_transfer_deposit;
    private BigDecimal today_prepaid_deposit;
    private BigDecimal today_AlipayToBank_deposit;
    private BigDecimal today_UnionPayToBank_deposit;
    private BigDecimal today_BankToBank_deposit;

}
