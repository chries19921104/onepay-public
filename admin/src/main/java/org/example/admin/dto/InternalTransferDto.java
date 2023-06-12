package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Txd
 * @since 2023-06-12 10:31:50
 */
@Data
@ApiModel(description = "接收前端的内部转账列表的查询参数")
@Validated
public class InternalTransferDto {

    @JsonProperty("PG100_ID")
    private Integer PG100_ID;

    @JsonProperty("status")
    @Min(value = 1, message = "status必须在（1,2,3,4,5）")
    @Max(value = 5, message = "status必须在（1,2,3,4,5）")
    @Valid
    private List<Integer> status;

    @JsonProperty("type")
    private ArrayList<String> type;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("BK100_ID")
    private Integer BK100_ID;

    @JsonProperty("start_date")
    private Timestamp start_date;

    @JsonProperty("end_date")
    private Timestamp end_date;

    @JsonProperty("updated_start_date")
    private Timestamp updated_start_date;

    @JsonProperty("updated_end_date")
    @FutureOrPresent
    private Timestamp updated_end_date;

    @JsonProperty("completed_start_time")
    private Timestamp completed_start_time;

    @JsonProperty("completed_end_time")
    @FutureOrPresent
    private Timestamp completed_end_time;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("vnd_otp")
    private String[] vnd_otp;

    @JsonProperty("vnd_payment_method")
    private String vnd_payment_method;

    @JsonProperty("postscript")
    private String postscript;

    @JsonProperty("alt_id")
    private String alt_id;

    @JsonProperty("tr_auto")
    private boolean tr_auto;

    @JsonProperty("vbs_tag")
    @Min(value = 1, message = "status必须在（1,2）")
    @Max(value = 5, message = "status必须在（1,2）")
    private Integer vbs_tag;

    private Integer rp;

    private Integer page;


}
