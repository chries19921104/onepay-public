package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DashboardDto {

    private String currency;
    @JsonProperty("PG100_ID")
    private Integer cardGroupId;
    @JsonProperty("SH100_ID")
    private List<String> merchantId;
}
