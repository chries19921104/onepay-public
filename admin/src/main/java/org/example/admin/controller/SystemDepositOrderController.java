package org.example.admin.controller;

import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.common.base.CommResp;
import org.example.admin.dto.DashboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.example.admin.service.SystemDepositOrderService;

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
      return   systemDepositOrderService.infoText();
    }



}
