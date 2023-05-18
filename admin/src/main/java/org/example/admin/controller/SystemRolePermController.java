package org.example.admin.controller;



import org.example.admin.dto.RolePermDto;
import org.example.admin.service.SystemRolePermService;
import org.example.admin.vo.RolePermVo;
import org.example.common.base.CommResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class SystemRolePermController {
   @Autowired
    SystemRolePermService systemRolePermService;
    /**
     * 新增角色
     */
    @PostMapping("/add")
    public CommResp add(@RequestBody RolePermDto rolePermDto){
        return systemRolePermService.add(rolePermDto);
    }
    /**
     * 修改角色
     */
    @PutMapping("/edit")
    public CommResp edit(@RequestBody RolePermDto rolePermDto) {
     return systemRolePermService.edit(rolePermDto);
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleId}")
    public CommResp remove(@PathVariable Integer roleId)
    {
        return systemRolePermService.deleteRoleByIds(roleId);
    }

 /**
  * 角色列表
  */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public CommResp list(@PathVariable Integer pageNum,@PathVariable Integer pageSize)
    {
     return systemRolePermService.selectList(pageNum,pageSize);
    }




}
