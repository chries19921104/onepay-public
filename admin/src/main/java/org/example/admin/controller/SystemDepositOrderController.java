package org.example.admin.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.common.dto.DashboardDto;
import org.example.common.dto.SystemDepositOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
        return systemDepositOrderService.searchByCondition(systemDepositOrderDto, request);
    }


}
