package org.example.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDto {
    private List<Long> SH100ID;
    private Long agentId;
    private String currency;
    private Integer rp;
    private Integer page;
}
