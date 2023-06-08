package org.example.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "用于常用返回数据中的totals数据")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Totals {
    private BigDecimal availableBalance;
    private BigDecimal currentBalance;
    private BigDecimal depositOutstandingBalance;
    private BigDecimal frozenBalance;
    private BigDecimal holdBalance;
    private BigDecimal todayTrFee;

    @JsonProperty("ET_hold_balance")
    private BigDecimal etHoldBalance;

    @JsonProperty("FI_hold_balance")
    private BigDecimal fiHoldBalance;

    @JsonProperty("FO_hold_balance")
    private BigDecimal foHoldBalance;

    @JsonProperty("FX_hold_balance")
    private BigDecimal fxHoldBalance;

    @JsonProperty("TR_in_hold_balance")
    private BigDecimal trInHoldBalance;

    @JsonProperty("TR_out_hold_balance")
    private BigDecimal trOutHoldBalance;

    @JsonProperty("loss")
    private BigDecimal loss;

    @JsonProperty("remaining_balance")
    private BigDecimal remainingBalance;

    @JsonProperty("today_DP")
    private BigDecimal today_DP;

    @JsonProperty("today_Payout")
    private BigDecimal today_Payout;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("balance_xy")
    private BigDecimal balance_xy;
}
