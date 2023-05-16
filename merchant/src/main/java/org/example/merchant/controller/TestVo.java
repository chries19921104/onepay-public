package org.example.merchant.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class TestVo {
    private int id;
    @ApiModelProperty("名称")
    @NotEmpty(message = "name不能为空")
    private String name;
}
