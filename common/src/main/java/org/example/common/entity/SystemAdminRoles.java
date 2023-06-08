package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.naming.ldap.PagedResultsControl;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("system_admin_roles")
public class SystemAdminRoles implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO )
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 0 不可用, 1 可用
     */
    private Short status;
    /**
     * 超级管理员, 此角色不允许被修改及移除
     */
    private Short superAdmin;
    /**
     * 创建人员
     */
    private String creator;
    /**
     * 更新人员
     */
    private String updater;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 用户所有权限的id用逗号隔开
     */
    private String permissionIds;
}
