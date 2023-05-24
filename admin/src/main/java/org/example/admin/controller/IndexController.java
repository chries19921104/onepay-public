package org.example.admin.controller;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.example.common.base.CommResp;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class IndexController {

/*    @RequestMapping("/login")
    @ResponseBody
    public CommResp login(HttpServletRequest request, HttpServletResponse response) {
        return CommResp.FAIL("unauthorized >  401 ");
    }*/
    @GetMapping("/login")
    public String login11(){
        return "login";
    }


}
