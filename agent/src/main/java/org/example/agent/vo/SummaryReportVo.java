package org.example.agent.vo;

import lombok.Data;
import org.example.agent.bo.SummaryReportDataBo;
import org.example.agent.bo.TotalBalanceBo;

@Data
public class SummaryReportVo {

    private SummaryReportDataBo data;

    private TotalBalanceBo totalBalance;

}
