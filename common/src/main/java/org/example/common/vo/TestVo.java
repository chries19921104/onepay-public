package org.example.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class TestVo {
    private int id;
    @ApiModelProperty("名称")
    private String name;
}
