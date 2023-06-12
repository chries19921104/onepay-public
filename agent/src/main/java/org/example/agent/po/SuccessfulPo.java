package org.example.agent.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SuccessfulPo {

    private Integer count;
    private BigDecimal amount;
    private BigDecimal rate;

}
