package org.example.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用于常用商户查询返回数据类")
public class MerchantResp {
    private Integer current_page;
    private Object data;
    private String first_page_url;
    private Integer from;
    private Integer last_page;
    private String last_page_url;
    private String next_page_url;
    private String path;
    private String per_page;
    private String prev_page_url;
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private Totals subtotals;
    private Integer to;
    private Integer total;
    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    private Totals totals;
}
