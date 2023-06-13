package org.example.agent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.agent.base.Result;
import org.example.agent.dto.UserDto;
import org.example.agent.vo.ChangePasswordVo;
import org.example.common.entity.SystemAgents;


/**
 * 代理表(SystemAgents)表服务接口
 *
 * @author makejava
 * @since 2023-06-07 15:31:27
 */
public interface SystemAgentsService extends IService<SystemAgents> {

    Result login(UserDto userDto);

    Result changePassword(ChangePasswordVo changePasswordVo, String token);

    Result logout(String token);

    Result profile(String token);
}

