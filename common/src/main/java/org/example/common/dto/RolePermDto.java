package org.example.common.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RolePermDto {
    private Integer roleId;
    private String name;
    private Short status;
    private ArrayList<String> permission;
}
