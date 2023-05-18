package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.admin.vo.RolePermVo;
import org.example.common.entity.SystemAdminRolePerms;


@Mapper
public interface SystemRolePermDao extends BaseMapper<SystemAdminRolePerms> {
    IPage<RolePermVo> selectByList(@Param("page")Page<RolePermVo> page);
}
