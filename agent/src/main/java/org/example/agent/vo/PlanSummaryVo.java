package org.example.agent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanSummaryVo {
    private Long belongId;
    private LocalDateTime createdMan;
    private LocalDateTime createdAt;

    private String fullName;
    private Long agentId;
    private Integer identity;
    private List<SummaryVo> planSummary;
    private Integer status;
    private Integer updatedAt;
    private String updatedMan;
    private String username;


}
