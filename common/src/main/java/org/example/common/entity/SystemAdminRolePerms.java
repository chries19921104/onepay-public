package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@TableName("system_admin_role_perms")
public class SystemAdminRolePerms implements Serializable {
    private static final long serialVersionUID = 1L;
    //主键ID
    @TableId(type = IdType.AUTO )
    private Integer id;
    //角色ID
    private Integer roleId;
    //权限数据
    private String permission;
    //创建时间
    private Date createdAt;
    //更新时间
    private Date updatedAt;


}
