package org.example.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankLogVo {

    @JsonProperty("BK100LOG_ID")
    private Long bankLogId;

    @JsonProperty("BK100_ID")
    private Long bankId;

    private Integer type;

    private String content;

    private LocalDateTime createdAt;

    private String operator;
}
