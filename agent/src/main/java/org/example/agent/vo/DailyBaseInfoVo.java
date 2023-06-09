package org.example.agent.vo;

import lombok.Data;

@Data
public class DailyBaseInfoVo {
    private Long id;
    private String display_id;
    private String username;
    private String full_name;
    private Integer identity;
    private Long belong_id;
}
