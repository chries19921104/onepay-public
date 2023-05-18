package org.example.merchant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.utils.MessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@Api(tags = "国际化测试")
@RestController
@RequestMapping(value = "/test/i18n")
public class I18nTest {

    // 固定的登录界面
    private static final List<String> list= Arrays
            .asList("MasterBackOffice","Password","login","Username");

    @ApiOperation(value = "国际化测试")
    @GetMapping("/test")
    public Map<String,String> testInfo() {
        Map<String,String> map=new HashMap<>();
        // 查询出需要国际化的值，返回固定格式给前端，后续用json
        for (String s : list) {
            map.put(s,MessageUtil.getLocale(s));
        }

        return map;
    }
}
