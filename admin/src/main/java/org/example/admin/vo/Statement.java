package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Statement {

    @JsonProperty("lCount")
    private Integer lCount;

    @JsonProperty("pCount")
    private Integer pCount;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("is_errurl")
    private Integer isErrurl;

    @JsonProperty("old_command_id")
    private String oldCommandId;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("history_status")
    private Integer historyStatus;
}
