package org.example.agent.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.agent.dto.DailyBaseInfoDto;
import org.example.agent.dto.DailyReportDto;
import org.example.agent.dto.SummaryReportDto;
import org.example.agent.dto.ZoneMerchantDto;
import org.example.agent.service.impl.SH140ServiceImpl;
import org.example.agent.vo.AgentZoneMerchantVo;
import org.example.agent.vo.DailyBaseInfoVo;
import org.example.agent.vo.DailyReportVo;
import org.example.agent.vo.SummaryReportVo;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(tags = "代理报表控制器")
@RequestMapping("/sh140")
public class SH140Controller {

    @Autowired
    private SH140ServiceImpl sh140Service;


    @ApiOperation(value = "获取日报表")
    @GetMapping("/dailyReport")
    public CommResp dailyReport(DailyReportDto dailyReportDto){
        DailyReportVo dailyReportVo = sh140Service.getDailyReportList(new SystemAgents(),dailyReportDto);
        System.out.println(dailyReportVo);
        return CommResp.data(dailyReportVo);
    }

    @ApiOperation(value = "获取代理收入总表")
    @GetMapping("/summaryReport")
    public CommResp summaryReport(SummaryReportDto summaryReportDto){
        SummaryReportVo summaryReportVo = sh140Service.getSummaryReport(summaryReportDto);
        return CommResp.data(summaryReportVo);
    }

    @ApiOperation(value = "获取日报表页面信息")
    @GetMapping("/dailyBaseInfo")
    public CommResp getDailyBaseInf(DailyBaseInfoDto dailyBaseInfoDto){
        DailyBaseInfoVo dailyBaseInfo = sh140Service.getDailyBaseInfo(dailyBaseInfoDto.getIdentity(), dailyBaseInfoDto.getBelong_id());
        if(dailyBaseInfo != null) return CommResp.data(dailyBaseInfo);
        else return CommResp.FAIL();
    }

    @ApiOperation(value = "更具地区获取商户")
    @GetMapping("/zonemerchant")
    public CommResp getZoneMerchants(ZoneMerchantDto zoneMerchantDto){
        List<AgentZoneMerchantVo> merchangtList = sh140Service.getAgentZoneMerchantList(zoneMerchantDto.getCurrency(),zoneMerchantDto.getAgent_id());
        return CommResp.data(merchangtList);
    }


}
