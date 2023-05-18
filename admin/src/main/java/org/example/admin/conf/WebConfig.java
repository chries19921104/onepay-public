package org.example.admin.conf;

import org.example.admin.conf.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${token.key}")
    private String tokenKey;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TokenInterceptor loginInterceptor = new TokenInterceptor(tokenKey);
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**"); //拦截请求的url前缀
        //.excludePathPatterns("/css/**","/images/**","/js/**");// 放行
    }
}

