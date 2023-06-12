package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transfer_内转订单
 * @TableName system_introversion_order
 */
@TableName(value ="system_introversion_order")
@Data
public class SystemIntroversionOrder implements Serializable {
    /**
     * 中转ID
     */
    @TableId(type = IdType.AUTO)
    private Object trId;

    /**
     * 参考编号
     */
    private String reference;

    /**
     * 银行卡ID (from)
     */
    private Object fromBankCardId;

    /**
     * 银行卡ID (to)
     */
    private Object toBankCardId;

    /**
     * 用户发起金额
     */
    private BigDecimal requestAmount;

    /**
     * 实际作用金额
     */
    private BigDecimal paidAmount;

    /**
     * 系统手续费
     */
    private BigDecimal rate;

    /**
     * 银行手续费
     */
    private BigDecimal bankFee;

    /**
     * 余额 (from)
     */
    private BigDecimal balanceFrom;

    /**
     * 交易前余额 (from)
     */
    private BigDecimal preBalanceFrom;

    /**
     * 讯银余额 (from)
     */
    private BigDecimal balanceFromXy;

    /**
     * 讯银交易前余额 (from)
     */
    private BigDecimal preBalanceFromXy;

    /**
     * 余额 (to)
     */
    private BigDecimal balanceTo;

    /**
     * 交易前余额 (to)
     */
    private BigDecimal preBalanceTo;

    /**
     * 讯银余额 (to)
     */
    private BigDecimal balanceToXy;

    /**
     * 讯银交易前余额 (to)
     */
    private BigDecimal preBalanceToXy;

    /**
     * 订单完成时间
     */
    private Date completedAt;

    /**
     * 币别
     */
    private String currency;

    /**
     * 讯银步骤
     */
    private String step;

    /**
     * 迅银回传当前状态（文字）
     */
    private String message;

    /**
     * "手动操作状态
                                                                成功 Uploaded
                                                                失败 Unknown Error After Otp
                                                                BS200"
     */
    private Integer action;

    /**
     * 备注
     */
    private String note;

    /**
     * U盾token input
     */
    private String tokenInput;

    /**
     * 我们的单号
     */
    private String commandId;

    /**
     * 出款虚拟账单ID
     */
    private Object fromVsId;

    /**
     * 收款虚拟账单ID
     */
    private Object toVsId;

    /**
     * 执行次数
     */
    private Integer retryTimes;

    /**
     * "讯银回传状态
                                                                1.Pending–等待机器人跑
                                                                2.Processing–机器人正在跑
                                                                3.Completed–成功交易
                                                                4.Manual process–失败后的手动操作
                                                                人工可操作成成功或失败（如果交易试成功的）
                                                                5.Failed–失败, 不可更改状态
                                                                BS200"
     */
    private Integer status;

    /**
     * "type 转移类型
                                                            1 FI to FO
                                                            2 FI to FX
                                                            3 FI to TR
                                                            4 TR to FO
                                                            5 TR to FX""
                                                            BS200"
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
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 当地创建时间
     */
    private Date localCreatedAt;

    /**
     * 当地创建时间
     */
    private Date localUpdatedAt;

    /**
     * OTP发起时间
     */
    private Date receivedotptime;

    /**
     * OTP倒数时间
     */
    private Integer otptimer;

    /**
     * OPT验证码
     */
    private String refCode;

    /**
     * 银行流水单号
     */
    private String bankreferenceno;

    /**
     * 状态0：未送出过1：已送出过
     */
    private String isRefCode;

    /**
     * 是否手动添加  1.是
     */
    private Integer isEntry;

    /**
     * VND OTP
     */
    private String vndOtp;

    /**
     * VND 出款方式
     */
    private String vndPaymentMethod;

    /**
     * 
     */
    private String postscript;

    /**
     * 服务器ID
     */
    private Object serverId;

    /**
     * TR 自动转帐
     */
    private Integer trAuto;

    /**
     * 
     */
    private Object altVpnId;

    /**
     * 重新整理点击次数
     */
    private Integer runmonNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SystemIntroversionOrder other = (SystemIntroversionOrder) that;
        return (this.getTrId() == null ? other.getTrId() == null : this.getTrId().equals(other.getTrId()))
            && (this.getReference() == null ? other.getReference() == null : this.getReference().equals(other.getReference()))
            && (this.getFromBankCardId() == null ? other.getFromBankCardId() == null : this.getFromBankCardId().equals(other.getFromBankCardId()))
            && (this.getToBankCardId() == null ? other.getToBankCardId() == null : this.getToBankCardId().equals(other.getToBankCardId()))
            && (this.getRequestAmount() == null ? other.getRequestAmount() == null : this.getRequestAmount().equals(other.getRequestAmount()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getBankFee() == null ? other.getBankFee() == null : this.getBankFee().equals(other.getBankFee()))
            && (this.getBalanceFrom() == null ? other.getBalanceFrom() == null : this.getBalanceFrom().equals(other.getBalanceFrom()))
            && (this.getPreBalanceFrom() == null ? other.getPreBalanceFrom() == null : this.getPreBalanceFrom().equals(other.getPreBalanceFrom()))
            && (this.getBalanceFromXy() == null ? other.getBalanceFromXy() == null : this.getBalanceFromXy().equals(other.getBalanceFromXy()))
            && (this.getPreBalanceFromXy() == null ? other.getPreBalanceFromXy() == null : this.getPreBalanceFromXy().equals(other.getPreBalanceFromXy()))
            && (this.getBalanceTo() == null ? other.getBalanceTo() == null : this.getBalanceTo().equals(other.getBalanceTo()))
            && (this.getPreBalanceTo() == null ? other.getPreBalanceTo() == null : this.getPreBalanceTo().equals(other.getPreBalanceTo()))
            && (this.getBalanceToXy() == null ? other.getBalanceToXy() == null : this.getBalanceToXy().equals(other.getBalanceToXy()))
            && (this.getPreBalanceToXy() == null ? other.getPreBalanceToXy() == null : this.getPreBalanceToXy().equals(other.getPreBalanceToXy()))
            && (this.getCompletedAt() == null ? other.getCompletedAt() == null : this.getCompletedAt().equals(other.getCompletedAt()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getStep() == null ? other.getStep() == null : this.getStep().equals(other.getStep()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getTokenInput() == null ? other.getTokenInput() == null : this.getTokenInput().equals(other.getTokenInput()))
            && (this.getCommandId() == null ? other.getCommandId() == null : this.getCommandId().equals(other.getCommandId()))
            && (this.getFromVsId() == null ? other.getFromVsId() == null : this.getFromVsId().equals(other.getFromVsId()))
            && (this.getToVsId() == null ? other.getToVsId() == null : this.getToVsId().equals(other.getToVsId()))
            && (this.getRetryTimes() == null ? other.getRetryTimes() == null : this.getRetryTimes().equals(other.getRetryTimes()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getLocalCreatedAt() == null ? other.getLocalCreatedAt() == null : this.getLocalCreatedAt().equals(other.getLocalCreatedAt()))
            && (this.getLocalUpdatedAt() == null ? other.getLocalUpdatedAt() == null : this.getLocalUpdatedAt().equals(other.getLocalUpdatedAt()))
            && (this.getReceivedotptime() == null ? other.getReceivedotptime() == null : this.getReceivedotptime().equals(other.getReceivedotptime()))
            && (this.getOtptimer() == null ? other.getOtptimer() == null : this.getOtptimer().equals(other.getOtptimer()))
            && (this.getRefCode() == null ? other.getRefCode() == null : this.getRefCode().equals(other.getRefCode()))
            && (this.getBankreferenceno() == null ? other.getBankreferenceno() == null : this.getBankreferenceno().equals(other.getBankreferenceno()))
            && (this.getIsRefCode() == null ? other.getIsRefCode() == null : this.getIsRefCode().equals(other.getIsRefCode()))
            && (this.getIsEntry() == null ? other.getIsEntry() == null : this.getIsEntry().equals(other.getIsEntry()))
            && (this.getVndOtp() == null ? other.getVndOtp() == null : this.getVndOtp().equals(other.getVndOtp()))
            && (this.getVndPaymentMethod() == null ? other.getVndPaymentMethod() == null : this.getVndPaymentMethod().equals(other.getVndPaymentMethod()))
            && (this.getPostscript() == null ? other.getPostscript() == null : this.getPostscript().equals(other.getPostscript()))
            && (this.getServerId() == null ? other.getServerId() == null : this.getServerId().equals(other.getServerId()))
            && (this.getTrAuto() == null ? other.getTrAuto() == null : this.getTrAuto().equals(other.getTrAuto()))
            && (this.getAltVpnId() == null ? other.getAltVpnId() == null : this.getAltVpnId().equals(other.getAltVpnId()))
            && (this.getRunmonNumber() == null ? other.getRunmonNumber() == null : this.getRunmonNumber().equals(other.getRunmonNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTrId() == null) ? 0 : getTrId().hashCode());
        result = prime * result + ((getReference() == null) ? 0 : getReference().hashCode());
        result = prime * result + ((getFromBankCardId() == null) ? 0 : getFromBankCardId().hashCode());
        result = prime * result + ((getToBankCardId() == null) ? 0 : getToBankCardId().hashCode());
        result = prime * result + ((getRequestAmount() == null) ? 0 : getRequestAmount().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getBankFee() == null) ? 0 : getBankFee().hashCode());
        result = prime * result + ((getBalanceFrom() == null) ? 0 : getBalanceFrom().hashCode());
        result = prime * result + ((getPreBalanceFrom() == null) ? 0 : getPreBalanceFrom().hashCode());
        result = prime * result + ((getBalanceFromXy() == null) ? 0 : getBalanceFromXy().hashCode());
        result = prime * result + ((getPreBalanceFromXy() == null) ? 0 : getPreBalanceFromXy().hashCode());
        result = prime * result + ((getBalanceTo() == null) ? 0 : getBalanceTo().hashCode());
        result = prime * result + ((getPreBalanceTo() == null) ? 0 : getPreBalanceTo().hashCode());
        result = prime * result + ((getBalanceToXy() == null) ? 0 : getBalanceToXy().hashCode());
        result = prime * result + ((getPreBalanceToXy() == null) ? 0 : getPreBalanceToXy().hashCode());
        result = prime * result + ((getCompletedAt() == null) ? 0 : getCompletedAt().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getStep() == null) ? 0 : getStep().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getTokenInput() == null) ? 0 : getTokenInput().hashCode());
        result = prime * result + ((getCommandId() == null) ? 0 : getCommandId().hashCode());
        result = prime * result + ((getFromVsId() == null) ? 0 : getFromVsId().hashCode());
        result = prime * result + ((getToVsId() == null) ? 0 : getToVsId().hashCode());
        result = prime * result + ((getRetryTimes() == null) ? 0 : getRetryTimes().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLocalCreatedAt() == null) ? 0 : getLocalCreatedAt().hashCode());
        result = prime * result + ((getLocalUpdatedAt() == null) ? 0 : getLocalUpdatedAt().hashCode());
        result = prime * result + ((getReceivedotptime() == null) ? 0 : getReceivedotptime().hashCode());
        result = prime * result + ((getOtptimer() == null) ? 0 : getOtptimer().hashCode());
        result = prime * result + ((getRefCode() == null) ? 0 : getRefCode().hashCode());
        result = prime * result + ((getBankreferenceno() == null) ? 0 : getBankreferenceno().hashCode());
        result = prime * result + ((getIsRefCode() == null) ? 0 : getIsRefCode().hashCode());
        result = prime * result + ((getIsEntry() == null) ? 0 : getIsEntry().hashCode());
        result = prime * result + ((getVndOtp() == null) ? 0 : getVndOtp().hashCode());
        result = prime * result + ((getVndPaymentMethod() == null) ? 0 : getVndPaymentMethod().hashCode());
        result = prime * result + ((getPostscript() == null) ? 0 : getPostscript().hashCode());
        result = prime * result + ((getServerId() == null) ? 0 : getServerId().hashCode());
        result = prime * result + ((getTrAuto() == null) ? 0 : getTrAuto().hashCode());
        result = prime * result + ((getAltVpnId() == null) ? 0 : getAltVpnId().hashCode());
        result = prime * result + ((getRunmonNumber() == null) ? 0 : getRunmonNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", trId=").append(trId);
        sb.append(", reference=").append(reference);
        sb.append(", fromBankCardId=").append(fromBankCardId);
        sb.append(", toBankCardId=").append(toBankCardId);
        sb.append(", requestAmount=").append(requestAmount);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", rate=").append(rate);
        sb.append(", bankFee=").append(bankFee);
        sb.append(", balanceFrom=").append(balanceFrom);
        sb.append(", preBalanceFrom=").append(preBalanceFrom);
        sb.append(", balanceFromXy=").append(balanceFromXy);
        sb.append(", preBalanceFromXy=").append(preBalanceFromXy);
        sb.append(", balanceTo=").append(balanceTo);
        sb.append(", preBalanceTo=").append(preBalanceTo);
        sb.append(", balanceToXy=").append(balanceToXy);
        sb.append(", preBalanceToXy=").append(preBalanceToXy);
        sb.append(", completedAt=").append(completedAt);
        sb.append(", currency=").append(currency);
        sb.append(", step=").append(step);
        sb.append(", message=").append(message);
        sb.append(", action=").append(action);
        sb.append(", note=").append(note);
        sb.append(", tokenInput=").append(tokenInput);
        sb.append(", commandId=").append(commandId);
        sb.append(", fromVsId=").append(fromVsId);
        sb.append(", toVsId=").append(toVsId);
        sb.append(", retryTimes=").append(retryTimes);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", creator=").append(creator);
        sb.append(", updater=").append(updater);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", localCreatedAt=").append(localCreatedAt);
        sb.append(", localUpdatedAt=").append(localUpdatedAt);
        sb.append(", receivedotptime=").append(receivedotptime);
        sb.append(", otptimer=").append(otptimer);
        sb.append(", refCode=").append(refCode);
        sb.append(", bankreferenceno=").append(bankreferenceno);
        sb.append(", isRefCode=").append(isRefCode);
        sb.append(", isEntry=").append(isEntry);
        sb.append(", vndOtp=").append(vndOtp);
        sb.append(", vndPaymentMethod=").append(vndPaymentMethod);
        sb.append(", postscript=").append(postscript);
        sb.append(", serverId=").append(serverId);
        sb.append(", trAuto=").append(trAuto);
        sb.append(", altVpnId=").append(altVpnId);
        sb.append(", runmonNumber=").append(runmonNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}