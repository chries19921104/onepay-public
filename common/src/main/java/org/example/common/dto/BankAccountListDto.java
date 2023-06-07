package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Txd
 * @since 2023-06-06 16:11:46
 */
@Data
@ApiModel(description = "接收前端的银行监控列表的查询参数")
public class BankAccountListDto {

    private String currency;

    private int[] PG100_ID;

    private int BK100_ID;

    private int[] type;

    private int[] status;

    private String account_code;

    private String BC100_name;
}
