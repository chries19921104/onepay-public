package org.example.agent.dto;

import lombok.Data;

import java.util.List;

@Data
public class SummaryReportDto {

    /**
     * id列表
     */
    private List<Long> SH100_ID;

    /**
     * 地区
     */
    private String currency;
    private Integer year;
    private Integer month;

    /**
     * 返回数据条数（页面大小）
     */
    private Integer rp;

    /**
     * 页数
     */
    private Integer page;
}
