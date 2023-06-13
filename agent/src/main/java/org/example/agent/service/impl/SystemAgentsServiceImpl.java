package org.example.agent.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.base.Result;
import org.example.agent.dto.UserDto;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.mapper.SystemCurrencyMapper;
import org.example.agent.service.SystemAgentsService;
import org.example.agent.utils.BeanCopyUtils;
import org.example.agent.utils.TokenUtils;
import org.example.agent.vo.*;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private SystemCurrencyMapper systemCurrencyMapper;

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

    @Override
    public Result changePassword(ChangePasswordVo changePasswordVo, String token) {
        Long agentId = TokenUtils.getAgentId(token);
        SystemAgents agents = getById(agentId);
        if (agents.getPassword().equals(SecureUtil.md5(changePasswordVo.getOldPassword()))) {
            return Result.failed("旧密码不正确");
        }
        if (changePasswordVo.getNewPassword().equals(changePasswordVo.getOldPassword())) {
            return Result.failed("新密码与旧密码必须不同");
        }
        if (changePasswordVo.getConfirmPassword().equals(changePasswordVo.getNewPassword())) {
            return Result.failed("请与新密码保持一致");
        }

        LambdaUpdateWrapper<SystemAgents> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SystemAgents::getAgentId, agentId)
                .set(SystemAgents::getPassword, changePasswordVo.getNewPassword())
                .set(SystemAgents::getUpdater, agents.getFullName());
        update(null, wrapper);
        return Result.success("修改成功");
    }

    @Override
    public Result logout(String token) {
//        Long agentId = TokenUtils.getAgentId(token);
//        LambdaUpdateWrapper<SystemAgents> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(SystemAgents::getAgentId, agentId)
//                .set(SystemAgents::getStatus, 0);
//        update(null, wrapper);
        return Result.success();
    }

    @Override
    public Result profile(String token) {
        Long agentId = TokenUtils.getAgentId("token");
        SystemAgents agents = getById(agentId);
        ProfileInfoVo profileInfoVo = BeanCopyUtils.copyBean(agents, ProfileInfoVo.class);

        if (profileInfoVo.getIdentity().equals(1)) {
            List<String> currencys = systemCurrencyMapper.selectCurrency();
            profileInfoVo.setCurrency(currencys);
            List<AgentProfileInfoVo> agentProfileInfoVos = agentsMapper.selectAgentfoVo(agents.getAgentId(), agents.getBelongId());
            profileInfoVo.setCrews(agentProfileInfoVos);
            return Result.success(profileInfoVo);
        }

        AgentInfoVo agentInfoVo = BeanCopyUtils.copyBean(agents, AgentInfoVo.class);
        LambdaQueryWrapper<SystemAgents> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemAgents::getAgentId,agentId)
                .apply("`belong_id` = `agent_Id'");
        List<SystemAgents> list = list(wrapper);
        return null;

    }

}


