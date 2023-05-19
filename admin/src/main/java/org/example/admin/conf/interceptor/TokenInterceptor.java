package org.example.admin.conf.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.common.utils.BaseContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    //由于注册器那边是new的新对象，所以拦截器这边不能注册到IOC容器里面
    //这边采用构造方法的形式传参给注册器，由注册器注入到IOC容器里面
    private String tokenKey;

    public TokenInterceptor(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            //handler是object类型不能直接调用，如果不强转可以用反射handler.getClass().getMethod()
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //判断有没有注解
            NoAuthorization noAuthorization = handlerMethod.getMethod().getAnnotation(NoAuthorization.class);
            if (noAuthorization != null) {
                //如果有该注解，证明该方法不需要token验证；
                return true;
            }
        }

        //获取token 前端起的名字token
        String token = request.getHeader("token");

        //如果token不为空，进行token验证
        if (StrUtil.isNotEmpty(token)) {
            // 证token   validate(long leeway)验证token是否有效 0容忍
            boolean pass = JWT.of(token).setKey(tokenKey.getBytes()).validate(0);

            if (!pass) {
                //验证失败，返回401
                response.setStatus(401);
                return false;
            } else {
                //解析token
                JWT jwt = JWTUtil.parseToken(token);
                JWTPayload payload = jwt.getPayload();
                Long id =Long.valueOf(payload.getClaim("id").toString());
                BaseContext.setCurrent(id);
                return true;
            }
        }

        //没有token，直接返回401
        response.setStatus(401);
        return false;

    }
}

