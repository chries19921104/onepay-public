package org.example.admin.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "接受前端每月调整数据")
public class MonthlyAdjustmentsDto {


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
    @TableField("SH100_ID")
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
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页显示条数
     */
    private Integer rp;

    /**
     * 日期
     */
    private String date;

    /**
     * 所属总代
     */
    @TableField("belong_id")
    private Long belongId;

    /**
     * 代理ID
     */
    @TableField("agent_id")
    private Long agentId;




}
