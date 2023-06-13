package org.example.agent.controller;

import org.example.agent.dto.OverviewDto;
import org.example.agent.service.impl.DashBoardServiceImpl;
import org.example.agent.vo.OverviewVo;
import org.example.common.base.CommResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashBoardController {

    @Autowired
    private DashBoardServiceImpl dashBoardService;

    @GetMapping("/dashboard")
    public CommResp dashboard(OverviewDto overviewDto){
        Long agent_id = Long.getLong("3");
        OverviewVo todayOverView = dashBoardService.getTodayOverView(overviewDto,agent_id);
        return CommResp.data(todayOverView);
    }
}
