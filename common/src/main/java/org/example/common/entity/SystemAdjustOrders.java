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
 * 调整订单
 * @TableName system_adjust_orders
 */
@TableName(value ="system_adjust_orders")
@Data
public class SystemAdjustOrders implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 父 ID
     */
    private Integer parentId;

    /**
     * 虚拟银行对账单主键ID
     */
    private Integer virtualBankReconciliationId;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 交易类型：1 Debit, 2 Credit
     */
    private Integer tradeType;

    /**
     * 调整类型：1 Deposit, 2 Withdraw, 3 Topup, 4 Balance
     */
    private Integer adjustType;

    /**
     * 原因 1 Human error, 2 System error, 3 Freeze, 4 Unfreeze, 5 Others
     */
    private Integer reason;

    /**
     * 总额
     */
    private BigDecimal amount;

    /**
     * 交易费
     */
    private BigDecimal fee;

    /**
     * 状态 1 Pending, 2 Approved, 3 Rejected
     */
    private Integer status;

    /**
     * 币别
     */
    private String currency;

    /**
     * 描述
     */
    private String description;

    /**
     * 外部描述
     */
    private String externalDescription;

    /**
     * 我们的单号
     */
    private String commandId;

    /**
     * 商户余额
     */
    private BigDecimal merchantBalance;

    /**
     * 交易单单号
     */
    private String txnCommandId;

    /**
     * 交易单类型
     */
    private String modelClass;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdAt;

    /**
     * 更新时间
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
        SystemAdjustOrders other = (SystemAdjustOrders) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getVirtualBankReconciliationId() == null ? other.getVirtualBankReconciliationId() == null : this.getVirtualBankReconciliationId().equals(other.getVirtualBankReconciliationId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getTradeType() == null ? other.getTradeType() == null : this.getTradeType().equals(other.getTradeType()))
            && (this.getAdjustType() == null ? other.getAdjustType() == null : this.getAdjustType().equals(other.getAdjustType()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getExternalDescription() == null ? other.getExternalDescription() == null : this.getExternalDescription().equals(other.getExternalDescription()))
            && (this.getCommandId() == null ? other.getCommandId() == null : this.getCommandId().equals(other.getCommandId()))
            && (this.getMerchantBalance() == null ? other.getMerchantBalance() == null : this.getMerchantBalance().equals(other.getMerchantBalance()))
            && (this.getTxnCommandId() == null ? other.getTxnCommandId() == null : this.getTxnCommandId().equals(other.getTxnCommandId()))
            && (this.getModelClass() == null ? other.getModelClass() == null : this.getModelClass().equals(other.getModelClass()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getLocalCreatedAt() == null ? other.getLocalCreatedAt() == null : this.getLocalCreatedAt().equals(other.getLocalCreatedAt()))
            && (this.getLocalUpdatedAt() == null ? other.getLocalUpdatedAt() == null : this.getLocalUpdatedAt().equals(other.getLocalUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getVirtualBankReconciliationId() == null) ? 0 : getVirtualBankReconciliationId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getTradeType() == null) ? 0 : getTradeType().hashCode());
        result = prime * result + ((getAdjustType() == null) ? 0 : getAdjustType().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getExternalDescription() == null) ? 0 : getExternalDescription().hashCode());
        result = prime * result + ((getCommandId() == null) ? 0 : getCommandId().hashCode());
        result = prime * result + ((getMerchantBalance() == null) ? 0 : getMerchantBalance().hashCode());
        result = prime * result + ((getTxnCommandId() == null) ? 0 : getTxnCommandId().hashCode());
        result = prime * result + ((getModelClass() == null) ? 0 : getModelClass().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLocalCreatedAt() == null) ? 0 : getLocalCreatedAt().hashCode());
        result = prime * result + ((getLocalUpdatedAt() == null) ? 0 : getLocalUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", virtualBankReconciliationId=").append(virtualBankReconciliationId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", tradeType=").append(tradeType);
        sb.append(", adjustType=").append(adjustType);
        sb.append(", reason=").append(reason);
        sb.append(", amount=").append(amount);
        sb.append(", fee=").append(fee);
        sb.append(", status=").append(status);
        sb.append(", currency=").append(currency);
        sb.append(", description=").append(description);
        sb.append(", externalDescription=").append(externalDescription);
        sb.append(", commandId=").append(commandId);
        sb.append(", merchantBalance=").append(merchantBalance);
        sb.append(", txnCommandId=").append(txnCommandId);
        sb.append(", modelClass=").append(modelClass);
        sb.append(", creator=").append(creator);
        sb.append(", updater=").append(updater);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", localCreatedAt=").append(localCreatedAt);
        sb.append(", localUpdatedAt=").append(localUpdatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}