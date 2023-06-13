package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.dto.RebateSchemeDto;
import org.example.admin.service.SystemRebateSchemeService;
import org.example.common.base.CommResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "返点设置控制器")
@RestController
@RequestMapping("/api")
public class SystemRebateSchemeController {

    @Autowired
    private SystemRebateSchemeService systemRebateSchemeService;



    //http://localhost:8091/api/rp100
    @ApiOperation(value = "代理-返点设置-条件查询")
    @GetMapping("/rp100")
//    @NoAuthorization
    public CommResp getScheme(RebateSchemeDto rebateSchemeDto){

        return systemRebateSchemeService.getScheme(rebateSchemeDto);
    }



}
