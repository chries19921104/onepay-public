package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "返回代理数据")
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
