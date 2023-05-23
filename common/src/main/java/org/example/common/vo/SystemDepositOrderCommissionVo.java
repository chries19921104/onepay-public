package org.example.common.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class SystemDepositOrderCommissionVo {
    private BigDecimal money;
    private BigDecimal merchantFee;
    private BigDecimal bankFee;
}
