package org.example.admin.controller;

import org.example.admin.service.SystemMerchantService;
import org.example.common.base.MerchantData;
import org.example.common.base.MerchantResp;
import org.example.common.dto.MerchantDto;
import org.example.common.vo.AgentsByCardGroupVo;
import org.example.common.vo.AgentsVo;
import org.example.common.vo.MerchantByAgentByGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
* system_merchant 控制器实现
* </p>
*
* @author asus
* @since 2023-05-16 14:27:59
*/
@RestController
@RequestMapping()
public class SystemMerchantController {

    @Autowired
    private SystemMerchantService systemMerchantService;

    //http://localhost:8088/api/sh100/simple?with=currency,agent_id&with=currency,PG100_ID（选择商户）
    @GetMapping("/sh100/simple")
    public List<MerchantByAgentByGroupVo> getMerchant(@RequestParam("with") List<String> with) {
        //查询出所有商户资料，相关表为system_merchant
        //返回数据[{SH100_ID: 151, name: "BTTHB14", code: "BTTHB14", currency: "THB", PG100_ID: 1},…]
        return systemMerchantService.getMerchant();
    }

    //http://localhost:8088/api/agent/simple(选择代理)
    @GetMapping("/agent/simple")
    public List<AgentsVo> getAgents() {
        //查询出所有代理信息，相关表为system_agents
        //返回数据[{id: 1, display_id: "System", username: "System", full_name: "System??\哈嚕", belong_id: null,…},…]
        return systemMerchantService.getAgents();
    }

    //http://localhost:8088/api/pg100/simple?with=currency,model(选择账户群组)
    @GetMapping("/pg100/simple")
    public List<AgentsByCardGroupVo> getGroup(String with) {
        //查询出所有的账户群组返回
        //返回的数据类型：{PG100_ID: 1, PG100_name: "DEV-THB", currency: "THB"}
        //里面牵涉表，system_bank_card_group
        if ("currency,model".equals(with)){
            return systemMerchantService.getGroup();
        }
        return null;
    }

    //http://localhost:8088/api/sh100?
    // SH100_ID[]=151&SH100_ID[]=150&
    // agent_id[]=none&agent_id[]=5&
    // currency=THB&
    // start_date=2020-05-05&
    // end_date=2023-05-17&
    // status[]=1&status[]=0&
    // not_allowed_types[]=1&not_allowed_types[]=2&
    // not_allowed_BK100_ID=&
    // rp=100&
    // page=1&
    // PG100_ID=1
    @GetMapping("/sh100")
    public List<MerchantResp> selectMerchant(@RequestParam("SH100_ID[]") List<Integer> merchantId,
                                             @RequestParam("agent_id[]") List<String> agentId,
                                             @RequestParam("currency") String currency,
                                             @RequestParam("start_date") String startDate,
                                             @RequestParam("end_date") String endDate,
                                             @RequestParam("status[]") List<Integer> status,
                                             @RequestParam("not_allowed_types[]") List<Integer> notAllowedTypes,
                                             @RequestParam("not_allowed_BK100_ID") String notAllowedBk100Id,
                                             @RequestParam("rp") Integer rp,
                                             @RequestParam("page") Integer page,
                                             @RequestParam("PG100_ID") Integer groupId) {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setMerchantId(merchantId);
        merchantDto.setAgentId(agentId);
        merchantDto.setCurrency(currency);
        merchantDto.setEndDate(endDate);
        merchantDto.setStartDate(startDate);
        merchantDto.setStatus(status);
        merchantDto.setNotAllowedTypes(notAllowedTypes);
        merchantDto.setNotAllowedBk100Id(notAllowedBk100Id);
        merchantDto.setRp(rp);
        merchantDto.setPage(page);
        merchantDto.setGroupId(groupId);

        //data:
        List<MerchantData> merchantData = systemMerchantService.selectData(merchantDto);
        //toals

//        return systemMerchantService.selectMerchant();
        return null;
    }
}
