package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.entity.SystemAgents;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SystemAgentsMapper extends BaseMapper<SystemAgents> {
}
