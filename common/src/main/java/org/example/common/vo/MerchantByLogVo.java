package org.example.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "返回商户log,data数据")
public class MerchantByLogVo {
    @JsonProperty("SH100LOG_ID")
    private Long merchantLogId;

    @JsonProperty("SH100_ID")
    private Long merchantId;

    private String content;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private String operator;
    private Integer type;
}
