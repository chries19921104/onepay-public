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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    //http://localhost:8088/api/agent/all
    @ApiOperation(value = "代理-代理列表-条件查询")
    @GetMapping("/agent")
    @NoAuthorization
    public CommResp getAgentsAll(AgentDto agentDto){

        return systemAgentsService.getAgentsAll(agentDto);
    }

    //http://localhost:8088/api/agent/noIdentity
    @ApiOperation(value = "代理-代理列表-无身份查询")
    @GetMapping("/agent/noIdentity")
    @NoAuthorization
    public CommResp getAgentsNoIdentity(AgentDto agentDto){

        return systemAgentsService.getAgentsNoIdentity(agentDto);
    }

    //http://localhost:8088/api/agent/insert
    @ApiOperation(value = "代理-代理列表-新增")
    @PostMapping("/agent/insert")
    public CommResp InsertAgent(AgentDto agentDto){

        return systemAgentsService.InsertAgent(agentDto);

    }

    //http://localhost:8088/api/agent/{id}
    @ApiOperation(value = "代理-代理列表-详情")
    @GetMapping("/agent/{id}")
    @NoAuthorization
    public CommResp getAgentData(@PathVariable("id")Long id){

        return systemAgentsService.getAgentData(id);
    }

    //http://localhost:8088/api/agent/{id}
    @ApiOperation(value = "代理-代理列表-详情-编辑")
    @PutMapping("/agent/{id}/")
    public CommResp updateAgent(@PathVariable("id")Long id, @RequestBody AgentDto agentDto){

        return systemAgentsService.updateAgent(id,agentDto);
    }

    //http://localhost:8088/api/agent/{id}/password/reset
    @ApiOperation(value = "代理-代理列表-详情-重置密码")
    @PutMapping("/agent/{id}/password/reset")
    @NoAuthorization
    public CommResp updateAgentPassword(@PathVariable("id")Long id){

        return systemAgentsService.updateAgentPassword(id);
    }

    //http://localhost:8088/api/agent/{id}/plans/summary
    @ApiOperation(value = "代理-代理列表-详情-搜索")
    @PutMapping("/agent/{id}/plans/summary")
    @NoAuthorization
    public CommResp getAgentDataSelect(@PathVariable("id")Long id){

        return systemAgentsService.getAgentDataSelect(id);
    }





}
