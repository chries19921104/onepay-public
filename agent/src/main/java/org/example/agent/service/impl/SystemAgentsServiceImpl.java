package org.example.agent.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.base.Result;
import org.example.agent.dto.UserDto;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.service.SystemAgentsService;
import org.example.agent.utils.BeanCopyUtils;
import org.example.agent.vo.AgentVo;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * 代理表(SystemAgents)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 15:31:27
 */
@Service("systemAgentsService")
public class SystemAgentsServiceImpl extends ServiceImpl<SystemAgentsMapper, SystemAgents> implements SystemAgentsService {

    @Value("${token.key}")
    private String key;
    @Autowired
    private SystemAgentsMapper agentsMapper;


    @Override
    public Result login(UserDto userDto) {
        LambdaQueryWrapper<SystemAgents> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemAgents::getUsername, userDto.getUsername())
                .eq(SystemAgents::getPassword, SecureUtil.md5(userDto.getPassword()))
                .eq(SystemAgents::getStatus, 1);
        SystemAgents systemAgents = agentsMapper.selectOne(wrapper);

        if (Objects.isNull(systemAgents)) {
            return Result.failed("用户或密码错误");
        }
        //获取jwt
        HashMap<String, Object> payload = new HashMap<>();
        //
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 60);
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("agentId", systemAgents.getAgentId());
        payload.put("username", systemAgents.getUsername());
        String token = JWTUtil.createToken(payload, key.getBytes());

        AgentVo agentVo = BeanCopyUtils.copyBean(systemAgents, AgentVo.class);
        agentVo.setToken(token);


        return Result.success(agentVo);
    }
}


