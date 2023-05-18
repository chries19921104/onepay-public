package org.example.common.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class MerchantByBrandVo {
    private Integer merchantId;
    private String code;
    private Integer mbId;
    private String acc_name;
    private String bank_code;
    private String bank_name;
    private String branch;
    private String card_number;
    private String created_at;
    private String name;
    private int status;
}
