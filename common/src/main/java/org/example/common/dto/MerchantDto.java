package org.example.common.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "接收商户数据")
public class MerchantDto {
    private List<Integer> merchantId;
    private List<String> agentId;
    private String currency;
    private String startDate;
    private String endDate;
    private List<Integer> status;
    private List<Integer> notAllowedTypes;
    private String notAllowedBk100Id;
    private Integer rp;
    private Integer page;
    private Integer groupId;
}
