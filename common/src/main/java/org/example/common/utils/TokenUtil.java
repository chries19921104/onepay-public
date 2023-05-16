package org.example.common.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

public class TokenUtil {
    /**
     * 创建token
     * @param param 盐
     * @return
     */
    public static String generateToken(String param) {
        UUID uuid = UUID.fastUUID();
        String md5 = SecureUtil.md5(uuid + param);
        return md5;
    }
}
