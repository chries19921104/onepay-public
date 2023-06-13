package org.example.agent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.entity.SystemAgentCommissionSettings;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryVo {
   private Long merchantId;
   private Long agentId;
   private String code;
   private String currency;
   private CurrentRebateVo currentRebate;
   private String name;
   private BigDecimal trQrRate;
   private BigDecimal trRate;
   private BigDecimal trTrueRate;
   private BigDecimal wdRate;
   private SystemAgentCommissionSettings nearestRebate;
}
