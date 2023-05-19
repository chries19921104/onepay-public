package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.SystemMenuService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* <p>
* system_menu 控制器实现
* </p>
*
* @author Administrator
* @since 2023-05-11 16:34:04
*/
@RestController
@RequestMapping("/system/menu")
@Api(tags = "管理员菜单控制器")
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    @ApiOperation(value = "菜单分页列表")
    @GetMapping("/list")
    public CommResp listPage(Integer pageNum, Integer pageSize) {
        return systemMenuService.listPage(pageNum, pageSize);
    }

    @ApiOperation(value = "菜单新增")
    @PostMapping("/save")
    public CommResp create(@RequestBody SystemMenu systemMenu) {
        return systemMenuService.create(systemMenu);
    }

    @ApiOperation(value = "菜单修改")
    @PutMapping("/update")
    public CommResp update(@RequestBody SystemMenu systemMenu) {
        return systemMenuService.update(systemMenu);
    }

    @ApiOperation(value = "菜单删除（逻辑删除）")
    @DeleteMapping("/{id}")
    public CommResp deleteById(@PathVariable Integer id) {
        return systemMenuService.deleteById(id);
    }

    @ApiOperation(value = "查询单个菜单（编辑菜单回显）")
    @GetMapping("/get/{id}")
    public CommResp getId(@PathVariable Integer id) {
        return systemMenuService.getId(id);
    }

    @ApiOperation(value = "获取所有的父级名")
    @GetMapping("/pidName")
    public CommResp getPidName() {
        return systemMenuService.getPidName();
    }

    @ApiOperation(value = "获取父级下所有的子菜单")
    @GetMapping("/get/list/{id}")
    public CommResp getIdName(@PathVariable Integer id) {
        return systemMenuService.getIdName(id);
    }
}
