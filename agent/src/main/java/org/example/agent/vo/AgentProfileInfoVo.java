package org.example.agent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentProfileInfoVo {
    private Long agentId;


   private Long displayId;

    /**
     * 登录账号
     */
    private String username;


    /**
     * 全称
     */
    private String fullName;

}
