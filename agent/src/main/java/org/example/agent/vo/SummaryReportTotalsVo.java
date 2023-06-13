package org.example.agent.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryReportTotalsVo {

    private BigDecimal markup;

    private BigDecimal rebate;

    private BigDecimal total;

    private BigDecimal monthly_adjustment;

}
