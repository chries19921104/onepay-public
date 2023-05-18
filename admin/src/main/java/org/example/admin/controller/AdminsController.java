package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.AdminsService;
import org.example.common.base.CommResp;
import org.example.common.dto.AdminsDTO;
import org.example.common.entity.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
* <p>
* admins 控制器实现
* </p>
*
* @author zhangmi
* @since 2023-05-11 16:44:33
*/
@Api(tags = "管理员登录")
@RestController
@RequestMapping()
public class AdminsController {

    @Autowired
    private AdminsService adminsService;
    @Autowired
    private HttpServletRequest request;


    @ApiOperation(value ="登录")
    @PostMapping("/login")
    public CommResp loginAuth(@RequestParam("username") String username,@RequestParam("password") String password) {
        Admins admins=new Admins();
        admins.setUsername(username);
        admins.setPassword(password);
        return adminsService.login(admins);
    }
    @ApiOperation(value ="注册")
    @PostMapping("/api/admin")
    public CommResp register(@RequestBody AdminsDTO adminsDTO) {
        return adminsService.register(adminsDTO);
    }
    @ApiOperation(value ="修改")
    @PostMapping("/api/adminupdate")
    public CommResp update(@RequestBody AdminsDTO adminsDTO) {
        String token = request.getHeader("token");
        adminsDTO.setToken(token);
        return adminsService.update(adminsDTO);
    }

    @ApiOperation(value ="删除")
    @PostMapping("/api/admindelete")
    public CommResp delete(@RequestBody AdminsDTO adminsDTO) {
        String token = request.getHeader("token");
        adminsDTO.setToken(token);
        return adminsService.delete(adminsDTO);
    }
    @ApiOperation(value ="登出")
    @GetMapping("/logout")
    public CommResp logout(){
        boolean status = adminsService.update().eq("status", 0).update();
        return CommResp.data(status);
    }

}
