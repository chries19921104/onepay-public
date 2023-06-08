package org.example.common.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.example.common.utils.URLUtils;

import javax.servlet.http.HttpServletRequest;
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
    /**
     * 从哪页开始
     */
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
    /**
     * 小计
     */
    private Totals subtotals;
    /**
     * 总条数
     */
    private Integer to;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 总计
     */
    private Totals totals;

    /**
     * 获取封装数据
     * @param request
     * @param page
     * @return
     */
    public static  <T> MerchantResp getMerchantResp(HttpServletRequest request, Page<T> page) {
        int pageNum = (int) page.getCurrent();
        int pageSize = (int) page.getSize();
        MerchantResp merchantResp = new MerchantResp();
        merchantResp.setCurrent_page(pageNum);
        merchantResp.setData(page);
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        merchantResp.setFirst_page_url(url + "?page=1");
        // 获取总页数
        int last_page = 1;
        if (page.getTotal() > 0){
            last_page = page.getTotal() % pageSize == 0 ? (int)(page.getTotal() / pageSize) : (int)(page.getTotal() / pageSize) + 1;
        }
        merchantResp.setLast_page(last_page);
        merchantResp.setLast_page_url(url + "?page=" + last_page);
        merchantResp.setPath(url);
        merchantResp.setPer_page(String.valueOf(pageSize));

        if (page.getPages() > pageNum){
            merchantResp.setNext_page_url(url + "?page=" + (pageNum+1));
        }
        //保存其他信息
        if (page.getTotal() != 0){
            merchantResp.setFrom(pageNum);
        }
        merchantResp.setPrev_page_url(null);
        if (page.getTotal() != 0){
            merchantResp.setTo((int) page.getTotal());
        }
        merchantResp.setTotal((int) page.getTotal());

        return merchantResp;
    }
}
