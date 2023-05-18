package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Settlement_子下发订单
 * @TableName system_sub_settlement_order
 */
@TableName(value ="system_sub_settlement_order")
@Data
public class SystemSubSettlementOrder implements Serializable {
    /**
     * 子下发ID
     */
    @TableId(type = IdType.AUTO)
    private Integer fx110Id;

    /**
     * 下发ID
     */
    private Integer fx100Id;

    /**
     * 过往子下发ID
     */
    private Integer fx110Preid;

    /**
     * 银行卡ID (from)
     */
    private Integer bc100Id;

    /**
     * 第三方串接ID
     */
    private Integer tpi100Id;

    /**
     * 交易种类
     */
    private Integer txnMode;

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
     * 第三方下發银行手续费 壹支付承担
     */
    private BigDecimal tpiBankFee;

    /**
     * 余额 (from)
     */
    private BigDecimal balance;

    /**
     * 交易前余额 (from)
     */
    private BigDecimal preBalance;

    /**
     * 讯银余额 (from)
     */
    private BigDecimal balanceXy;

    /**
     * 讯银交易前余额 (from)
     */
    private BigDecimal preBalanceXy;

    /**
     * SH110余额
     */
    private BigDecimal sh110Balance;

    /**
     * 币别
     */
    private String currency;

    /**
     * 讯银步骤
     */
    private String step;

    /**
     * 执行次数
     */
    private Integer retryTimes;

    /**
     * 是否点选过按钮
     */
    private Integer isPressButton;

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
     * "手动操作状态: BS200
                                                                    1 Uploaded(成功)
                                                                    0 Unknown Error After Otp(失败)"
     */
    private Integer action;

    /**
     * 备注
     */
    private String message;

    /**
     * 我们的单号
     */
    private String commandId;

    /**
     * 
     */
    private Integer vb100Id;

    /**
     * OTP发起时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date receivedotptime;

    /**
     * OTP倒数时间
     */
    private Integer otptimer;

    /**
     * 
     */
    private String refCode;

    /**
     * 
     */
    private String bankreferenceno;

    /**
     * 状态0：未送出过1：已送出过
     */
    private Integer isRefCode;

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
     * 备注
     */
    private String note;

    /**
     * U盾token input
     */
    private String tokenInput;

    /**
     * 
     */
    private String xyToMan;

    /**
     * 创建人员
     */
    private String createdMan;

    /**
     * 更新人员
     */
    private String updatedMan;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdAt;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatedAt;

    /**
     * 当地创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date localCreatedAt;

    /**
     * 当地创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date localUpdatedAt;

    /**
     * 
     */
    private Integer vpn100Id;

    /**
     * 
     */
    private Integer altVpnId;

    /**
     * 码农ID
     */
    private Integer mn100Id;

    /**
     * 码农卡片ID
     */
    private Integer mn120Id;

    /**
     * 回到市场次数
     */
    private Integer isLateTimes;

    /**
     * 异常单 0.非异常,1.异常,2.已解决
     */
    private Integer isWeird;

    /**
     * 描述
     */
    private String description;

    /**
     * 外部描述
     */
    private String externalDescription;

    /**
     * 催单 0.无动作,1.进行催单
     */
    private Integer isUrgent;

    /**
     * 收据，码农上传水单，财务审核通过才上分
     */
    private String receipt;

    /**
     * 自身返点
     */
    private BigDecimal keepPoint;

    /**
     * 上级返点
     */
    private BigDecimal superiorKeepPoint;

    /**
     * 上级MN100ID
     */
    private Integer superiorMn100Id;

    /**
     * 冻结黑马币
     */
    private BigDecimal frozenAvailableBalance;

    /**
     * 冻结保证金
     */
    private BigDecimal frozenGuaranteeBalance;

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
        SystemSubSettlementOrder other = (SystemSubSettlementOrder) that;
        return (this.getFx110Id() == null ? other.getFx110Id() == null : this.getFx110Id().equals(other.getFx110Id()))
            && (this.getFx100Id() == null ? other.getFx100Id() == null : this.getFx100Id().equals(other.getFx100Id()))
            && (this.getFx110Preid() == null ? other.getFx110Preid() == null : this.getFx110Preid().equals(other.getFx110Preid()))
            && (this.getBc100Id() == null ? other.getBc100Id() == null : this.getBc100Id().equals(other.getBc100Id()))
            && (this.getTpi100Id() == null ? other.getTpi100Id() == null : this.getTpi100Id().equals(other.getTpi100Id()))
            && (this.getTxnMode() == null ? other.getTxnMode() == null : this.getTxnMode().equals(other.getTxnMode()))
            && (this.getRequestAmount() == null ? other.getRequestAmount() == null : this.getRequestAmount().equals(other.getRequestAmount()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getBankFee() == null ? other.getBankFee() == null : this.getBankFee().equals(other.getBankFee()))
            && (this.getTpiBankFee() == null ? other.getTpiBankFee() == null : this.getTpiBankFee().equals(other.getTpiBankFee()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getPreBalance() == null ? other.getPreBalance() == null : this.getPreBalance().equals(other.getPreBalance()))
            && (this.getBalanceXy() == null ? other.getBalanceXy() == null : this.getBalanceXy().equals(other.getBalanceXy()))
            && (this.getPreBalanceXy() == null ? other.getPreBalanceXy() == null : this.getPreBalanceXy().equals(other.getPreBalanceXy()))
            && (this.getSh110Balance() == null ? other.getSh110Balance() == null : this.getSh110Balance().equals(other.getSh110Balance()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getStep() == null ? other.getStep() == null : this.getStep().equals(other.getStep()))
            && (this.getRetryTimes() == null ? other.getRetryTimes() == null : this.getRetryTimes().equals(other.getRetryTimes()))
            && (this.getIsPressButton() == null ? other.getIsPressButton() == null : this.getIsPressButton().equals(other.getIsPressButton()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getCommandId() == null ? other.getCommandId() == null : this.getCommandId().equals(other.getCommandId()))
            && (this.getVb100Id() == null ? other.getVb100Id() == null : this.getVb100Id().equals(other.getVb100Id()))
            && (this.getReceivedotptime() == null ? other.getReceivedotptime() == null : this.getReceivedotptime().equals(other.getReceivedotptime()))
            && (this.getOtptimer() == null ? other.getOtptimer() == null : this.getOtptimer().equals(other.getOtptimer()))
            && (this.getRefCode() == null ? other.getRefCode() == null : this.getRefCode().equals(other.getRefCode()))
            && (this.getBankreferenceno() == null ? other.getBankreferenceno() == null : this.getBankreferenceno().equals(other.getBankreferenceno()))
            && (this.getIsRefCode() == null ? other.getIsRefCode() == null : this.getIsRefCode().equals(other.getIsRefCode()))
            && (this.getIsEntry() == null ? other.getIsEntry() == null : this.getIsEntry().equals(other.getIsEntry()))
            && (this.getVndOtp() == null ? other.getVndOtp() == null : this.getVndOtp().equals(other.getVndOtp()))
            && (this.getVndPaymentMethod() == null ? other.getVndPaymentMethod() == null : this.getVndPaymentMethod().equals(other.getVndPaymentMethod()))
            && (this.getPostscript() == null ? other.getPostscript() == null : this.getPostscript().equals(other.getPostscript()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getTokenInput() == null ? other.getTokenInput() == null : this.getTokenInput().equals(other.getTokenInput()))
            && (this.getXyToMan() == null ? other.getXyToMan() == null : this.getXyToMan().equals(other.getXyToMan()))
            && (this.getCreatedMan() == null ? other.getCreatedMan() == null : this.getCreatedMan().equals(other.getCreatedMan()))
            && (this.getUpdatedMan() == null ? other.getUpdatedMan() == null : this.getUpdatedMan().equals(other.getUpdatedMan()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getLocalCreatedAt() == null ? other.getLocalCreatedAt() == null : this.getLocalCreatedAt().equals(other.getLocalCreatedAt()))
            && (this.getLocalUpdatedAt() == null ? other.getLocalUpdatedAt() == null : this.getLocalUpdatedAt().equals(other.getLocalUpdatedAt()))
            && (this.getVpn100Id() == null ? other.getVpn100Id() == null : this.getVpn100Id().equals(other.getVpn100Id()))
            && (this.getAltVpnId() == null ? other.getAltVpnId() == null : this.getAltVpnId().equals(other.getAltVpnId()))
            && (this.getMn100Id() == null ? other.getMn100Id() == null : this.getMn100Id().equals(other.getMn100Id()))
            && (this.getMn120Id() == null ? other.getMn120Id() == null : this.getMn120Id().equals(other.getMn120Id()))
            && (this.getIsLateTimes() == null ? other.getIsLateTimes() == null : this.getIsLateTimes().equals(other.getIsLateTimes()))
            && (this.getIsWeird() == null ? other.getIsWeird() == null : this.getIsWeird().equals(other.getIsWeird()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getExternalDescription() == null ? other.getExternalDescription() == null : this.getExternalDescription().equals(other.getExternalDescription()))
            && (this.getIsUrgent() == null ? other.getIsUrgent() == null : this.getIsUrgent().equals(other.getIsUrgent()))
            && (this.getReceipt() == null ? other.getReceipt() == null : this.getReceipt().equals(other.getReceipt()))
            && (this.getKeepPoint() == null ? other.getKeepPoint() == null : this.getKeepPoint().equals(other.getKeepPoint()))
            && (this.getSuperiorKeepPoint() == null ? other.getSuperiorKeepPoint() == null : this.getSuperiorKeepPoint().equals(other.getSuperiorKeepPoint()))
            && (this.getSuperiorMn100Id() == null ? other.getSuperiorMn100Id() == null : this.getSuperiorMn100Id().equals(other.getSuperiorMn100Id()))
            && (this.getFrozenAvailableBalance() == null ? other.getFrozenAvailableBalance() == null : this.getFrozenAvailableBalance().equals(other.getFrozenAvailableBalance()))
            && (this.getFrozenGuaranteeBalance() == null ? other.getFrozenGuaranteeBalance() == null : this.getFrozenGuaranteeBalance().equals(other.getFrozenGuaranteeBalance()))
            && (this.getRunmonNumber() == null ? other.getRunmonNumber() == null : this.getRunmonNumber().equals(other.getRunmonNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFx110Id() == null) ? 0 : getFx110Id().hashCode());
        result = prime * result + ((getFx100Id() == null) ? 0 : getFx100Id().hashCode());
        result = prime * result + ((getFx110Preid() == null) ? 0 : getFx110Preid().hashCode());
        result = prime * result + ((getBc100Id() == null) ? 0 : getBc100Id().hashCode());
        result = prime * result + ((getTpi100Id() == null) ? 0 : getTpi100Id().hashCode());
        result = prime * result + ((getTxnMode() == null) ? 0 : getTxnMode().hashCode());
        result = prime * result + ((getRequestAmount() == null) ? 0 : getRequestAmount().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getBankFee() == null) ? 0 : getBankFee().hashCode());
        result = prime * result + ((getTpiBankFee() == null) ? 0 : getTpiBankFee().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getPreBalance() == null) ? 0 : getPreBalance().hashCode());
        result = prime * result + ((getBalanceXy() == null) ? 0 : getBalanceXy().hashCode());
        result = prime * result + ((getPreBalanceXy() == null) ? 0 : getPreBalanceXy().hashCode());
        result = prime * result + ((getSh110Balance() == null) ? 0 : getSh110Balance().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getStep() == null) ? 0 : getStep().hashCode());
        result = prime * result + ((getRetryTimes() == null) ? 0 : getRetryTimes().hashCode());
        result = prime * result + ((getIsPressButton() == null) ? 0 : getIsPressButton().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getCommandId() == null) ? 0 : getCommandId().hashCode());
        result = prime * result + ((getVb100Id() == null) ? 0 : getVb100Id().hashCode());
        result = prime * result + ((getReceivedotptime() == null) ? 0 : getReceivedotptime().hashCode());
        result = prime * result + ((getOtptimer() == null) ? 0 : getOtptimer().hashCode());
        result = prime * result + ((getRefCode() == null) ? 0 : getRefCode().hashCode());
        result = prime * result + ((getBankreferenceno() == null) ? 0 : getBankreferenceno().hashCode());
        result = prime * result + ((getIsRefCode() == null) ? 0 : getIsRefCode().hashCode());
        result = prime * result + ((getIsEntry() == null) ? 0 : getIsEntry().hashCode());
        result = prime * result + ((getVndOtp() == null) ? 0 : getVndOtp().hashCode());
        result = prime * result + ((getVndPaymentMethod() == null) ? 0 : getVndPaymentMethod().hashCode());
        result = prime * result + ((getPostscript() == null) ? 0 : getPostscript().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getTokenInput() == null) ? 0 : getTokenInput().hashCode());
        result = prime * result + ((getXyToMan() == null) ? 0 : getXyToMan().hashCode());
        result = prime * result + ((getCreatedMan() == null) ? 0 : getCreatedMan().hashCode());
        result = prime * result + ((getUpdatedMan() == null) ? 0 : getUpdatedMan().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLocalCreatedAt() == null) ? 0 : getLocalCreatedAt().hashCode());
        result = prime * result + ((getLocalUpdatedAt() == null) ? 0 : getLocalUpdatedAt().hashCode());
        result = prime * result + ((getVpn100Id() == null) ? 0 : getVpn100Id().hashCode());
        result = prime * result + ((getAltVpnId() == null) ? 0 : getAltVpnId().hashCode());
        result = prime * result + ((getMn100Id() == null) ? 0 : getMn100Id().hashCode());
        result = prime * result + ((getMn120Id() == null) ? 0 : getMn120Id().hashCode());
        result = prime * result + ((getIsLateTimes() == null) ? 0 : getIsLateTimes().hashCode());
        result = prime * result + ((getIsWeird() == null) ? 0 : getIsWeird().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getExternalDescription() == null) ? 0 : getExternalDescription().hashCode());
        result = prime * result + ((getIsUrgent() == null) ? 0 : getIsUrgent().hashCode());
        result = prime * result + ((getReceipt() == null) ? 0 : getReceipt().hashCode());
        result = prime * result + ((getKeepPoint() == null) ? 0 : getKeepPoint().hashCode());
        result = prime * result + ((getSuperiorKeepPoint() == null) ? 0 : getSuperiorKeepPoint().hashCode());
        result = prime * result + ((getSuperiorMn100Id() == null) ? 0 : getSuperiorMn100Id().hashCode());
        result = prime * result + ((getFrozenAvailableBalance() == null) ? 0 : getFrozenAvailableBalance().hashCode());
        result = prime * result + ((getFrozenGuaranteeBalance() == null) ? 0 : getFrozenGuaranteeBalance().hashCode());
        result = prime * result + ((getRunmonNumber() == null) ? 0 : getRunmonNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fx110Id=").append(fx110Id);
        sb.append(", fx100Id=").append(fx100Id);
        sb.append(", fx110Preid=").append(fx110Preid);
        sb.append(", bc100Id=").append(bc100Id);
        sb.append(", tpi100Id=").append(tpi100Id);
        sb.append(", txnMode=").append(txnMode);
        sb.append(", requestAmount=").append(requestAmount);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", rate=").append(rate);
        sb.append(", bankFee=").append(bankFee);
        sb.append(", tpiBankFee=").append(tpiBankFee);
        sb.append(", balance=").append(balance);
        sb.append(", preBalance=").append(preBalance);
        sb.append(", balanceXy=").append(balanceXy);
        sb.append(", preBalanceXy=").append(preBalanceXy);
        sb.append(", sh110Balance=").append(sh110Balance);
        sb.append(", currency=").append(currency);
        sb.append(", step=").append(step);
        sb.append(", retryTimes=").append(retryTimes);
        sb.append(", isPressButton=").append(isPressButton);
        sb.append(", status=").append(status);
        sb.append(", action=").append(action);
        sb.append(", message=").append(message);
        sb.append(", commandId=").append(commandId);
        sb.append(", vb100Id=").append(vb100Id);
        sb.append(", receivedotptime=").append(receivedotptime);
        sb.append(", otptimer=").append(otptimer);
        sb.append(", refCode=").append(refCode);
        sb.append(", bankreferenceno=").append(bankreferenceno);
        sb.append(", isRefCode=").append(isRefCode);
        sb.append(", isEntry=").append(isEntry);
        sb.append(", vndOtp=").append(vndOtp);
        sb.append(", vndPaymentMethod=").append(vndPaymentMethod);
        sb.append(", postscript=").append(postscript);
        sb.append(", note=").append(note);
        sb.append(", tokenInput=").append(tokenInput);
        sb.append(", xyToMan=").append(xyToMan);
        sb.append(", createdMan=").append(createdMan);
        sb.append(", updatedMan=").append(updatedMan);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", localCreatedAt=").append(localCreatedAt);
        sb.append(", localUpdatedAt=").append(localUpdatedAt);
        sb.append(", vpn100Id=").append(vpn100Id);
        sb.append(", altVpnId=").append(altVpnId);
        sb.append(", mn100Id=").append(mn100Id);
        sb.append(", mn120Id=").append(mn120Id);
        sb.append(", isLateTimes=").append(isLateTimes);
        sb.append(", isWeird=").append(isWeird);
        sb.append(", description=").append(description);
        sb.append(", externalDescription=").append(externalDescription);
        sb.append(", isUrgent=").append(isUrgent);
        sb.append(", receipt=").append(receipt);
        sb.append(", keepPoint=").append(keepPoint);
        sb.append(", superiorKeepPoint=").append(superiorKeepPoint);
        sb.append(", superiorMn100Id=").append(superiorMn100Id);
        sb.append(", frozenAvailableBalance=").append(frozenAvailableBalance);
        sb.append(", frozenGuaranteeBalance=").append(frozenGuaranteeBalance);
        sb.append(", runmonNumber=").append(runmonNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}