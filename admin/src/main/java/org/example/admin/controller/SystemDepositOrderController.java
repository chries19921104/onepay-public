package org.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

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
@RequestMapping("/system/deposit/order")
public class SystemDepositOrderController {

    @Autowired
    private SystemDepositOrderService systemDepositOrderService;

    @PostMapping
    public String create(@RequestBody SystemDepositOrder systemDepositOrder) {
        systemDepositOrderService.save(systemDepositOrder);
        return "OK";
    }

    @PutMapping
    public String updateById(@RequestBody SystemDepositOrder systemDepositOrder) {
        systemDepositOrderService.updateById(systemDepositOrder);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return systemDepositOrderService.removeById(id);
    }

    @GetMapping("/get/{id}")
    public SystemDepositOrder getById(@PathVariable Serializable id) {
        return systemDepositOrderService.getById(id);
    }
}
