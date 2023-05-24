package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "接收前端银行数据")
public class BrankDto {
    @JsonProperty("BK100_ID")
    private Long bankId;

    private String message;

    @JsonProperty("is_enable")
    private Integer isEnabled;

    @JsonProperty("txn_type")
    private Integer txnType;
}
