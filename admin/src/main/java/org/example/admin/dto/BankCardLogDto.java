package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BankCardLogDto {

    @ApiModelProperty(value = "当前的银行卡id",required = true)
    @JsonProperty("BC100_ID")
    private Long bankCardId;

    private Integer type;

    @JsonProperty("admin_name")
    private String adminName;

    @ApiModelProperty(value = "每页条数",required = true)
    private Integer rp;

    @ApiModelProperty(value = "当前页码",required = true)
    private Integer page;

    @JsonProperty("start_data")
    private String startData;

    @JsonProperty("end_data")
    private String endData;

}
