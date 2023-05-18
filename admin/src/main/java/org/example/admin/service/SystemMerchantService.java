package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.MerchantData;
import org.example.common.dto.MerchantDto;
import org.example.common.vo.AgentsByCardGroupVo;
import org.example.common.vo.AgentsVo;
import org.example.common.vo.MerchantByAgentByGroupVo;
import org.example.common.entity.SystemMerchant;

import java.util.List;

/**
* <p>
* system_merchant Service 接口
* </p>
*
* @author Administrator
* @since 2023-05-16 14:57:07
*/
public interface SystemMerchantService extends IService<SystemMerchant> {

    List<AgentsByCardGroupVo> getGroup();

    List<MerchantByAgentByGroupVo> getMerchant();

    List<AgentsVo> getAgents();

    List<MerchantData> selectData(MerchantDto merchantDto);
}