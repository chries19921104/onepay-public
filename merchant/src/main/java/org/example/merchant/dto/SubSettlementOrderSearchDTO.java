package org.example.merchant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.example.common.dto.PageDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value = "子下发查询模型")
@Data
public class SubSettlementOrderSearchDTO extends PageDTO {

    @ApiModelProperty(value = "币别" )
    private String currency;

    @ApiModelProperty(value = "群组id")
    private Integer cardGroupId;

    @ApiModelProperty(value = "商户id")
    private Integer[] merchantId;

    @ApiModelProperty(value = "第三方id tpi100_id" )
    private Integer tpi100Id;

    @ApiModelProperty(value = "银行id（card_id）  " )
    private Integer cardId;

    @ApiModelProperty(value = "状态" )
    private Integer[] status;

    @ApiModelProperty(value = "出款方式（vnd_payment_method）" )
    private String[] vndPaymentMethod;

    @ApiModelProperty(value = "OTP vnd_otp" )
    private String[] vndOtp;

    @ApiModelProperty(value = "创建开始时间 created_at" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAtBeginTime;

    @ApiModelProperty(value = "创建结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAtAtEndTime;

    @ApiModelProperty(value = "更新时间 updated_at" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAtBeginTime;

    @ApiModelProperty(value = "更新结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAtEndTime;

    @ApiModelProperty(value = "银行卡id（from）" )
    private Integer bc100ID;

    @ApiModelProperty(value = "to 名称" )
    private String toMan;

    @ApiModelProperty(value = "主下发id" )
    private Integer fxId;

    @ApiModelProperty(value = "子下发id" )
    private Integer fx110ID;

    @ApiModelProperty(value = "商户订单号" )
    private String commandId;

    @ApiModelProperty(value = "附言(description)" )
    private String description;

}
