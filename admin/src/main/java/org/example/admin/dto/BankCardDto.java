package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
public class BankCardDto {

    @JsonProperty("BC100_ID")
    private List<Long> CardId;

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    private Integer type;

    private Integer assigned;

    private String currency;
}
