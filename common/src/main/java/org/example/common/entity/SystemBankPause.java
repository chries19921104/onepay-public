package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* system_bank_pause 实体类
* </p>
*
* @author Administrator
* @since 2023-06-01 12:49:22
*/
@Getter
@Setter
@TableName("system_bank_pause")
public class SystemBankPause implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
    * 银行ID
    */
    private Long bankId;

    /**
    * 1: Bank 2: Route (Bank) 3: Route (FI) 4: Route(QR FI)
    */
    private Integer type;

    /**
    * 1: One Time 2: Every Day 3: Periodicity
    */
    private Integer frequency;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    private String startTime;

    private String endTime;

    /**
    * 2^0 [for Sunday] through 2^6 [for Saturday]
    */
    private Integer daysOfWeek;

    /**
    * 0: 未启用 1: 启用
    */
    private Integer status;

    /**
    * 信息
    */
    private String message;

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
    private LocalDateTime updatedAt;


}
