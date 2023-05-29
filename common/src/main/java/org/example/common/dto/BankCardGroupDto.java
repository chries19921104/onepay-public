package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BankCardGroupDto {

    @JsonProperty("PG100_ID")
    private Long groupId;

    @JsonProperty("PG100_name")
    private String groupName;

    @JsonProperty("SH100_ID")
    private List<Long> merchantId;

    @JsonProperty("BC100_ID")
    private List<Long> cardId;

    private String currency;

    @JsonProperty("account_code")
    private String accountCode;

    private Integer model;//新数据库已取消该字段
    private Integer rp;
    private Integer page;
}
