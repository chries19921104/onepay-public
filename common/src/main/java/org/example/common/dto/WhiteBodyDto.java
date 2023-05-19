package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "接收白名单数据")
public class WhiteBodyDto {
    @JsonProperty("SH100_ID")
    private Long merchantId;

    private String ip;
    private Integer status;
    private String type;
}
