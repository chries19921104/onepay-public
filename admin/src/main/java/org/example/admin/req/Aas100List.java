package org.example.admin.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Aas100List {
    @ApiModelProperty("当前页")
    @NotNull(message = "page不能为空")
    Integer page;
    @ApiModelProperty("偏移量")
    @NotNull(message = "limit不能为空")
    Integer limit;
    @ApiModelProperty("数据ID")
    Integer id;
}
