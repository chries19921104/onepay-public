package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BankLogDto {

    @JsonProperty("BK100_ID")
    private Long bankId;

    private String type;

    @JsonProperty("admin_name")
    private String operator;

    private Integer rp;

    private Integer page;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
}
