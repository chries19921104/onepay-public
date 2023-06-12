package org.example.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Data
@TableName("system_rebate_scheme")
@ApiModel(description = "回扣方案实体类")
public class SystemRebateScheme {

    @TableId(value = "rs_id",type = IdType.AUTO)
    private Long rsId;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 1- 累进式, 2- 固定式
     */
    private Integer type;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 更新人员
     */
    private String updater;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime UpdatedAt;



}
