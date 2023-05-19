package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "接收商户白名单数据")
public class MerchantByWhiteDto {
    private List<Integer> merchantId;
    private String currency;
    private List<Integer> type;
    private String ip;
    private String startDate;
    private String endDate;
    private Integer rp;
    private Integer page;
}
