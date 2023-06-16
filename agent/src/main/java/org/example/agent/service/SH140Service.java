package org.example.agent.service;

import org.example.agent.dto.DailyReportDto;
import org.example.agent.dto.OverviewDto;
import org.example.agent.dto.SummaryReportDto;
import org.example.agent.vo.*;
import org.example.common.entity.SystemAgents;

import java.util.List;

public interface SH140Service {

    /**
     * 获取代理指定商户、时间段的日报表
     * @param agent
     * @param dailyReportDto
     * @return
     */
    public DailyReportVo getDailyReportList(SystemAgents agent, DailyReportDto dailyReportDto);

    public DailyBaseInfoVo getDailyBaseInfo(Integer identity,Long belongId);

    /**
     * 获取代理指定地区的商户列表
     * @param currency
     * @param agent_id
     * @return
     */
    public List<AgentZoneMerchantVo> getAgentZoneMerchantList(String currency,Long agent_id);

    /**
     * 代理总体报表
     * @param summaryReportDto
     * @return
     */
    public SummaryReportVo getSummaryReport(SummaryReportDto summaryReportDto);



}
