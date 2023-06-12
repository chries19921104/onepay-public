package org.example.agent.controller;

import org.example.agent.base.Result;
import org.example.agent.dto.UserDto;
import org.example.agent.service.SystemAgentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private SystemAgentsService agentsService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        // 登录成功返回用户信息
        return agentsService.login(userDto);
    }
}
