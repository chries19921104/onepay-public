package org.example.admin.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.dto.AdminsDTO;
import org.example.common.base.CommResp;
import org.example.common.entity.Admins;
import org.example.common.vo.AdminsVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
* admins Service 接口
* </p>
*
* @author zhangmi
* @since 2023-05-11 16:39:22
*/
@Service
public interface AdminsService extends IService<Admins> {

    CommResp login(Admins admins);

    CommResp register(AdminsDTO adminsDTO);

    CommResp update(AdminsDTO adminsDTO);

    CommResp delete(AdminsDTO adminsDTO);

    //查询出所有管理员姓名
    List<AdminsVO> getAdminByName();
}