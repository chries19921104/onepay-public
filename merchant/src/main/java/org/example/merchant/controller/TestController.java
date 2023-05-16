package org.example.merchant.controller;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("Test请求")
    public String  test(){
        System.out.println("createMsg");
        return "1";
    }




}
