package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "商户订单表查询模型")
@Data
public class SystemMerchantStatementSearchDTO extends PageDTO {

    @ApiModelProperty(value = "币别")
    private String currency;

    @ApiModelProperty(value = "商户id")
    private String merchantId;

    @ApiModelProperty(value = "选择类型")
    private Integer[] type;

    @ApiModelProperty(value = "对账单id")
    private Integer id;

    @ApiModelProperty(value = "交易开始时间 trade_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "日期不能为空")
    private Date tradeTimeBeginTime;

    @ApiModelProperty(value = "交易结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "日期不能为空")
    private Date tradeTimeEndTime;


}
