package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 币别
 * </p>
 *
 * @author rj
 * @since 2023-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemCurrency implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 币别代号
     */
    private String currency;

    /**
     * 是否启用0否1是
     */
    @TableField("is_active")
    private Boolean active;

    /**
     * 币别符号
     */
    private String symbol;

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
