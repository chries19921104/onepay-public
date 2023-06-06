package org.example.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@ApiModel(description = "返回商户银行数据")
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
    private Integer status;
}
