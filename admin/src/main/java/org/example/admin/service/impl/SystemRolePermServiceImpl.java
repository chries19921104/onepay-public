package org.example.admin.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.buf.StringUtils;
import org.example.admin.mapper.SystemRolePermDao;
import org.example.admin.mapper.SystemRolesDao;
import org.example.admin.service.SystemRolePermService;
import org.example.admin.vo.RolePermVo;
import org.example.common.base.CommResp;
import org.example.admin.dto.RolePermDto;
import org.example.common.entity.SystemAdminRolePerms;
import org.example.common.entity.SystemAdminRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;


@Service
public class SystemRolePermServiceImpl extends ServiceImpl<SystemRolePermDao, SystemAdminRolePerms> implements SystemRolePermService {
    @Autowired
    SystemRolePermDao systemRolePermDao;
    @Autowired
    SystemRolesDao systemRolesDao;
    @Override
    public CommResp add(RolePermDto rolePermDto) {
        if (rolePermDto.getRoleId()!=null){
            return CommResp.FAIL_SAVE();
        }
        SystemAdminRoles systemAdminRoles= new SystemAdminRoles();
        BeanUtils.copyProperties(rolePermDto,systemAdminRoles);
        systemAdminRoles.setCreator("admin");
        systemAdminRoles.setUpdater("admin");
        systemAdminRoles.setCreatedAt(new Date());
        systemAdminRoles.setUpdatedAt(new Date());
        systemRolesDao.insert(systemAdminRoles);
        Integer roleId = systemAdminRoles.getId();
        SystemAdminRolePerms systemAdminRolePerms=new SystemAdminRolePerms();
        systemAdminRolePerms.setRoleId(roleId);
        ArrayList<String> permissions = rolePermDto.getPermission();
        String permission = StringUtils.join(permissions, ',');
        systemAdminRolePerms.setPermission(permission);
        systemAdminRolePerms.setCreatedAt(new Date());
        systemAdminRolePerms.setUpdatedAt(new Date());
        systemRolePermDao.insert(systemAdminRolePerms);
        return CommResp.SUCCEE();
    }

    @Override
    public CommResp edit(RolePermDto rolePermDto) {
       if (rolePermDto.getRoleId()==null){
           return CommResp.FAIL_UPDATE();
       }
       Integer roleId = rolePermDto.getRoleId();
        SystemAdminRoles systemAdminRoles = systemRolesDao.selectById(roleId);
        BeanUtils.copyProperties(rolePermDto,systemAdminRoles);
        systemAdminRoles.setCreatedAt(new Date());
        systemAdminRoles.setUpdatedAt(new Date());
        systemRolesDao.updateById(systemAdminRoles);
        LambdaQueryWrapper<SystemAdminRolePerms> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(SystemAdminRolePerms::getRoleId, roleId);
        SystemAdminRolePerms systemAdminRolePerms = systemRolePermDao.selectOne(queryWrapper);
        ArrayList<String> permissions = rolePermDto.getPermission();
        String permission = StringUtils.join(permissions, ',');
        systemAdminRolePerms.setPermission(permission);
        systemAdminRolePerms.setCreatedAt(new Date());
        systemAdminRolePerms.setUpdatedAt(new Date());
        systemRolePermDao.updateById(systemAdminRolePerms);
        return CommResp.SUCCEE();
    }

    @Override
    public CommResp deleteRoleByIds(Integer roleId) {
        if (roleId==null){
            return CommResp.FAIL_DELETE();
        }
        systemRolesDao.deleteById(roleId);
        LambdaQueryWrapper<SystemAdminRolePerms> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(SystemAdminRolePerms::getRoleId, roleId);
        SystemAdminRolePerms systemAdminRolePerms = systemRolePermDao.selectOne(queryWrapper);
        systemRolePermDao.deleteById(systemAdminRolePerms.getId());
        return CommResp.SUCCEE();
    }

    @Override
    public CommResp selectList(Integer pageNum, Integer pageSize) {
         Page<RolePermVo> page=new Page (pageNum,pageSize);
        IPage<RolePermVo> rolePermDtoIPage = systemRolePermDao.selectByList(page);
        return CommResp.data(rolePermDtoIPage.getRecords());
    }


}
