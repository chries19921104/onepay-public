package org.example.admin.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.example.common.entity.SystemAgents;
import org.example.common.entity.SystemRebateProgression;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Data
@ApiModel(description = "返回返点设置数据")
public class RebateSchemeVo {

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
     * 存放代理用户数据的数组
     */
    private HashSet<SystemAgents> agents;

    /**
     *存放details数据的数组
     */
    private List<?> details;


}
