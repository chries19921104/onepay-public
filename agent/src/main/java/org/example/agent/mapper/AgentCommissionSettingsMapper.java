package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.agent.vo.CurrentRebateVo;
import org.example.common.entity.SystemAgentCommissionSettings;

import java.util.List;


/**
 * 抽成设定(AgentCommissionSettings)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-08 12:00:35
 */
@Mapper
public interface AgentCommissionSettingsMapper extends BaseMapper<SystemAgentCommissionSettings> {


    List<CurrentRebateVo> currentRebate(@Param("agentId") Long agentId, @Param("currency") String currency);
}

