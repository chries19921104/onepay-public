package org.example.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(description = "返回商户新增数据")
public class MerchantByCreateVo {
    private BigDecimal FI_bank_max;
    private BigDecimal FI_bank_min;
    private BigDecimal FI_qrpay_max;
    private BigDecimal FI_qrpay_min;
    private BigDecimal FI_true_max;
    private BigDecimal FI_true_min;
    private BigDecimal FO_Tpi_driver;
    private BigDecimal FO_max;
    private BigDecimal FO_min;
    private BigDecimal FX_max;
    private BigDecimal FX_min;
    private Long cardGroupId;
    private Long merchantId;//1
    private Long agent_id;
    private Boolean check_accname;
    private String code;
    private String created_at;//
    private String created_man;//
    private String currency;
    private List<String> domains;
    private String name;
    private String name4qr;
    private Integer no_qr_adj;
    private Boolean no_qr_adj_random;
    private String password;//
    private Boolean pay_fo_bank_fee;
    private String sec_code;
    private Integer sett_card_max;
    private BigDecimal sett_fee;
    private Integer settlement_term;
    private Boolean tpi_settled;//true
    private BigDecimal tr_qr_rate;
    private BigDecimal tr_rate;
    private BigDecimal tr_true_rate;
    private String updated_at;//
    private String updated_man;//
    private Boolean use_qr_pairing_code;//true
    private String user_id;//
    private String username;
    private BigDecimal wd_rate;
}
