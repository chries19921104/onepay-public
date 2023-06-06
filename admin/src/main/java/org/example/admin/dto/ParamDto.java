package org.example.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParamDto {

    private Integer status;

    private List<String> with;
}
