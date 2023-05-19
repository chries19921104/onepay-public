package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "接收前端银行数据")
public class BrankDto {
    private Integer BK100_ID;
    private String message;
    private Integer is_enabled;
    private Integer txn_type;
}
