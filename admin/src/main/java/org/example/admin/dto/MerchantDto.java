package org.example.admin.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "接收商户数据")
public class MerchantDto {

    @JsonProperty("SH100_ID[]")
    private List<Integer> merchantId;

    @JsonProperty("agent_id[]")
    private List<String> agentId;

    private String currency;

    private Integer assigned;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    private List<Integer> status;

    @JsonProperty("not_allowed_types[]")
    private List<Integer> notAllowedTypes;

    @JsonProperty("not_allowed_BK100_ID[]")
    private String notAllowedBk100Id;

    private Integer rp;
    private Integer page;

    @JsonProperty("PG100_ID")
    private Integer groupId;
}
