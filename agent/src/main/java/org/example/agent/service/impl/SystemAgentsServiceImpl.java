package org.example.agent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.example.agent.base.Result;
import org.example.agent.dto.UserDto;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.service.SystemAgentsService;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 代理表(SystemAgents)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 15:31:27
 */
@Service("systemAgentsService")
public class SystemAgentsServiceImpl extends ServiceImpl<SystemAgentsMapper, SystemAgents> implements SystemAgentsService {

    @Autowired
    private SystemAgentsMapper agentsMapper;


    @Override
    public Result login(UserDto userDto) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        try {
            subject.login(token);
            return Result.success("登录成功");
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isEmpty(e.getMessage())) {
                msg = e.getMessage();
            }

            return Result.failed(msg);
        }
    }
}

