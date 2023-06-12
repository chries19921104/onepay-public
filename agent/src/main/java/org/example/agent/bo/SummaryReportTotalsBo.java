package org.example.agent.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SummaryReportTotalsBo {

    private BigDecimal markup;

    private BigDecimal rebate;

    private BigDecimal total;

    private BigDecimal monthly_adjustment;

}
