package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Txd
 * @since 2023-06-06 16:11:46
 */
@Data
@ApiModel(description = "返回前端的银行监控列表数据")
public class BankAccountListVo {

    private String command_id;
    @JsonProperty("BC100_ID")
    private int BC100_ID;
    @JsonProperty("PG100_ID")
    private int PG100_ID;
    private String status;
    private String account_code;
    private String name;
    private String mobile_name;
    private String sim;
    private String sim_opt;
    private String sim_credit_expired;
    private String currency;
    private String updated_at;
    @JsonProperty("VPN100_ID")
    private String VPN100_ID;
    private String mode;
    private String vnd_otp;
    private String vnd_payment_method;
    private String message;
    private String withdraw_pin;
    @JsonProperty("BC100_type")
    private String BC100_type;
    @JsonProperty("BC100_bank_code")
    private String BC100_bank_code;
    @JsonProperty("BC100_type_PG100_name")
    private String BC100_type_PG100_name;
    private String today_DP;
    private String today_Payout;
    private String max_balance;
    private String balance;
    private String balance_xy;
    private String PG100_name;
    @JsonProperty("From_Account")
    private String from_Account;
    @JsonProperty("Account_Remark")
    private String account_Remark;
    @JsonProperty("Daily_Transfer_Limit")
    private String daily_Transfer_Limit;
    @JsonProperty("Daily_Remaining_From_Limit")
    private String daily_Remaining_From_Limit;
    @JsonProperty("Daily_Remaining_To_Limit")
    private String daily_Remaining_To_Limit;
    @JsonProperty("Monthly_Transfer_Limit")
    private String monthly_Transfer_Limit;
    @JsonProperty("Monthly_Remaining_From_Limit")
    private String monthly_Remaining_From_Limit;
    @JsonProperty("Monthly_Remaining_To_Limit")
    private String monthly_Remaining_To_Limit;
    @JsonProperty("Transfer_Number_of_Times")
    private String transfer_Number_of_Times;
    @JsonProperty("Remaining_Number_of_Times")
    private String remaining_Number_of_Times;
    @JsonProperty("D_Transfer_Number_of_Times")
    private String d_Transfer_Number_of_Times;
    @JsonProperty("Internal_Max")
    private String internal_Max;
    @JsonProperty("Internal_Min")
    private String internal_Min;
    private String vpn_name;
    private String step;
    private String max_balance_ratio;
    private String statement;

}
