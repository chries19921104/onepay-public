package org.example.admin.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "接受前端返点设置数据")
public class RebateSchemeDto {

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

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页显示条数
     */
    private Integer rp;

    /**
     *存放details数据的数组
     */
    private Object[] details;

}
