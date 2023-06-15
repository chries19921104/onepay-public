package org.example.agent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.agent.dto.SummaryDto;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.service.SystemAgentsService;
import org.example.agent.service.SystemMerchantService;
import org.example.agent.utils.BeanCopyUtils;
import org.example.agent.vo.PlanSummaryVo;
import org.example.agent.vo.SummaryVo;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemAgents;
import org.example.common.entity.SystemMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class agentController {
    @Autowired
    private SystemAgentsService agentsService;
    @Autowired
    private SystemMerchantService merchantService;
    @Autowired
    private SystemAgentsMapper systemAgentsMapper;

    @GetMapping("/plans/summary")
    public CommResp planSummary(SummaryDto summaryDto) {
            System.out.println("111");
        LambdaQueryWrapper<SystemMerchant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(!StringUtils.isEmpty(summaryDto.getCurrency()), SystemMerchant::getCurrency, summaryDto.getCurrency());
        wrapper.in(SystemMerchant::getMerchantId, summaryDto.getSH100ID());
        wrapper.eq(SystemMerchant::getAgentId, summaryDto.getAgentId());

        List<SystemMerchant> merchants = merchantService.list(wrapper);
        LambdaQueryWrapper<SystemAgents> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemAgents::getStatus,1);
        lambdaQueryWrapper.eq(SystemAgents::getAgentId,summaryDto.getAgentId());
        SystemAgents agents = systemAgentsMapper.selectOne(lambdaQueryWrapper);
        PlanSummaryVo planSummaryVo = BeanCopyUtils.copyBean(agents, PlanSummaryVo.class);

        PlanSummaryVo planSummary = BeanCopyUtils.copyBean(planSummaryVo, PlanSummaryVo.class);
        List<SummaryVo> summaryVos = BeanCopyUtils.copyBeanList(merchants, SummaryVo.class);
        List<SummaryVo> summary = BeanCopyUtils.copyBeanList(summaryVos, SummaryVo.class);
        planSummary.setPlanSummary(summary);
        return CommResp.data(planSummary);

    }
}
