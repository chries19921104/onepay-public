package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.vo.RolePermVo;
import org.example.common.base.CommResp;
import org.example.common.dto.RolePermDto;
import org.example.common.entity.SystemAdminRolePerms;
import org.springframework.stereotype.Service;

@Service
public interface SystemRolePermService extends IService<SystemAdminRolePerms> {
    CommResp add(RolePermDto rolePermDto);

    CommResp edit(RolePermDto rolePermDto);

    CommResp deleteRoleByIds(Integer roleId);

    CommResp selectList(Integer pageNum, Integer pageSize);

}
