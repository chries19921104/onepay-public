package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Log Last Page
 * @TableName system_log_last_page
 */
@TableName(value ="system_log_last_page")
@Data
public class SystemLogLastPage implements Serializable {
    /**
     * log ID
     */
    @TableId(type = IdType.AUTO)
    private Object lastPageId;

    /**
     * 还不知道有什么状态，确认后一样用 BS200
     */
    private Integer type;

    /**
     * 爬虫服务器唯一ID
     */
    private String commandId;

    /**
     * 内容
     */
    private String data;

    /**
     * 备注
     */
    private String message;

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
        SystemLogLastPage other = (SystemLogLastPage) that;
        return (this.getLastPageId() == null ? other.getLastPageId() == null : this.getLastPageId().equals(other.getLastPageId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCommandId() == null ? other.getCommandId() == null : this.getCommandId().equals(other.getCommandId()))
            && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getLocalCreatedAt() == null ? other.getLocalCreatedAt() == null : this.getLocalCreatedAt().equals(other.getLocalCreatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLastPageId() == null) ? 0 : getLastPageId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCommandId() == null) ? 0 : getCommandId().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLocalCreatedAt() == null) ? 0 : getLocalCreatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lastPageId=").append(lastPageId);
        sb.append(", type=").append(type);
        sb.append(", commandId=").append(commandId);
        sb.append(", data=").append(data);
        sb.append(", message=").append(message);
        sb.append(", creator=").append(creator);
        sb.append(", updater=").append(updater);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", localCreatedAt=").append(localCreatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}