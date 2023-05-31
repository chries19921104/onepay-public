package org.example.common.base;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "用于常用返回数据中的totals数据")
public class  Totals {
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal depositOutstandingBalance;
    private BigDecimal frozenBalance;
    private BigDecimal holdBalance;
    private BigDecimal todayTrFee;
    private BigDecimal lossAmount;
    private BigDecimal orderAmount;
    private BigDecimal paidAmount;
    private BigDecimal rate;
    private BigDecimal requestAmount;
}
