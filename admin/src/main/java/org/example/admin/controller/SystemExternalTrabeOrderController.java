package org.example.admin.controller;

import org.example.admin.dto.SystemExternalTrabeOrderDto;
import org.example.admin.service.SystemExternalTrabeOrderService;
import org.example.common.base.MerchantResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SystemExternalTrabeOrderController {

    @Autowired
    private SystemExternalTrabeOrderService externalTrabeOrderService;

    @GetMapping("/et100")
    public MerchantResp getAll(SystemExternalTrabeOrderDto dto ){
        MerchantResp all = externalTrabeOrderService.getAll(dto);
        return all;
    }
}
