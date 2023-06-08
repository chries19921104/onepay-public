package org.example.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统下拉选单设定档
 * </p>
 *
 * @author rj
 * @since 2023-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemSelectOptionConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型 舉例：BK100_status
     */
    private String type;

    /**
     * 序号
     */
    private Integer num;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序/位置，可预设与num一样，下拉选单有特殊排序需求再改
     */
    private Integer spot;

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
