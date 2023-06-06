package org.example.admin.controller;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.example.admin.vo.TestVo;
import org.example.common.base.CommResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api("测试")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("Test请求")
    public String  test(@Validated TestVo vo){
        System.out.println(JSONUtil.toJsonStr(vo));
        return "1";
    }

    @GetMapping("/test2")
    @ApiOperation("Test2请求")
    @RequiresRoles("admin1")
    public String  test2(){

        return "1";
    }

    @GetMapping("/test3")
    @ApiOperation("Test3请求")
    public String  test3(){
//        int i = 1 / 0;
        log.info("test3");
        return "test3";
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public CommResp login(String username, String password) {
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        if (username == null || password == null) {
            return CommResp.FAIL("没有授权");
        }
        AuthenticationToken token = new UsernamePasswordToken(username, password);
        try {
            final Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            System.out.println(subject.getSession().getId().toString());

            subject.getSession().setAttribute("username", username);
        } catch (Exception e) {
            e.printStackTrace();
            return CommResp.FAIL();
        }
        return CommResp.SUCCEE();
    }




}
