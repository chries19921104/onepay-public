package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "返回银行数据")
public class BrankVo {
    /**
     * 银行ID
     */
    @TableId
    private Long bankId;

    /**
     * 银行名称
     */
    private String name;

    /**
     * 银行代号
     */
    private String code;

    /**
     * 币别
     */
    private String currency;

    /**
     * 状态 1.Available(可用) 0.Suspended(暂停) BS200
     */
    private Integer status;
}
