package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "调整订单查询模型")
@Data
public class SystemAdjustOrdersSearchDTO extends PageDTO{

    @ApiModelProperty(value = "币别" )
    private String currency;

    @ApiModelProperty(value = "商户" )
    private Integer[] merchantId;

    @ApiModelProperty(value = "交易类型" )
    private Integer[] tradeType;

    @ApiModelProperty(value = "选择类型" )
    private Integer[] adjustType;

    @ApiModelProperty(value = "选择原因" )
    private Integer[] reason;

    @ApiModelProperty(value = "状态" )
    private Integer[] status;

    @ApiModelProperty(value = "创建开始时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAtBeginTime;

    @ApiModelProperty(value = "创建结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAtEndTime;

    @ApiModelProperty(value = "更新开始时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAtBeginTime;

    @ApiModelProperty(value = "更新结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAtEndTime;

    @ApiModelProperty(value = "主键id" )
    private Integer id;

    @ApiModelProperty(value = "对账单id " )
    private Integer virtualBankReconciliationId;

}
