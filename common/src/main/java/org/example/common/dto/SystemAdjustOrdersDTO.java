package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "调整表新增or查询模型")
@Data
public class SystemAdjustOrdersDTO {

    @ApiModelProperty(value = "币别")
    @NotBlank(message = "币别不能为空")
    private String currency;

    @ApiModelProperty(value = "商户id")
    @NotNull(message = "商户id不能为空")
    private Integer merchantId;

    @ApiModelProperty(value = "金额")
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "交易类型")
    @NotNull(message = "交易类型不能为空")
    private Integer tradeType;

    @ApiModelProperty(value = "类别")
    @NotNull(message = "类别不能为空")
    private Integer adjustType;

    @ApiModelProperty(value = "原因")
    private Integer reason;

    @ApiModelProperty(value = "系统描述")
    @NotBlank(message = "系统描述不能为空")
    private String description;

    @ApiModelProperty(value = "外部描述")
    @NotBlank(message = "外部描述不能为空")
    private String externalDescription;

    @ApiModelProperty(value = "商户对账单")
    private String virtualBankReconciliationId;

    @ApiModelProperty(value = "调整id")
    private Integer id;


}
