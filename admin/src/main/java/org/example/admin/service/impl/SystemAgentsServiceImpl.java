package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemAgentsMapper;
import org.example.admin.service.SystemAgentsService;
import org.example.common.entity.SystemAgents;
import org.example.common.vo.AgentsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* system_agents Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-05-25 10:49:41
*/
@Service
@Transactional
@Slf4j
public class SystemAgentsServiceImpl extends ServiceImpl<SystemAgentsMapper, SystemAgents> implements SystemAgentsService {

    @Autowired
    private SystemAgentsMapper systemAgentsMapper;

    /**
     * 选择代理
     * @return
     */
    @Override
    public List<AgentsVo> getAgents() {
        List<SystemAgents> systemAgents = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                .eq(SystemAgents::getStatus, 1));
        List<AgentsVo> collect = systemAgents.stream().map(iter -> {
            AgentsVo agentsVo = new AgentsVo();
            BeanUtils.copyProperties(iter, agentsVo);
            return agentsVo;
        }).collect(Collectors.toList());
        return collect;
    }

}