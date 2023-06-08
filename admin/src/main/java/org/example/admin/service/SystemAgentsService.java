package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.AgentDto;
import org.example.common.entity.SystemAgents;
import org.example.admin.vo.AgentsVo;

import java.util.List;

/**
* <p>
* system_agents Service 接口
* </p>
*
* @author Administrator
* @since 2023-05-25 10:49:18
*/
public interface SystemAgentsService extends IService<SystemAgents> {

    /**
     * 选择代理
     * @return
     */
    List<AgentsVo> getAgents();

    //查询所有数据或条件查询
    List<AgentsVo> getAgentsAll(AgentDto agentDto);

    //查询无身份的数据
    List<AgentsVo> getAgentsNoIdentity(AgentDto agentDto);

    //代理-代理列表-新增
    boolean InsertAgent(AgentDto agentDto);
}