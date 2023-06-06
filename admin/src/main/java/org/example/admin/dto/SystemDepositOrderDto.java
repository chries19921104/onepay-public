package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SystemDepositOrderDto {

    @JsonProperty("from_bank")
    private String fromBank;

    /**
     * 银行id
     */
    @JsonProperty("bank_id")
    private Integer bk100Id;

    /**
     * 系统商户表id
     */
    @JsonProperty("merchant_id")
    private Integer[] sh100Ids;

    /**
     * 拼接主键
     */
    @JsonProperty("alt_id")
    private Integer altId;

    /**
     * 商户订单号
     */
    @JsonProperty("command_id")
    private Integer reference;

    /**
     * 转账的银行
     */
    @JsonProperty("from")
    private String from;

    /**
     * 接收的银行
     */
    @JsonProperty("to")
    private String to;

    @JsonProperty("beneficiary")
    private String beneficiary;

    /**
     * 币别
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * 状态
     */
    @JsonProperty("status")
    private Integer[] statusList;

    /**
     * 收款方式
     */
    @JsonProperty("payment_mode")
    private Integer txnMode;

    @JsonProperty("driver")
    private String driver;

    @JsonProperty("tpi_txn_mode")
    private String tpiTxnMode;

    /**
     * status
     */
    @JsonProperty("ON100_status")
    private Integer on100Status;

    /**
     * 信息
     */
    @JsonProperty("message")
    private String message;

    /**
     * 对账用唯一码，附言
     */
    @JsonProperty("postscript")
    private String postscript;

    /**
     * 查询VB id是否存在，存在为1，不存在为0
     */
    @JsonProperty("pairing_by_VBS")
    private Integer pairingByVbs;

    /**
     * 查询SM id是否存在，存在为1，不存在为0
     */
    @JsonProperty("pairing_by_SMS")
    private Integer pairingBySms;

    @JsonProperty("vnd_otp")
    private String vndOtp;

    /**
     * 每页显示条数
     */
    @JsonProperty("rp")
    private Integer rp;

    /**
     * 因为比较订单时为防止重复会加上一定的偏移量，所以存在区间
     * 订单金额区间
     */
    @JsonProperty("order_amount")
    private String[] orderAmount;

    @JsonProperty("request_amount")
    private String[] requestAmount;

    @JsonProperty("hide_no_card")
    private Integer hideNoCard;

    @JsonProperty("paid_amount")
    private String[] paidAmount;

    /**
     * 页码
     */
    @JsonProperty("page")
    private Integer page;

    /**
     * 银行卡账户群组id
     */
    @JsonProperty("PG100_ID")
    private Integer pg100Id;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("updated_start_date")
    private String updatedStartDate;

    @JsonProperty("updated_end_date")
    private String updatedEndDate;

    @JsonProperty("completed_start_time")
    private String completedStartDate;

    @JsonProperty("completed_end_time")
    private String completedEndDate;

    /**
     * 虚拟银行对账单id
     */
    @JsonProperty("vb_id")
    private Integer vb100Id;

    /**
     * SMS Monitor短信监控id
     */
    @JsonProperty("sm_id")
    private Integer sm100Id;
}
