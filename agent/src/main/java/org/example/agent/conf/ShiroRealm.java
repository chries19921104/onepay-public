package org.example.agent.conf;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SystemAgentsMapper agentsMapper;

    /**
     * 授权，   查数据库，添加权限
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        Set<String> realmNames = principals.getRealmNames();
        System.out.println(primaryPrincipal);
        System.out.println(realmNames);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("admin");
        return info;
    }


    /**
     * 认证，   查数据库，检查账户是否可以登录
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String name = upToken.getUsername();
        String password = new String((char[]) token.getCredentials());

        LambdaQueryWrapper<SystemAgents> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemAgents::getUsername, name)
                .eq(SystemAgents::getStatus,1);
        SystemAgents systemAgents = agentsMapper.selectOne(wrapper);
        if (Objects.isNull(systemAgents)) {
            return null;
        }

        if (!"admin".equals(name) || !"admin".equals(password)) {
            throw new UnknownAccountException();
        }
        String realmName = getName();
        System.out.println("realmName:" + realmName);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(name, password, realmName);
        return authenticationInfo;
    }


}
