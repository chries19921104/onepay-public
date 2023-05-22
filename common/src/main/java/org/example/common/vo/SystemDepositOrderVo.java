package org.example.common.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SystemDepositOrderVo {

    private Integer txnMode;
    private BigDecimal successMoney;
    private BigDecimal failMoney;
    private Integer successCount;
    private Integer failCount;

    private BigDecimal bankFee;
}
