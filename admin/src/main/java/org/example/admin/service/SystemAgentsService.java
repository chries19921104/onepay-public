package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.AgentDto;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemAgents;
import org.example.admin.vo.AgentsVo;

import java.util.List;
import java.util.Map;

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
    CommResp getAgentsAll(AgentDto agentDto);

    //查询无身份的数据
    CommResp getAgentsNoIdentity(AgentDto agentDto);

    //代理-代理列表-新增
    CommResp InsertAgent(AgentDto agentDto);

    //代理-代理列表-详情
    CommResp getAgentData(Long id);

    //代理-代理列表-详情-编辑
    CommResp updateAgent(Long id,AgentDto agentDto);

    //代理-代理列表-详情-重置密码
    CommResp updateAgentPassword(Long id);

    CommResp getAgentDataSelect(Long id);
}