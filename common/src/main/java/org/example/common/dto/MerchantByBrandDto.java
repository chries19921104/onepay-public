package org.example.common.dto;

import lombok.Data;

import java.util.List;

@Data
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
