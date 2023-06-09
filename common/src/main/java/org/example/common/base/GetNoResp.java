package org.example.common.base;

import org.example.common.utils.URLUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class GetNoResp {

    //常用分页查询无结果
    public static MerchantResp getNoMerchantResp(HttpServletRequest request, Integer rp,Totals totals) {
        MerchantResp merchantResp = new MerchantResp();
        merchantResp.setCurrent_page(1);
        merchantResp.setLast_page(1);
        merchantResp.setPer_page(rp+"");
        merchantResp.setTotal(0);
        merchantResp.setTotals(totals);
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=1");
        merchantResp.setPath(url);

        return merchantResp;
    }

    public static MerchantResp getNoResp(HttpServletRequest request, Integer rp) {
        MerchantResp merchantResp = new MerchantResp();
        merchantResp.setCurrent_page(1);
        merchantResp.setLast_page(1);
        merchantResp.setPer_page(rp+"");
        merchantResp.setTotal(0);
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=1");
        merchantResp.setPath(url);

        return merchantResp;
    }
}
