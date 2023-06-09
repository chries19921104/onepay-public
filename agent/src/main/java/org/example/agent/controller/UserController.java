package org.example.agent.controller;

import org.example.agent.base.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @PostMapping("/login")
    public Result login(String username,String password){



        return null;
    }
}
