package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.agent.vo.AgentProfileInfoVo;
import org.example.agent.vo.PlanSummaryVo;
import org.example.common.entity.SystemAgents;

import java.util.List;


/**
 * 代理表(SystemAgents)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-07 15:31:27
 */
@Mapper
public interface SystemAgentsMapper extends BaseMapper<SystemAgents> {

    PlanSummaryVo info(Long agentId);

    List<AgentProfileInfoVo> selectAgentfoVo();
}
