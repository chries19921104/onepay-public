package org.example.admin.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用接收分页参数
 * @author TAOTAO
 */
@Getter
@Setter
@ApiModel(value = "分页模型")
public class PageDTO {

    @ApiModelProperty(value = "当前第几页",required = true,example = "1")
    private Integer  page;

    @ApiModelProperty(value = "每页显示几笔",required = true,example = "10")
    private Integer size;
}
