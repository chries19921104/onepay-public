package org.example.common.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "用于常用返回数据中的totals数据")
public class  Totals {
    @JsonProperty("available_balance")
    private BigDecimal availableBalance;

    @JsonProperty("current_balance")
    private BigDecimal currentBalance;

    @JsonProperty("deposit_outstanding_balance")
    private BigDecimal depositOutstandingBalance;

    @JsonProperty("frozen_balance")
    private BigDecimal frozenBalance;

    @JsonProperty("hold_balance")
    private BigDecimal holdBalance;

    @JsonProperty("today_tr_fee")
    private BigDecimal todayTrFee;

    @JsonProperty("loss_amount")
    private BigDecimal lossAmount;

    @JsonProperty("order_amount")
    private BigDecimal orderAmount;

    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    @JsonProperty("rate")
    private BigDecimal rate;

    @JsonProperty("request_amount")
    private BigDecimal requestAmount;

    @JsonProperty("bank_fee")
    private BigDecimal bankFee;

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
}
