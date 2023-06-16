package org.example.agent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel("接收前端每日报表参数")
public class DailyReportDto {
    private List<Long> SH100_ID;
    private String currency;
    private Timestamp start_date;
    private Timestamp end_date;
    private Integer page;

}
