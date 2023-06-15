package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BankCardLogVo {

    @JsonProperty("BC100LOG_ID")
    private Long bankCardLogId;

    @JsonProperty("BC100_ID")
    private Long bankCardId;

    @JsonProperty("b_c100")
    private BankCardAllVo bankCardAllVo;

    private String content;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("operator")
    private String creator;

    private Integer type;
}
