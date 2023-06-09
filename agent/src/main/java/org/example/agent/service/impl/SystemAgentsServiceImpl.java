package org.example.agent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.entity.SystemAgents;
import org.example.topagent.mapper.SystemAgentsMapper;
import org.example.topagent.service.SystemAgentsService;
import org.springframework.stereotype.Service;

/**
 * 代理表(SystemAgents)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 15:31:27
 */
@Service("systemAgentsService")
public class SystemAgentsServiceImpl extends ServiceImpl<SystemAgentsMapper, SystemAgents> implements SystemAgentsService {

}
