package org.example.agent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryVo {
   private Long merchantId;
   private String code;
   private String currency;
   private Long currentRebate;
   private String name;
   private BigDecimal nearestRebate;
   private BigDecimal trQrRate;
   private BigDecimal trRate;
   private BigDecimal trTrueRate;
   private BigDecimal wdRate;
}
