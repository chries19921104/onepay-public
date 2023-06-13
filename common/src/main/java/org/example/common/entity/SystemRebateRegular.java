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
@TableName("system_rebate_regular")
@ApiModel(description = "固定式回扣方案实体类")
public class SystemRebateRegular {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 回扣方案主键ID
     */
    private Long rsId;

    /**
     * 固定值
     */
    private BigDecimal volume;

    /**
     * 返点率
     */
    private double rate;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;



}
