package org.example.admin.controller;

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
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 菜单分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public CommResp listPage(Integer pageNum, Integer pageSize) {
        return systemMenuService.listPage(pageNum, pageSize);
    }

    /**
     * 菜单新增
     * @param systemMenu
     * @return
     */
    @PostMapping("/save")
    public CommResp create(@RequestBody SystemMenu systemMenu) {
        return systemMenuService.create(systemMenu);
    }

    /**
     * 菜单修改
     * @param systemMenu
     * @return
     */
    @PutMapping("/update")
    public CommResp update(@RequestBody SystemMenu systemMenu) {
        return systemMenuService.update(systemMenu);
    }

    /**
     * 菜单删除（逻辑删除）
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CommResp deleteById(@PathVariable Integer id) {
        return systemMenuService.deleteById(id);
    }

    /**
     * 查询单个菜单（编辑菜单回显）
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public CommResp getId(@PathVariable Integer id) {
        return systemMenuService.getId(id);
    }

    /**
     * 获取所有的父级名
     * @return
     */
    @GetMapping("/pidName")
    public CommResp getPidName() {
        return systemMenuService.getPidName();
    }

    /**
     * 获取父级下所有的子菜单
     * @param id
     * @return
     */
    @GetMapping("/get/list/{id}")
    public CommResp getIdName(@PathVariable Integer id) {
        return systemMenuService.getIdName(id);
    }
}
