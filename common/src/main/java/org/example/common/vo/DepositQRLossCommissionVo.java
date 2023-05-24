package org.example.common.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositQRLossCommissionVo {
    private BigDecimal money;
    private int count;
}
