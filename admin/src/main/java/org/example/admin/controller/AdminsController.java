package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.AdminsService;
import org.example.common.base.CommResp;
import org.example.common.dto.AdminsDTO;
import org.example.common.entity.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping("/aas")
public class AdminsController {

    @Autowired
    private AdminsService adminsService;


    @ApiOperation(value ="登录")
    @PostMapping("/login")
    public CommResp loginAuth(@RequestBody Admins admins) {
        return adminsService.login(admins);
    }
    @ApiOperation(value ="注册")
    @PostMapping("/register")
    public CommResp register(@RequestBody AdminsDTO adminsDTO) {
        return adminsService.register(adminsDTO);
    }
    @ApiOperation(value ="修改")
    @PostMapping("/adminupdate")
    public CommResp update(@RequestBody AdminsDTO adminsDTO) {
        return adminsService.update(adminsDTO);
    }

    @ApiOperation(value ="删除")
    @PostMapping("/admindelete")
    public CommResp delete(@RequestBody AdminsDTO adminsDTO) {
        return adminsService.delete(adminsDTO);
    }
}
