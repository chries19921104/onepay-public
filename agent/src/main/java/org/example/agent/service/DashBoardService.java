package org.example.agent.service;

import org.example.agent.dto.OverviewDto;
import org.example.agent.vo.OverviewVo;

public interface DashBoardService {

    /**
     * 代理总览
     * @return
     */
    public OverviewVo getTodayOverView(OverviewDto overviewDto,Long agent_id);
}
