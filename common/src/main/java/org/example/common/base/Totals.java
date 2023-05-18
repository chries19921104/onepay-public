package org.example.common.base;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Totals {
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal depositOutstandingBalance;
    private BigDecimal frozenBalance;
    private BigDecimal holdBalance;
    private BigDecimal todayTrFee;
}
