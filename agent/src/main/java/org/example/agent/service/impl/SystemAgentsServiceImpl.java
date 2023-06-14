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
import org.example.agent.service.SystemMerchantService;
import org.example.agent.utils.BeanCopyUtils;
import org.example.agent.utils.TokenUtils;
import org.example.agent.vo.*;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private SystemMerchantService systemMerchantService;

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


        return Result.success(302, agentVo);
    }

    @Override
    public Result changePassword(ChangePasswordVo changePasswordVo, String token) {
//        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZ2VudElkIjoxMSwibmJmIjoxNjg2NzA4NDY4LCJleHAiOjE2ODY3MTIwNjgsImlhdCI6MTY4NjcwODQ2OCwidXNlcm5hbWUiOiJhZG1pbiJ9.M2FFgkZkw-11hSwAuRxKu345HjGuYk2xy6H8oX0aJDk";
        Long agentId = TokenUtils.getAgentId(token);
        SystemAgents agents = getById(agentId);
        if (!agents.getPassword().equals(SecureUtil.md5(changePasswordVo.getOldPassword()))) {
            return Result.failed(422, "旧密码不正确");
        }

        LambdaUpdateWrapper<SystemAgents> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SystemAgents::getAgentId, agentId)
                .set(SystemAgents::getPassword, SecureUtil.md5(changePasswordVo.getNewPassword()))
                .set(SystemAgents::getUpdater, agents.getFullName());
        update(null, wrapper);
        return Result.success("修改成功");
    }

    @Override
    public Result logout(String token) {
        Long agentId = TokenUtils.getAgentId(token);
        LambdaUpdateWrapper<SystemAgents> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SystemAgents::getAgentId, agentId)
                .set(SystemAgents::getStatus, 0);
        update(null, wrapper);
        return Result.success(302);
    }

    @Override
    public Result profile(String token) {
//        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZ2VudElkIjoxMSwibmJmIjoxNjg2NzA4NDY4LCJleHAiOjE2ODY3MTIwNjgsImlhdCI6MTY4NjcwODQ2OCwidXNlcm5hbWUiOiJhZG1pbiJ9.M2FFgkZkw-11hSwAuRxKu345HjGuYk2xy6H8oX0aJDk";
        Long agentId = TokenUtils.getAgentId(token);
        SystemAgents agents = getById(agentId);
        ProfileInfoVo profileInfoVo = BeanCopyUtils.copyBean(agents, ProfileInfoVo.class);
        List<String> currencys = systemCurrencyMapper.selectCurrency();

        if (profileInfoVo.getIdentity().equals(1)) {
            profileInfoVo.setCurrency(currencys);
            List<AgentProfileInfoVo> agentProfileInfoVos = agentsMapper.selectAgentfoVo();
            profileInfoVo.setCrews(agentProfileInfoVos);
            return Result.success(profileInfoVo);
        }

        AgentInfoVo agentInfoVo = BeanCopyUtils.copyBean(agents, AgentInfoVo.class);
        agentInfoVo.setCurrency(currencys);
        LambdaQueryWrapper<SystemAgents> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SystemAgents::getFullName)
                .eq(SystemAgents::getAgentId, agentId)
                .apply("belong_id = agent_id");
        List<Object> belongName = listObjs(wrapper);
        agentInfoVo.setBelongName(belongName);
        return Result.success(agentInfoVo);

    }

    @Override
    public Result simple(String token) {
//        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZ2VudElkIjoxMSwibmJmIjoxNjg2NzA4NDY4LCJleHAiOjE2ODY3MTIwNjgsImlhdCI6MTY4NjcwODQ2OCwidXNlcm5hbWUiOiJhZG1pbiJ9.M2FFgkZkw-11hSwAuRxKu345HjGuYk2xy6H8oX0aJDk";
        Long agentId = TokenUtils.getAgentId(token);
        SystemAgents agents = getById(agentId);
        LambdaQueryWrapper<SystemAgents> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SystemAgents::getAgentId, SystemAgents::getDisplayId, SystemAgents::getUsername, SystemAgents::getFullName);

        if (agents.getIdentity().equals(1)) {
            wrapper.eq(SystemAgents::getBelongId, agentId);
            List<Map<String, Object>> maps = listMaps(wrapper);
            return Result.success(maps);
        }

        wrapper.eq(SystemAgents::getAgentId, agentId);
        List<Map<String, Object>> maps = listMaps(wrapper);
        return Result.success(maps);
    }

    @Override
    public Result merchantSimple(String token) {
        Long agentId = TokenUtils.getAgentId(token);


        return null;
    }

}


