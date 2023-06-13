package org.example.agent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentInfoVo {
    private Long agentId;
    private Long displayId;
    private Integer identity;
    private String username;
    private String fullName;
    private Integer status;
    private List<String> currency;
    private Integer isTester;
    private String belongId;
    private List<String> belongName;
}
