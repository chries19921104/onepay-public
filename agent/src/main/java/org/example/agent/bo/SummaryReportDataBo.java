package org.example.agent.bo;

import lombok.Data;
import org.example.agent.po.SummaryReportDataPo;

import java.util.List;

@Data
public class SummaryReportDataBo {

    private Integer per_page;
    private Integer current_page;
    private Integer last_page;

    private String first_page_url;
    private String last_page_url;
    private String prev_page_url;
    private String next_page_url;

    private String path;
    private Integer from;
    private Integer to;
    private Integer total;

    private List<SummaryReportDataPo> data;
    private SummaryReportTotalsBo subtotalas;
    private SummaryReportTotalsBo totals;

}
