package org.example.admin.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RolePermVo {
    private Integer roleId;
    private String name;
    private Short status;
    private String permission;

}
