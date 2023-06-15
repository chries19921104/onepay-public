package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.dto.AgentDto;
import org.example.admin.dto.MonthlyAdjustmentsDto;
import org.example.admin.service.SystemMonthlyAdjustmentsService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemMonthlyAdjustments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "每月调整控制器")
@RestController
@RequestMapping("/api")
public class SystemMonthlyAdjustmentsController {

    @Autowired
    private SystemMonthlyAdjustmentsService systemMonthlyAdjustmentsService;


    //http://localhost:8091/api/ma100
    @ApiOperation(value = "代理-每月调整-条件查询")
    @GetMapping("/ma100")
//    @NoAuthorization
    public CommResp getAdjustmentsAll(MonthlyAdjustmentsDto monthlyAdjustmentsDto){

        return systemMonthlyAdjustmentsService.getAdjustmentsAll(monthlyAdjustmentsDto);
    }




}
