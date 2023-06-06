package org.example.admin.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(description = "接收商户数据")
public class MerchantBodyDto {
    private String FI_Tpi_driver;//
    private List<String> FI_Tpi_services_enable;//
    private String FI_True_max;//
    private String FI_True_min;//
    private BigDecimal FI_bank_max;//1
    private BigDecimal FI_bank_min;//1
    private BigDecimal FI_local_max;//1
    private BigDecimal FI_local_min;//1
    private BigDecimal FI_qrpay_max;//1
    private BigDecimal FI_qrpay_min;//1
    private BigDecimal FI_true_max;//1

    public BigDecimal getFI_true_max() {
        return FI_true_max;
    }

    private BigDecimal FI_true_min;//1

    public BigDecimal getFI_true_min() {
        return FI_true_min;
    }

    private String FO_Tpi_driver;//
    private List<String> FO_Tpi_services_enable;//
    private BigDecimal FO_max;//1
    private BigDecimal FO_min;//1
    private List<String> FX_Tpi_services_enable;//
    private BigDecimal FX_max;//1
    private BigDecimal FX_min;//1
    private String Indonesian_transaction_mode;//
    private Long cardGroupId;//1
    private Integer VNPAY_enabled;//1
    private List<String> VNPAY_services_enable;//
    private String admin_password;//
    private Long agent_id;//1
    private Boolean check_accname;//1
    private String code;//1
    private String currency;//1
    private List<String> domains;//
    private String local_rate;//2
    private String name;//1
    private String name4qr;//1
    private Integer no_bank_adj;//2
    private Integer no_qr_adj;//1
    private Boolean no_qr_adj_random;//1
    private List<BrankDto> open_bank;//
    private Boolean pay_fo_bank_fee;//1
    private String sec_code;//1
    private Integer sett_card_max;//1
    private BigDecimal sett_fee;//1
    private Integer settlement_term;//1
    private BigDecimal tr_qr_rate;//1
    private BigDecimal tr_rate;//1
    private BigDecimal tr_true_rate;//1
    private String username;//2
    private BigDecimal wd_rate;//1
}
