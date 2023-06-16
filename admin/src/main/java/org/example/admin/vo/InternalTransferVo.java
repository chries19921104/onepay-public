package org.example.admin.vo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.example.admin.mapper.SystemBankCardMapper;
import org.example.admin.req.Transaction;
import org.example.common.entity.SystemBankCard;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @author Txd
 * @since 2023-06-12 17:14:53
 */
@Data
@ApiModel(description = "返回前端的内部转账列表的查询数据")
public class InternalTransferVo {

    @Resource
    private SystemBankCardMapper systemBankCardMapper;

    @JsonProperty("completed_at")
    private Timestamp completedAt;
    @JsonProperty("retry_btn")
    private boolean retryBtn;
    @JsonProperty("update_btn")
    private boolean updateBtn;
    @JsonProperty("from_account_code")
    private String fromAccountCode;//
    @JsonProperty("to_account_code")
    private String toAccountCode;//
    @JsonProperty("from_mobile_name")
    private String fromMobileName;//
    @JsonProperty("is_errurl")
    private boolean isErrurl;//
    @JsonProperty("is_runMon")
    private boolean isRunMon;
    @JsonProperty("TR100_ID")
    private Integer trId;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("BC100_ID_from")
    private Integer fromBankCardId;
    @JsonProperty("BC100_ID_to")
    private Integer toBankCardId;
    @JsonProperty("request_amount")
    private Integer requestAmount;
    @JsonProperty("paid_amount")
    private Integer paidAmount;
    @JsonProperty("bank_fee")
    private Integer bankFee;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("step")
    private String step;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    @JsonProperty("created_man")
    private String creator;
    @JsonProperty("updated_man")
    private String updater;
    @JsonProperty("message")
    private String message;
    @JsonProperty("retry_times")
    private Integer retryTimes;
    @JsonProperty("VB100_ID_from")
    private Integer fromVsId;
    @JsonProperty("VB100_ID_to")
    private Integer toVsId;
    @JsonProperty("command_id")
    private String commandId;
    @JsonProperty("note")
    private String note;
    @JsonProperty("local_updated_at")
    private Timestamp localUpdatedAt;
    @JsonProperty("is_entry")
    private Integer isEntry;
    @JsonProperty("vnd_otp")
    private String vndOtp;
    @JsonProperty("postscript")
    private String postscript;
    @JsonProperty("vnd_payment_method")
    private String vndPaymentMethod;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("tr_auto")
    private boolean trAuto;
    @JsonProperty("alt_id")
    private String altId;

    private Integer action;

    @JsonIgnore
    private Integer total;

    public boolean isRetryBtn() {
        if (this.isEntry == 1){
            return false;
        }
        return this.status == 4 && this.retryTimes < 2
                && this.action == null;
    }

    public boolean isUpdateBtn() {
        return (this.isEntry == 0 && this.status == Transaction.STATUS_MANUAL_PROCESS)
                || (this.isEntry == 1 && this.status == Transaction.STATUS_PENDING);
    }

    public String getFromAccountCode() {
        SystemBankCard systemBankCard = systemBankCardMapper.selectOne(new LambdaQueryWrapper<SystemBankCard>().eq(SystemBankCard::getCardId, this.fromBankCardId));
        return systemBankCard.getAccountCode();
    }
}
