package org.example.common.base;

import lombok.Data;

import java.util.List;

@Data
public class MerchantResp {
    private int current_page;
    private List<MerchantData> data;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private String per_page;
    private String prev_page_url;
    private Totals subtotals;
    private int to;
    private int total;
    private Totals totals;
}
