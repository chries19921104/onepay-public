package org.example.agent.controller;

import org.example.agent.base.Result;
import org.example.agent.dto.UserDto;
import org.example.agent.service.SystemAgentsService;
import org.example.agent.vo.ChangePasswordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private SystemAgentsService agentsService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        // 登录成功返回用户信息
        return agentsService.login(userDto);
    }

    @PutMapping("/api/password/change")
    public Result changePassword(@RequestBody ChangePasswordVo changePasswordVo) {
        String token = request.getHeader("token");
        return agentsService.changePassword(changePasswordVo, token);
    }
    @PostMapping("/logout")
    public Result logout(){
        String token = request.getHeader("token");
        return agentsService.logout(token);
    }
    @GetMapping("/UserSetting/Profile")
    public Result profile(){
        String token = request.getHeader("token");
        return agentsService.profile(token);
    }

}
