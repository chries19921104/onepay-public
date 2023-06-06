package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BankPauseDto {

    @JsonProperty("BK120_ID")
    private Long id;

    @JsonProperty("BK100_ID")
    private Long bankId;

    private Integer type;

    private Integer status;

    private String currency;

    private Integer rp;

    private Integer page;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("day_of_week")
    private List<String> dayOfWeek;

    private Integer frequency;

    private String message;
}
