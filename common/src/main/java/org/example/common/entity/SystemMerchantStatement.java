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
 * 商户对账单表
 * @TableName system_merchant_statement
 */
@TableName(value ="system_merchant_statement")
@Data
public class SystemMerchantStatement implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 货币/地区
     */
    private String currency;

    /**
     * 对账单编号
     */
    private String orderSn;

    /**
     * 关联ID
     */
    private Integer linkId;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 对账单订单类型 FI-充值 QR-二维码充值 True-True Wallet充值 Bank-银行充值 FO-代付 FX-下发 TR-内转 Balance-余额
     */
    private String type;

    /**
     * 请求金额
     */
    private BigDecimal requestAmount;

    /**
     * 实际金额
     */
    private BigDecimal actualAmount;

    /**
     * 0 = 支出 1 = 获得
     */
    private Integer pm;

    /**
     * 交易费
     */
    private BigDecimal transactionFee;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 银行手续费
     */
    private BigDecimal bankFee;

    /**
     * 银行费用承担方
     */
    private String bankFeeBear;

    /**
     * 备注/描述
     */
    private String mark;

    /**
     * 只有调整单时有此字段值
     */
    private String reason;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tradeTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateAt;

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
        SystemMerchantStatement other = (SystemMerchantStatement) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCurrency() == null ? other.getCurrency() == null : this.getCurrency().equals(other.getCurrency()))
            && (this.getOrderSn() == null ? other.getOrderSn() == null : this.getOrderSn().equals(other.getOrderSn()))
            && (this.getLinkId() == null ? other.getLinkId() == null : this.getLinkId().equals(other.getLinkId()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getRequestAmount() == null ? other.getRequestAmount() == null : this.getRequestAmount().equals(other.getRequestAmount()))
            && (this.getActualAmount() == null ? other.getActualAmount() == null : this.getActualAmount().equals(other.getActualAmount()))
            && (this.getPm() == null ? other.getPm() == null : this.getPm().equals(other.getPm()))
            && (this.getTransactionFee() == null ? other.getTransactionFee() == null : this.getTransactionFee().equals(other.getTransactionFee()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getBankFee() == null ? other.getBankFee() == null : this.getBankFee().equals(other.getBankFee()))
            && (this.getBankFeeBear() == null ? other.getBankFeeBear() == null : this.getBankFeeBear().equals(other.getBankFeeBear()))
            && (this.getMark() == null ? other.getMark() == null : this.getMark().equals(other.getMark()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getTradeTime() == null ? other.getTradeTime() == null : this.getTradeTime().equals(other.getTradeTime()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        result = prime * result + ((getOrderSn() == null) ? 0 : getOrderSn().hashCode());
        result = prime * result + ((getLinkId() == null) ? 0 : getLinkId().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRequestAmount() == null) ? 0 : getRequestAmount().hashCode());
        result = prime * result + ((getActualAmount() == null) ? 0 : getActualAmount().hashCode());
        result = prime * result + ((getPm() == null) ? 0 : getPm().hashCode());
        result = prime * result + ((getTransactionFee() == null) ? 0 : getTransactionFee().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getBankFee() == null) ? 0 : getBankFee().hashCode());
        result = prime * result + ((getBankFeeBear() == null) ? 0 : getBankFeeBear().hashCode());
        result = prime * result + ((getMark() == null) ? 0 : getMark().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getTradeTime() == null) ? 0 : getTradeTime().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", currency=").append(currency);
        sb.append(", orderSn=").append(orderSn);
        sb.append(", linkId=").append(linkId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", type=").append(type);
        sb.append(", requestAmount=").append(requestAmount);
        sb.append(", actualAmount=").append(actualAmount);
        sb.append(", pm=").append(pm);
        sb.append(", transactionFee=").append(transactionFee);
        sb.append(", balance=").append(balance);
        sb.append(", bankFee=").append(bankFee);
        sb.append(", bankFeeBear=").append(bankFeeBear);
        sb.append(", mark=").append(mark);
        sb.append(", reason=").append(reason);
        sb.append(", tradeTime=").append(tradeTime);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}