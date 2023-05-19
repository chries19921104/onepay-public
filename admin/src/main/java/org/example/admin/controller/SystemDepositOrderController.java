package org.example.admin.controller;

import cn.hutool.core.date.DateUtil;
import org.example.common.base.CommResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.example.common.entity.SystemDepositOrder;
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
    public CommResp create(@RequestParam String currency) {
        return systemDepositOrderService.selectTxnModeByRegion(currency);
    }


}
