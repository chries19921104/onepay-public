package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AgentsVo {
    @TableId
    private Long agentId;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 所属总代
     */
    private Long belongId;
}
