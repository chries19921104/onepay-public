package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 代付子订单操作log
 * </p>
 *
 * @author rj
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemWithdrawalOrderLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * FO120 ID
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * FO110 ID
     */
    private Integer woId;

    /**
     * 纪录类型, 0:check, 1:retry
     */
    private Integer type;

    /**
     * 创建人员
     */
    private String createdMan;

    /**
     * 更新人员
     */
    private String updatedMan;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
