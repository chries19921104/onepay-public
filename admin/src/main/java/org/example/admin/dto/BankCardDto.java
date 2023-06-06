package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class BankCardDto {

    @JsonProperty("BC100_ID")
    private List<Long> CardId;

    @JsonProperty("PG100_ID")
    private Long cardGroupId;

    @JsonProperty("PG100_ID[]")
    private List<Long> cardGroupIds;

    @JsonProperty("BK100_ID")
    private Long bankId;

    private Integer type;

    @JsonProperty("type[]")
    private List<Integer> types;

    private Integer assigned;

    private String currency;

    @JsonProperty("number")
    private String bankNumber;

    private Integer rp;

    private Integer page;

    @JsonProperty("status[]")
    private List<Integer> status;

    @JsonProperty("opencard[]")
    private List<Integer> openingStatus;

    @JsonProperty("mode")
    private Integer paymentMode;

    @JsonProperty("start_date")
    private Timestamp startDate;

    @JsonProperty("end_date")
    private Timestamp endDate;

    @JsonProperty("updated_start_date")
    private Timestamp updatedStartDate;

    @JsonProperty("updated_end_date")
    private Timestamp updatedEndDate;

    @JsonProperty("name")
    private String account;

    @JsonProperty("account_code")
    private String accountCode;

    @JsonProperty("mobile_name")
    private String mobileName;

    @JsonProperty("vnd_otp[]")
    private List<String> vndOtp;

    @JsonProperty("vnd_payment_method[]")
    private List<String> vndPaymentMethod;
}
