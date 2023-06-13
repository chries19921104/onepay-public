package org.example.common.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "外部交易订单")
public class SystemExternalTrabeOrder {
    /*
    主键id
     */
    private Long etId;
    /*
    银行卡id(from)
     */
    private Long bankCardId;
    /*
    to用户名称
     */
    private String toMan;
    /*
    to银行
     */
    private String toBank;
    /*
    to银行账户
     */
    private String toCardNumber;
    /*
    用户发起金额
     */
    private BigDecimal requestAmount;
    /*
    实际作用金额
     */
    private BigDecimal paidAmount;
    /*
    银行手续费
     */
    private BigDecimal bankFee;
    /*
    余额(from)
     */
    private BigDecimal balance;
    /*
    交易前余额(from)
     */
    private BigDecimal preBalance;
    /*
    讯银余额(from)
     */
    private BigDecimal balanceXy;
    /*
    讯银交易前余额(from)
     */
    private BigDecimal preBalanceXy;
    /*
    订单完成时间
     */
    private LocalDateTime completedAt;
    /*
    币别
     */
    private String currency;
    /*
    讯银步骤
     */
    private String step;
    /*
    执行次数
     */
    private Integer retryTimes;
    /*
    是否点选过按钮
     */
    private Integer isPressButton;
    /*
    订单类型
     */
    private Integer type;
    /*
    订单状态
     */
    private Integer status;
    /*
    关联VBS
     */
    private String vbSn;
    /*
    关联调整单
     */
    private Long adId;
    /*
    手动操作状态 1. reject BS200
     */
    private Integer action;
    /*
    备注
     */
    private String message;
    /*
    我们的单号
     */
    private String commandId;
    /*
    虚拟对账单主键Id
     */
    private Long virtualBankId;
    /*
    OTP发起时间
     */
    private LocalDateTime receivedOTPtime;
    /*
    OTP到数时间
     */
    private LocalDateTime otptimr;

    private String refCode;

    private String bankreferenceno;
    /*
    状态0:未送出过 1:已送出过
     */
    private Integer isRefCode;
    /*
    是否手动添加 1.是
     */
    private Integer isEntry;

    private String postscript;
    /*
    Transaction Code交易代码
     */
    private String trCode;
    /*
    VND OTP
     */
    private String vndOtp;
    /*
    VND出款方式
     */
    private String vndPaymentMethod;
    /*
    备注
     */
    private  String note;
    /*
    U盾token input
     */
    private String tokenInput;

    private String xyToMan;
    /*
    XY备注
     */
    private String description;
    /*
    审核账户名称0 预设(不需要审核) 1.待财务审核 2.待商户审核 3.拒绝 4.通过
     */
    private Integer confirmAccname;
    /*
    审核人员
     */
    private String confirmedMan;
    /*
    审核时间
     */
    private LocalDateTime confirmedAt;
    /*
    订单操作记录
     */
    private String actionLog;
    /*
    创建人员
     */
    private String createdMan;
    /*
    更新人员
     */
    private String updatedMan;
    /*
    创建时间
     */
    private LocalDateTime createdAt;
    /*
    更新时间
     */
    private LocalDateTime updatedAt;
    /*
    当地创建时间
     */
    private LocalDateTime localCreatedAt;
    /*
    当地更新时间
     */
    private LocalDateTime localUpdatedAt;
    /*
    服务器id
     */
    private Long serverID;

    private Long altVpnID;
    /*
    重新整理点击次数
     */
    private Integer runmonNumber;
}
