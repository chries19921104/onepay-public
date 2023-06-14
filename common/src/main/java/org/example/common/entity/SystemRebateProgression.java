package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("system_rebate_progression")
@ApiModel(description = "累进式回扣方案实体类")
public class SystemRebateProgression {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 回扣方案主键ID
     */
    private Long rsId;

    /**
     * 区段开始金额
     */
//    @Column(name = "`from`")
    @TableField(value = "`from`")
    private BigDecimal from;

    /**
     * 区段结束金额
     */
//    @Column(name = "`to`")
    @TableField(value = "`to`")
    private BigDecimal to;

    /**
     * 返点率
     */
    private BigDecimal rate;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

}
