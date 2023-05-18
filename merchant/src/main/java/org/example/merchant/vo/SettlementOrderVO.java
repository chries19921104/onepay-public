package org.example.merchant.vo;

import lombok.Data;
import org.example.merchant.entity.SystemSettlementOrder;
@Data
public class SettlementOrderVO extends SystemSettlementOrder {

    //商户名称
    private String name;

    //商户代码
    private String code;
}
