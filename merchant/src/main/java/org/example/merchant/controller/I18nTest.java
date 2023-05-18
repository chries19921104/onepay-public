package org.example.merchant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.utils.MessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "国际化测试")
@RestController
@RequestMapping(value = "/test/i18n/")
public class I18nTest {


    @ApiOperation(value = "国际化测试")
    @GetMapping("/test")
    public String testInfo(HttpServletRequest request,String str) {
        return MessageUtil.getLocale(str);
    }
}
