package org.example.agent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.mapper.AgentCommissionSettingsMapper;
import org.example.agent.service.AgentCommissionSettingsService;
import org.example.common.entity.SystemAgentCommissionSettings;
import org.springframework.stereotype.Service;

/**
 * 抽成设定(AgentCommissionSettings)表服务实现类
 *
 * @author makejava
 * @since 2023-06-08 13:46:47
 */
@Service("agentCommissionSettingsService")
public class AgentCommissionSettingsServiceImpl extends ServiceImpl<AgentCommissionSettingsMapper, SystemAgentCommissionSettings> implements AgentCommissionSettingsService {

}

