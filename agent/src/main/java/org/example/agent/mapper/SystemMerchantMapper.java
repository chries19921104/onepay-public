package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.agent.vo.AgentZoneMerchantVo;
import org.example.common.entity.SystemMerchant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SystemMerchantMapper extends BaseMapper<SystemMerchant> {

    public List<AgentZoneMerchantVo> getAgentZoneMerchantList(String currency,Long agent_id);
}

