package org.example.common.base;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用于常用商户查询返回数据类")
public class MerchantResp {
    /**
     * 当前页
     */
    private Integer current_page;
    /**
     * 数据
     */
    private Object data;
    /**
     * 第一页网址
     */
    private String first_page_url;
    private Integer from;
    /**
     * 最后一页
     */
    private Integer last_page;
    /**
     * 最后一页网址
     */
    private String last_page_url;
    /**
     * 下一页网址
     */
    private String next_page_url;
    /**
     * 网址不加参数
     */
    private String path;
    /**
     * 显示条数
     */
    private String per_page;
    private String prev_page_url;
    private Totals subtotals;
    private Integer to;
    /**
     * 总条数
     */
    private Integer total;
    private Totals totals;
}
