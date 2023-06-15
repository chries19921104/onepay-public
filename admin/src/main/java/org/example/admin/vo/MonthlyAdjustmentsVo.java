package org.example.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "返回每月调整数据")
public class MonthlyAdjustmentsVo {

    private Integer maId;

    /**
     * 年
     */
    private Integer year;

    /**
     * 月
     */
    private Integer month;

    /**
     *  商户ID
     */
    private Long merchantId;

    /**
     * 币别
     */
    private String currency;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createdMan;

    /**
     * 更新人
     */
    private String updatedMan;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     *  代理全称
     */
    private String agentName;

    /**
     *  代理ID
     */
    private String agentId;

    /**
     *  商户名称
     */
    private String merchantName;


}
