package org.example.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "返回商户白名单data数据")
public class MerchantByWhiteVo {
    @JsonProperty("SH100_code")
    private String code;

    @JsonProperty("SH100_name")
    private String name;

    @JsonProperty("SH130_ID")
    private Long whiteId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("created_man")
    private String creator;

    private String ip;
    private Integer status;
    private Integer type;
}
