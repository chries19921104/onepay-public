package org.example.agent.controller;

import org.example.agent.base.Result;
import org.example.agent.dto.SummaryDto;
import org.example.agent.service.SystemMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AgentController {

    @Autowired
    private SystemMerchantService merchantService;

    @GetMapping("/plans/summary")
    public Result planSummary(SummaryDto summaryDto) {
        return merchantService.loadPlanSummaryTable(summaryDto);
    }

}
