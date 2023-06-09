package org.example.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@TableName("system_agent_users")
@ApiModel(description = "代理用户实体类")
@AllArgsConstructor
@NoArgsConstructor
public class SystemAgentUsers {

    /**
     * 代理id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 代理用户名
     */
    private String username;

    /**
     * 代理密码
     */
    private String password;

    /**
     * 代理姓名
     */
    private String agentName;

    /**
     * 代理标识符
     */
    private String agentCode;

    /**
     * 所属货币
     */
    private String currency;

    /**
     * 手续费抽成（0.00~100.00）
     */
    private Double priceRate;

    /**
     * 余额（保留小数点后四位）
     */
    private Double balance;

    /**
     * 申请提款后的锁定金额
     */
    private Integer holdBalance;

    /**
     * 目前状态，1：正常，2：封锁，3：待审核
     */
    private Integer status;

    /**
     * Token
     */
    private String rememberToken;

    /**
     * 最后修改操作者（admin）
     */
    private String lastController;

    /**
     * 最后修改动作
     */
    private String lastEvent;

    /**
     * 最后登录时间（时间戳）
     */
    private Long lastLogin;

    /**
     * 最后登录ip
     */
    private String lastIp;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
