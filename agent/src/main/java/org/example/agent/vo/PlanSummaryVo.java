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
    private String displayId;
    private String creator;
    private String createdAt;

    private String fullName;
    private Long agentId;
    private Integer identity;
    private List<SummaryVo> planSummary;
    private Integer status;
    private LocalDateTime updatedAt;
    private String updater;
    private String username;


}
