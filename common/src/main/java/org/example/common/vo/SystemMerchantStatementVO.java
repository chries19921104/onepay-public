package org.example.common.vo;

import lombok.Data;
import org.example.common.entity.SystemMerchantStatement;

@Data
public class SystemMerchantStatementVO extends SystemMerchantStatement {

    //商户代码
    private String code;

    // 商户名称
    private String name;
}
