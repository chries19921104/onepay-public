package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.dto.AgentDto;
import org.example.admin.service.SystemAgentsService;
import org.example.admin.vo.AgentsVo;
import org.example.common.base.CommResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
* system_agents 控制器实现
* </p>
*
* @author Administrator
* @since 2023-05-25 10:48:39
*/
@Api(tags = "代理控制器")
@RestController
@RequestMapping("/api")
public class SystemAgentsController {

    @Autowired
    private SystemAgentsService systemAgentsService;

    //http://localhost:8088/api/agent/simple(选择代理)
    @ApiOperation(value = "有关代理的一些选项列表查询接口")
    @GetMapping("/agent/simple")
    public List<AgentsVo> getAgents() {
        //查询出所有代理信息，相关表为system_agents
        //返回数据[{id: 1, display_id: "System", username: "System", full_name: "System??\哈嚕", belong_id: null,…},…]
        return systemAgentsService.getAgents();
    }

    @ApiOperation(value = "代理-代理列表-条件查询")
    @GetMapping("/agent/all")
    @NoAuthorization
    public CommResp getAgentsAll(AgentDto agentDto){
        List<AgentsVo> agentsVoList=systemAgentsService.getAgentsAll(agentDto);

        return CommResp.data(agentsVoList);
    }

    @ApiOperation(value = "代理-代理列表-无身份查询")
    @GetMapping("/agent/noIdentity")
    @NoAuthorization
    public CommResp getAgentsNoIdentity(AgentDto agentDto){
        List<AgentsVo> agentsVoList=systemAgentsService.getAgentsNoIdentity(agentDto);

        return CommResp.data(agentsVoList);
    }

    @ApiOperation(value = "代理-代理列表-新增")
    @PostMapping("/agent/insert")
    public CommResp InsertAgent(AgentDto agentDto){

        boolean b=systemAgentsService.InsertAgent(agentDto);
        if(b){
            return CommResp.data("添加成功");
        }

        return CommResp.FAIL("新增失败");
    }




}
