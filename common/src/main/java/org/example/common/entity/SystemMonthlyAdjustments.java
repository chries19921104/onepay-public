package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("system_monthly_adjustments")
@ApiModel(description = "每月调整实体类")
public class SystemMonthlyAdjustments {

    @TableId(value = "ma_id",type = IdType.AUTO)
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
    private Integer merchantId;

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



}
