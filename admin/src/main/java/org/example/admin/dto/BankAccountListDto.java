package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Txd
 * @since 2023-06-06 16:11:46
 */
@Data
@ApiModel(description = "接收前端的银行监控列表的查询参数")
@Validated
public class BankAccountListDto {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("PG100_ID")
    private String[] PG100_ID;

    @JsonProperty("BK100_ID")
    private String BK100_ID;

    @JsonProperty("type")
    private String[] type;

    @JsonProperty("status")
    private String[] status;

    @JsonProperty("mode")
    private String[] mode;

    @JsonProperty("account_code")
    private String account_code;

    @JsonProperty("BC100_name")
    private String BC100_name;

    private Integer rp;

    private Integer page;
}
