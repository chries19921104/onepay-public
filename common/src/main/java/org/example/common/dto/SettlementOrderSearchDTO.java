package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.example.common.dto.PageDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@ApiModel(value = "下发查询模型")
@Data
public class SettlementOrderSearchDTO extends PageDTO {

    @ApiModelProperty(value = "币别" )
    private String currency;

    @ApiModelProperty(value = "群组id")
    private Integer cardGroupId;

    @ApiModelProperty(value = "商户id")
    private Integer[] merchantId;

    @ApiModelProperty(value = "状态" )
    private Integer[] status;

    @ApiModelProperty(value = "第三方支付名称" )
    private String company;

    @ApiModelProperty(value = "银行账户（from）" )
    private String toCardNumber;

    @ApiModelProperty(value = "to银行" )
    private String toBank;

    @ApiModelProperty(value = "下发id" )
    private Integer fxId;

    @ApiModelProperty(value = "子下发id" )
    private Integer fx110ID;

    @ApiModelProperty(value = "交易代码" )
    private String trCode;

    @ApiModelProperty(value = "创建开始时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAtBeginTime;

    @ApiModelProperty(value = "创建结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAtEndTime;

    @ApiModelProperty(value = "更新时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAtBeginTime;

    @ApiModelProperty(value = "更新结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAtEndTime;

    @ApiModelProperty(value = "成功时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completedAtBeginTime;

    @ApiModelProperty(value = "成功结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date completedAtEndTime;



}
