package org.example.common.base;

import lombok.Data;
import org.example.common.vo.MerchantByBrandVo;

import java.util.List;

@Data
public class MerchantByBrandResp {
    private Integer current_page;
    private List<MerchantByBrandVo> data;
    private String first_page_url;
    private Integer from;
    private Integer last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private String per_page;
    private String prev_page_url;
    private Integer to;
    private Integer total;
}
