package org.example.common.entity;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 已经批准的账户报表
 * @TableName system_approved_account_report
 */
@TableName(value ="system_approved_account_report")
@Data
public class SystemApprovedAccountReport implements Serializable {

    /**
     * 编号
     */
    private String number;

    /**
     * 银行名称
     */
    private String bank;

    /**
     *
     */
    @TableField("`partition`")
    private String partition;

    /**
     * 1: FO, 2: ET
     */
    private Integer type;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 请求名称
     */
    private String requestedName;

    /**
     * 匹配的名称
     */
    private String matchedNames;


    @TableField(exist = false)
    private JSONArray matchedNamesJson;

    @TableField(exist = false)
    private String merchantName;

    /**
     * 上次请求时间
     */
    private DateTime lastRequestedAt;

    /**
     * 请求次数
     */
    private Object requests;

    /**
     * 创建时间
     */
    private DateTime createdAt;

    /**
     * 更新时间
     */
    private DateTime updatedAt;

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
        SystemApprovedAccountReport other = (SystemApprovedAccountReport) that;
        return (this.getPartition() == null ? other.getPartition() == null : this.getPartition().equals(other.getPartition()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getBank() == null ? other.getBank() == null : this.getBank().equals(other.getBank()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getRequestedName() == null ? other.getRequestedName() == null : this.getRequestedName().equals(other.getRequestedName()))
            && (this.getMatchedNames() == null ? other.getMatchedNames() == null : this.getMatchedNames().equals(other.getMatchedNames()))
            && (this.getLastRequestedAt() == null ? other.getLastRequestedAt() == null : this.getLastRequestedAt().equals(other.getLastRequestedAt()))
            && (this.getRequests() == null ? other.getRequests() == null : this.getRequests().equals(other.getRequests()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPartition() == null) ? 0 : getPartition().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getBank() == null) ? 0 : getBank().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getRequestedName() == null) ? 0 : getRequestedName().hashCode());
        result = prime * result + ((getMatchedNames() == null) ? 0 : getMatchedNames().hashCode());
        result = prime * result + ((getLastRequestedAt() == null) ? 0 : getLastRequestedAt().hashCode());
        result = prime * result + ((getRequests() == null) ? 0 : getRequests().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", partition=").append(partition);
        sb.append(", number=").append(number);
        sb.append(", bank=").append(bank);
        sb.append(", type=").append(type);
        sb.append(", merchnatId=").append(merchantId);
        sb.append(", requestedName=").append(requestedName);
        sb.append(", matchedNames=").append(matchedNames);
        sb.append(", lastRequestedAt=").append(lastRequestedAt);
        sb.append(", requests=").append(requests);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}