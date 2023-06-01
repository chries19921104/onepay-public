package org.example.admin.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.common.dto.DashboardDto;
import org.example.common.dto.SystemDepositOrderDto;
import org.example.common.utils.URLUtils;
import org.example.common.vo.SystemDepositOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.example.common.entity.SystemDepositOrder;
import org.example.admin.service.SystemDepositOrderService;

import javax.servlet.http.HttpServletRequest;

/**
* <p>
* system_deposit_order 控制器实现
* </p>
*
* @author zhangmi
* @since 2023-05-17 19:16:15
*/
@RestController
@RequestMapping("/api")
public class SystemDepositOrderController {

    @Autowired
    private SystemDepositOrderService systemDepositOrderService;

    @GetMapping("/dashboard")
    public CommResp dashboard(DashboardDto dashboardDto) {
        return systemDepositOrderService.selectTxnModeByRegion(dashboardDto);
    }

    @GetMapping("/status/info")
    @NoAuthorization
    public CommResp info(){
      return systemDepositOrderService.infoText();
    }

    @GetMapping("system_deposit_order")
    @ApiOperation(value = "搜索")
    @NoAuthorization
    public MerchantResp search(SystemDepositOrderDto systemDepositOrderDto, HttpServletRequest request){
        Page<SystemDepositOrderVo> orderVoPage = systemDepositOrderService.searchByCondition(systemDepositOrderDto);

        // 封装数据
        MerchantResp merchantResp = getMerchantResp(request, orderVoPage);
        // 获取Totals
        Totals totals = getTotals(orderVoPage.getRecords());
        merchantResp.setSubtotals(totals);
        merchantResp.setTotals(totals);
        return merchantResp;
    }

    /**
     * 获取封装数据
     * @param request
     * @param page
     * @return
     */
    private <T> MerchantResp getMerchantResp(HttpServletRequest request, Page<T> page) {
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
            // TODO 这里是总条数还是总页数
            merchantResp.setTo((int) page.getTotal());
        }
        merchantResp.setTotal((int) page.getTotal());

        return merchantResp;
    }

    /**
     * 获取Totals
     * @param depositOrderVos
     * @return
     */
    private Totals getTotals(List<SystemDepositOrderVo> depositOrderVos) {
        Totals totals = new Totals();
        BigDecimal lossAmount = BigDecimal.ZERO;
        BigDecimal orderAmount = BigDecimal.ZERO;
        BigDecimal paidAmount = BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal requestAmount = BigDecimal.ZERO;
        //遍历depositOrderVos
        for (SystemDepositOrderVo orderVo : depositOrderVos) {
            // 相加
            lossAmount = lossAmount.add(orderVo.getLossAmount());
            orderAmount = orderAmount.add(orderVo.getOrderAmount());
            paidAmount = paidAmount.add(orderVo.getPaidAmount());
            rate = rate.add(orderVo.getRate());
            requestAmount = requestAmount.add(orderVo.getRequestAmount());
        }
        //将totals数据存储
        totals.setLossAmount(lossAmount);
        totals.setOrderAmount(orderAmount);
        totals.setPaidAmount(paidAmount);
        totals.setRate(rate);
        totals.setRequestAmount(requestAmount);
        return totals;
    }

}
