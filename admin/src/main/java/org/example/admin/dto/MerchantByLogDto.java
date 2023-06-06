package org.example.admin.dto;

import lombok.Data;

@Data
public class MerchantByLogDto {
    private Integer merchantId;
    private String type;
    private String admin_name;
    private String startDate;
    private String endDate;
    private Integer rp;
    private Integer page;
}
