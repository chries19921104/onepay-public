package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class SystemExternalTrabeOrderVo {

    @JsonProperty("et_id")
    private Long etId;

    @JsonProperty("account_code")
    private String accountCode;

    @JsonProperty("to_man")
    private String toMan;

    @JsonProperty("to_bank")
    private String toBank;

    @JsonProperty("to_card_number")
    private String toCardNumber;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("pai_amount")
    private Long paidAmount;

    @JsonProperty("bank_fee")
    private Long bankFee;

    @JsonProperty("vnd_payment_method")
    private String vndPaymentMethod;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("local_created_at")
    private LocalDateTime localCreatedAt;

    @JsonProperty("local_updated_at")
    private LocalDateTime localUpdatedAt;

    @JsonProperty("created_man")
    private String createdMan;

    @JsonProperty("updated_man")
    private String updatedMan;

    @JsonProperty("completed_at")
    private LocalDateTime completedAt;

    @JsonProperty("action_log")
    private String actionLog;

    @JsonProperty("message")
    private String message;

    @JsonProperty("step")
    private String step;

    @JsonProperty("request_amount")
    private Long requestAmount;
}
