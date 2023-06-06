package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WhiteCreateVo {
    @JsonProperty("SH100_ID")
    private Long merchantId;

    @JsonProperty("SH130_ID")
    private Long whiteId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String creator;

    private String ip;
    private Integer status;
    private Integer type;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_man")
    private String updater;
}
