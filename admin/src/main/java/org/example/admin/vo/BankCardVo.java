package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BankCardVo {
    @JsonProperty("BC100_ID")
    private Long cardId;

    @JsonProperty("BK100_ID")
    private Long bankId;

    @JsonProperty("account_code")
    private String accountCode;

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("name")
    private String account;

    @JsonProperty("number")
    private String bankNumber;
}
