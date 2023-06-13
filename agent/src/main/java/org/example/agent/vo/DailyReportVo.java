package org.example.agent.vo;

import lombok.Data;
import org.example.agent.bo.TotalBalanceBo;
import org.example.agent.po.DailyReportPo;
import java.util.List;


/**
 *
 */
@Data
public class DailyReportVo {
    private List<DailyReportPo> data;
    private TotalBalanceBo totals;

}
