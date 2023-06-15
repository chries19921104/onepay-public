package org.example.agent.utils;

import cn.hutool.jwt.JWTUtil;

public class TokenUtils {
    public static Long getAgentId(String token) {
        return Long.valueOf(JWTUtil.parseToken(token).getPayload().getClaim("agentId").toString());
    }

    public static String getUsername(String token) {
        return JWTUtil.parseToken(token).getPayload().getClaim("username").toString();
    }
}
