package org.example.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Txd
 * @since 2023-06-06 16:11:46
 */
@Data
@ApiModel(description = "返回前端的银行监控列表数据")
public class BankAccountListVo {

    private int ss;
}
