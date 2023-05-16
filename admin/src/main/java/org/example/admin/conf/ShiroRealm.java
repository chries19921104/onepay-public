package org.example.admin.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**授权，   查数据库，添加权限**/
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

    /**认证，   查数据库，检查账户是否可以登录**/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getPrincipal().toString();
        String pwd = token.getCredentials().toString();
        String password = new String((char[]) token.getCredentials());
        if (!"admin".equals(name) || !"admin".equals(password)) {
            throw new UnknownAccountException();
        }
        String realmName = getName();
        System.out.println("realmName:" + realmName);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(name, password, realmName);
        return authenticationInfo;
    }
}
