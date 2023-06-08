package org.example.admin.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DepositOrderCommissionVo {
    private BigDecimal money;
    private BigDecimal merchantFee;
    private BigDecimal bankFee;
}
