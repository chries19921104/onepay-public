package org.example.agent.controller;

import org.example.agent.base.Result;
import org.example.agent.dto.SummaryDto;
import org.example.agent.service.SystemAgentsService;
import org.example.agent.service.SystemMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AgentController {

    @Autowired
    private SystemMerchantService merchantService;
    @Autowired
    private SystemAgentsService agentsService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/plans/summary")
    public Result planSummary(SummaryDto summaryDto) {
        return merchantService.loadPlanSummaryTable(summaryDto);
    }

    @GetMapping("/agent/simple")
    public Result agentSimple(){
        String token = request.getHeader("token");
        return agentsService.simple(token);
    }

    @GetMapping("/sh100/simple")
    public Result  merchantSimple(){
        String token=request.getHeader("token");
        return agentsService.merchantSimple(token);
    }

}
