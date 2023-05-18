package org.example.common.vo;

import lombok.Data;
import org.example.common.entity.SystemSubSettlementOrder;

@Data
public class SubSettlementOrderVO extends SystemSubSettlementOrder {

    // 商户代码
    private String code;

    // 商户名称
    private String name;

    // to银行
    private String toBank;

    // to银行账户
    private String toCardNumber;

    // to用户名称
    private String toMan;


}
