package org.example.admin.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Txd
 * @since 2023-06-12 17:14:53
 */
@Data
@ApiModel(description = "返回前端的内部转账列表的查询数据")
public class InternalTransferVo {

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
    private Integer TR100_ID;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("BC100_ID_from")
    private Integer BC100_ID_from;
    @JsonProperty("BC100_ID_to")
    private Integer BC100_ID_to;
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
    private String createdMan;
    @JsonProperty("updated_man")
    private String updatedMan;
    @JsonProperty("message")
    private String message;
    @JsonProperty("retry_times")
    private Integer retryTimes;
    @JsonProperty("VB100_ID_from")
    private Integer VB100_ID_from;
    @JsonProperty("VB100_ID_to")
    private Integer VB100_ID_to;
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
}
