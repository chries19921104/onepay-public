package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "接收商户账户数据")
public class MerchantByBrandDto {
    private String currency;
    private List<Integer> merchantId;
    private List<Integer> status;
    private String cardNumber;
    private String startDate;
    private String endDate;
    private Integer rp;
    private Integer page;
}
