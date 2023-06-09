package org.example.agent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRebateVo {
    private Long rb100Id;
    private Long sh100Id;
    //代理ID
    private Integer agentId;
    private Integer year;
    private Integer month;
    //币别
    private String currency;
    //银行充值回扣方案
    private Integer bdrsRpId;
    //QR充值回扣方案
    private Integer qdrsRpId;

    //代付回扣方案
    private Integer wrsRpId;
    //银行充值抽成 (%)
    private Double bdmRate;
    //QR充值抽成 (%)
    private Double qdmRate;
    //true wallet充值抽成 (%)
    private Double twdmRate;
    //代付抽成 (%)
    private Double wdmRate;
    //使用中
    private Integer active;
    private Long twdrsRpId;
    //创建人员
    private String createdMan;
    //更新人员
    private String updatedMan;
    private Date createdAt;
    private Date updatedAt;
    private BankDepositRebatePlanVo bankDepositRebatePlanVo;
    private QrDepositRebatePlanVo qrDepositRebatePlanVo;
    private WithdrawRebatePlanVo withdrawRebatePlanVo;


}
