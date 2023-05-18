package org.example.common.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class I18nUtil {

    // 固定的登录界面
    private static final List<String> list= Arrays
            .asList("MasterBackOffice","Password","login","Username");

    public Map<String,String> i18n(){

        Map<String,String> map=new HashMap<>();
        // 查询出需要国际化的值，返回固定格式给前端，后续用json
        for (String s : list) {
            map.put(s,MessageUtil.getLocale(s));
        }

        return map;
    }
}
