package org.example.merchant.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Aas100List {
    @ApiModelProperty("当前页")
    int page;
    @ApiModelProperty("名称")
    int limit;
    @ApiModelProperty("数据ID")
    Integer id;
}
