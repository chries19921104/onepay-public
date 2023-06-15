package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SystemExternalTrabeOrderDto {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("BK100_ID")
    private Long bankId;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("start_date")
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    private LocalDateTime endDate;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("alt_id")
    private Long altId;

    @JsonProperty("message")
    private String message;

    @JsonProperty("VB100_id_associated")
    private String vb100IdAssociated;

    @JsonProperty("AJ100_id_associated")
    private String aj100IdAssociated;

    @JsonProperty("postscript")
    private String postscript;

    @JsonProperty("updated_start_date")
    private LocalDateTime updatedStartDate;

    @JsonProperty("updated_end_date")
    private LocalDateTime updatedEndDate;

    @JsonProperty("rp")
    private Integer rp;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("completed_start_time")
    private LocalDateTime completedStartTime;

    @JsonProperty("completed_end_time")
    private LocalDateTime completedEndTime;

}
