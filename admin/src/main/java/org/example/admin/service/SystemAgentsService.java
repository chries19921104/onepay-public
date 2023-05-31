package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.entity.SystemAgents;
import org.example.common.vo.AgentsVo;

import java.util.List;

/**
* <p>
* system_agents Service 接口
* </p>
*
* @author Administrator
* @since 2023-05-25 10:49:18
*/
public interface SystemAgentsService extends IService<SystemAgents> {

    /**
     * 选择代理
     * @return
     */
    List<AgentsVo> getAgents();

}