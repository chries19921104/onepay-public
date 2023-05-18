package org.example.common.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;
import java.util.stream.Stream;

@Data
public class MerchantBodyDto {
    private String FI_Tpi_driver;//
    private List<String> FI_Tpi_services_enable;//
    private String FI_True_max;//
    private String FI_True_min;//
    private String FI_bank_max;//1
    private String FI_bank_min;//1
    private String FI_local_max;//1
    private String FI_local_min;//1
    private String FI_qrpay_max;//1
    private String FI_qrpay_min;//1
    private String FI_true_max;//1
    private String FI_true_min;//1
    private String FO_Tpi_driver;//
    private List<String> FO_Tpi_services_enable;//
    private String FO_max;//1
    private String FO_min;//1
    private List<String> FX_Tpi_services_enable;//
    private String FX_max;//1
    private String FX_min;//1
    private String Indonesian_transaction_mode;//
    private Integer cardGroupId;//1
    private Integer VNPAY_enabled;//1
    private List<String> VNPAY_services_enable;//
    private String admin_password;//
    private Integer agent_id;//1
    private String check_accname;//1
    private String code;//1
    private String currency;//1
    private List<String> domains;//
    private String local_rate;//2
    private String name;//1
    private String name4qr;//1
    private Integer no_bank_adj;//2
    private Integer no_qr_adj;//1
    private String no_qr_adj_random;//1
    private List<BrankDto> open_bank;//
    private String pay_fo_bank_fee;//1
    private String sec_code;//1
    private String sett_card_max;//1
    private String sett_fee;//1
    private Integer settlement_term;//1
    private String tr_qr_rate;//1
    private String tr_rate;//1
    private String tr_true_rate;//1
    private String username;//2
    private String wd_rate;//1
}
