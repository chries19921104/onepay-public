package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 抽成设定(SystemAgentCommissionSettings)表实体类
 *
 * @author makejava
 * @since 2023-06-08 11:16:59
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_agent_commission_settings")
public class SystemAgentCommissionSettings  {
    @TableId
    private Long rb100Id;

    
    private Long sh100Id;
    //代理ID
    private Long agentId;
    
    private Integer year;
    
    private Integer month;
    //币别
    private String currency;
    //银行充值回扣方案
    private Integer bdrsRpId;
    //QR充值回扣方案
    private Integer qdrsRpId;
    //true wallet充值回扣方案
    private Integer twdrsRpId;
    //LocalBankTransfer充值回扣方案
    private Integer lbdrsRpId;
    //代付回扣方案
    private Integer wrsRpId;
    //银行充值抽成 (%)
    private Double bdmRate;
    //QR充值抽成 (%)
    private Double qdmRate;
    //true wallet充值抽成 (%)
    private Double twdmRate;
    //LocalBankTransfer充值抽成 (%)
    private Double lbdmRate;
    //代付抽成 (%)
    private Double wdmRate;
    //使用中
    private Integer active;
    //创建人员
    private String createdMan;
    //更新人员
    private String updatedMan;
    
    private Date createdAt;
    
    private Date updatedAt;



}

