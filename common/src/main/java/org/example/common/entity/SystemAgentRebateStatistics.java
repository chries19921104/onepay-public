package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemAgentRebateStatistics {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long merchantId;
    private Date date;
    private Long agentId;
    private BigDecimal fiBankMarkupRate;
    private BigDecimal fiQrpayMarkupRate;
    private BigDecimal fiTruewalletMarkupRate;
    private BigDecimal fiLocalbankMarkupRate;
    private BigDecimal foMarkupRate;
    private BigDecimal fiBankRebate;
    private BigDecimal fiQrpayRebate;
    private BigDecimal fiTruewalletRebate;
    private BigDecimal fiLocalbankRebate;
    private BigDecimal foRebate;
    private BigDecimal revenue;
    private String creator;
    private String updater;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
