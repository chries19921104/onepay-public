package org.example.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {

    private MessageUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final MessageSource messageSource = SpringUtil.getBean(MessageSource.class);

    public static String getLocale(String msg) {
        try {
            return messageSource.getMessage(msg, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msg;
        }
    }
}

