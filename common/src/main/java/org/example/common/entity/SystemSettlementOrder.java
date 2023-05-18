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
 * Settlement_下发订单
 * @TableName system_settlement_order
 */
@TableName(value ="system_settlement_order")
@Data
public class SystemSettlementOrder implements Serializable {
    /**
     * 下发ID
     */
    @TableId(type = IdType.AUTO)
    private Integer fxId;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 参考编号
     */
    private String reference;

    /**
     * to用户名称
     */
    private String toMan;

    /**
     * to银行
     */
    private String toBank;

    /**
     * 支行
     */
    private String toBankBranch;

    /**
     * to银行账户
     */
    private String toCardNumber;

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
     * 订单完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date completedAt;

    /**
     * 币别
     */
    private String currency;

    /**
     * "根據 FO110 去判斷
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
     * 手动操作状态 1. reject BS200
     */
    private Integer action;

    /**
     * Transaction Code 交易代码
     */
    private String trCode;

    /**
     * 备注
     */
    private String note;

    /**
     * 批准方式: 1- 一般, 2- 略过
     */
    private Integer approveType;

    /**
     * 批准时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date approveTime;

    /**
     * 批准人
     */
    private String approvePerson;

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
     * MN100ID
     */
    private Integer mn100Id;

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
        SystemSettlementOrder other = (SystemSettlementOrder) that;
        return (this.getFxId() == null ? other.getFxId() == null : this.getFxId().equals(other.getFxId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getReference() == null ? other.getReference() == null : this.getReference().equals(other.getReference()))
            && (this.getToMan() == null ? other.getToMan() == null : this.getToMan().equals(other.getToMan()))
            && (this.getToBank() == null ? other.getToBank() == null : this.getToBank().equals(other.getToBank()))
            && (this.getToBankBranch() == null ? other.getToBankBranch() == null : this.getToBankBranch().equals(other.getToBankBranch()))
            && (this.getToCardNumber() == null ? other.getToCardNumber() == null : this.getToCardNumber().equals(other.getToCardNumber()))
            && (this.getRequestAmount() == null ? other.getRequestAmount() == null : this.getRequestAmount().equals(other.getRequestAmount()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getBankFee() == null ? other.getBankFee() == null : this.getBankFee().equals(other.getBankFee()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getPreBalance() == null ? other.getPreBalance() == null : this.getPreBalance().equals(other.getPreBalance()))
            && (this.getBalanceXy() == null ? other.getBalanceXy() == null : this.getBalanceXy().equals(other.getBalanceXy()))
            && (this.getPreBalanceXy() == null ? other.getPreBalanceXy() == null : this.getPreBalanceXy().equals(other.getPreBalanceXy()))
            && (this.getSh110Balance() == null ? other.getSh110Balance() == null : this.getSh110Balance().equals(other.getSh110Balance()))
            && (this.getCompletedAt() == null ? other.getCompletedAt() == null : this.getCompletedAt().equals(other.getCompletedAt()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getTrCode() == null ? other.getTrCode() == null : this.getTrCode().equals(other.getTrCode()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getApproveType() == null ? other.getApproveType() == null : this.getApproveType().equals(other.getApproveType()))
            && (this.getApproveTime() == null ? other.getApproveTime() == null : this.getApproveTime().equals(other.getApproveTime()))
            && (this.getApprovePerson() == null ? other.getApprovePerson() == null : this.getApprovePerson().equals(other.getApprovePerson()))
            && (this.getCreatedMan() == null ? other.getCreatedMan() == null : this.getCreatedMan().equals(other.getCreatedMan()))
            && (this.getUpdatedMan() == null ? other.getUpdatedMan() == null : this.getUpdatedMan().equals(other.getUpdatedMan()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getLocalCreatedAt() == null ? other.getLocalCreatedAt() == null : this.getLocalCreatedAt().equals(other.getLocalCreatedAt()))
            && (this.getLocalUpdatedAt() == null ? other.getLocalUpdatedAt() == null : this.getLocalUpdatedAt().equals(other.getLocalUpdatedAt()))
            && (this.getMn100Id() == null ? other.getMn100Id() == null : this.getMn100Id().equals(other.getMn100Id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFxId() == null) ? 0 : getFxId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getReference() == null) ? 0 : getReference().hashCode());
        result = prime * result + ((getToMan() == null) ? 0 : getToMan().hashCode());
        result = prime * result + ((getToBank() == null) ? 0 : getToBank().hashCode());
        result = prime * result + ((getToBankBranch() == null) ? 0 : getToBankBranch().hashCode());
        result = prime * result + ((getToCardNumber() == null) ? 0 : getToCardNumber().hashCode());
        result = prime * result + ((getRequestAmount() == null) ? 0 : getRequestAmount().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getBankFee() == null) ? 0 : getBankFee().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getPreBalance() == null) ? 0 : getPreBalance().hashCode());
        result = prime * result + ((getBalanceXy() == null) ? 0 : getBalanceXy().hashCode());
        result = prime * result + ((getPreBalanceXy() == null) ? 0 : getPreBalanceXy().hashCode());
        result = prime * result + ((getSh110Balance() == null) ? 0 : getSh110Balance().hashCode());
        result = prime * result + ((getCompletedAt() == null) ? 0 : getCompletedAt().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getTrCode() == null) ? 0 : getTrCode().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getApproveType() == null) ? 0 : getApproveType().hashCode());
        result = prime * result + ((getApproveTime() == null) ? 0 : getApproveTime().hashCode());
        result = prime * result + ((getApprovePerson() == null) ? 0 : getApprovePerson().hashCode());
        result = prime * result + ((getCreatedMan() == null) ? 0 : getCreatedMan().hashCode());
        result = prime * result + ((getUpdatedMan() == null) ? 0 : getUpdatedMan().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLocalCreatedAt() == null) ? 0 : getLocalCreatedAt().hashCode());
        result = prime * result + ((getLocalUpdatedAt() == null) ? 0 : getLocalUpdatedAt().hashCode());
        result = prime * result + ((getMn100Id() == null) ? 0 : getMn100Id().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fxId=").append(fxId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", reference=").append(reference);
        sb.append(", toMan=").append(toMan);
        sb.append(", toBank=").append(toBank);
        sb.append(", toBankBranch=").append(toBankBranch);
        sb.append(", toCardNumber=").append(toCardNumber);
        sb.append(", requestAmount=").append(requestAmount);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", rate=").append(rate);
        sb.append(", bankFee=").append(bankFee);
        sb.append(", balance=").append(balance);
        sb.append(", preBalance=").append(preBalance);
        sb.append(", balanceXy=").append(balanceXy);
        sb.append(", preBalanceXy=").append(preBalanceXy);
        sb.append(", sh110Balance=").append(sh110Balance);
        sb.append(", completedAt=").append(completedAt);
        sb.append(", currency=").append(currency);
        sb.append(", status=").append(status);
        sb.append(", action=").append(action);
        sb.append(", trCode=").append(trCode);
        sb.append(", note=").append(note);
        sb.append(", approveType=").append(approveType);
        sb.append(", approveTime=").append(approveTime);
        sb.append(", approvePerson=").append(approvePerson);
        sb.append(", createdMan=").append(createdMan);
        sb.append(", updatedMan=").append(updatedMan);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", localCreatedAt=").append(localCreatedAt);
        sb.append(", localUpdatedAt=").append(localUpdatedAt);
        sb.append(", mn100Id=").append(mn100Id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}