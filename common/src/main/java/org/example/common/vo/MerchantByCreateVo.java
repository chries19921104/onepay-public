package org.example.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "返回商户新增数据")
public class MerchantByCreateVo {
    private String FI_bank_max;
    private String FI_bank_min;
    private String FI_qrpay_max;
    private String FI_qrpay_min;
    private String FI_true_max;
    private String FI_true_min;
    private String FO_Tpi_driver;
    private String FO_max;
    private String FO_min;
    private String FX_max;
    private String FX_min;
    private Integer cardGroupId;
    private Integer merchantId;//1
    private Integer agent_id;
    private String check_accname;
    private String code;
    private String created_at;//
    private String created_man;//
    private String currency;
    private List<String> domains;
    private String name;
    private String name4qr;
    private Integer no_qr_adj;
    private String no_qr_adj_random;
    private String password;//
    private String pay_fo_bank_fee;
    private String sec_code;
    private String sett_card_max;
    private String sett_fee;
    private Integer settlement_term;
    private boolean tpi_settled;//true
    private String tr_qr_rate;
    private String tr_rate;
    private String tr_true_rate;
    private String updated_at;//
    private String updated_man;//
    private boolean use_qr_pairing_code;//true
    private String user_id;//
    private String username;
    private String wd_rate;
}
