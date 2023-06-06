package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SystemWithdrawalOrderDto {

    /**
     * 银行id
     */
    @JsonProperty("BK100_ID")
    private Long bk100Id;

    /**
     * 系统商户表id
     */
    @JsonProperty("SH100_ID")
    private List<Integer> sh100Ids;

    /**
     * 拼接主键id
     */
    @JsonProperty("alt_id")
    private String altId;

    /**
     * 子代付表的id 从子代付中找到代付
     */
    @JsonProperty("FO110_alt_id")
    private String fo110AltId;

    /**
     * 参考编号
     */
    @JsonProperty("reference")
    private String reference;

    /**
     * 账户代码
     */
    @JsonProperty("from")
    private String from;

    /**
     * accountCode 接收的银行账户代码
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

    public String getCurrency() {
        return currency;
    }

    /**
     * 状态
     */
    @JsonProperty("status")
    private List<Integer> statuses;

    /**
     * 通知状态
     */
    @JsonProperty("ON100_status")
    private Integer on100Status;

    /**
     * 每页显示条数
     */
    @JsonProperty("rp")
    private Integer rp;

    /**
     * 请求金额
     */
    @JsonProperty("request_amount")
    private List<BigDecimal> requestAmounts;

    /**
     * 实际作用金额
     */
    @JsonProperty("paid_amount")
    private List<BigDecimal> paidAmounts;

    /**
     * 当前页码
     */
    @JsonProperty("page")
    private Integer page;

    /**
     * 账户确认
     */
    @JsonProperty("confirm_accname")
    private Integer confirmAccname;

    /**
     * 这个字段已经没有了
     */
    @JsonProperty("driver")
    private String driver;

    /**
     * 银行卡账户群组id 商户表中有
     */
    @JsonProperty("PG100_ID")
    private Long pg100Id;

    /**
     * 创建日期开始
     */
    @JsonProperty("start_date")
    private LocalDateTime startDate;

    /**
     * 创建日期结束
     */
    @JsonProperty("end_date")
    private LocalDateTime endDate;

    /**
     * 成功日期开始
     */
    @JsonProperty("completed_start_time")
    private LocalDateTime completedStartTime;

    /**
     * 成功日期结束
     */
    @JsonProperty("completed_end_time")
    private LocalDateTime completedEndTime;

    /**
     * 更新日期开始
     */
    @JsonProperty("updated_start_date")
    private LocalDateTime updatedStartDate;

    /**
     * 更新日期结束
     */
    @JsonProperty("updated_end_date")
    private LocalDateTime updatedEndDate;

}
