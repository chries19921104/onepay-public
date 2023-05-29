package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SystemBankCardDto {

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    private Integer type;
}
