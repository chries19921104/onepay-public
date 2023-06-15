package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.agent.vo.AgentZoneMerchantVo;
import org.example.common.entity.SystemMerchant;

import java.util.List;


/**
 * 商户资料(SystemMerchant)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-07 14:49:39
 */

@Mapper
public interface SystemMerchantMapper extends BaseMapper<SystemMerchant> {

    List<AgentZoneMerchantVo> getAgentZoneMerchantList(String currency,Long agent_id);
}

