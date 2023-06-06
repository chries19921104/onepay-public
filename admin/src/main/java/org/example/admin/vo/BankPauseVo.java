package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BankPauseVo {
    @JsonProperty("BK100_ID")
    private Long bankId;

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_man")
    private String creator;

    @JsonProperty("days_of_week")
    private List<String> daysOfWeek;

    @JsonProperty("end_datetime")
    private LocalDateTime endDatetime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("frequency")
    private Integer frequency;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("start_datetime")
    private LocalDateTime startDatetime;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("updated_man")
    private String updater;
}
