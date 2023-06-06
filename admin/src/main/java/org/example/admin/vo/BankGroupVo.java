package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankGroupVo {
    @JsonProperty("PG100_ID")
    private Long groupId;

    @JsonProperty("PG100_name")
    private String groupName;

    private String currency;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String creator;

    @JsonProperty("updated_man")
    private String updater;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
