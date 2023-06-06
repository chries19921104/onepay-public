package org.example.admin.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@ApiModel(description = "返回银行数据")
public class BrankVo {
    /**
     * 银行ID
     */
    @JsonProperty("BK100_ID")
    private Long bankId;

    /**
     * 银行名称
     */
    private String name;

    /**
     * 银行代号
     */
    private String code;

    /**
     * 币别
     */
    private String currency;

    /**
     * 最大收款
     */
    @JsonProperty("FI_max")
    private BigDecimal fiMax;

    /**
     * 最小收款
     */
    @JsonProperty("FI_min")
    private BigDecimal fiMin;

    /**
     * 最大代付
     */
    @JsonProperty("FO_max")
    private BigDecimal foMax;

    /**
     * 最小代付
     */
    @JsonProperty("FO_min")
    private BigDecimal foMin;

    /**
     * 最大下发
     */
    @JsonProperty("FX_max")
    private BigDecimal fxMax;

    /**
     * 最小下发
     */
    @JsonProperty("FX_min")
    private BigDecimal fxMin;

    /**
     * 最大内转
     */
    @JsonProperty("TR_max")
    private BigDecimal trMax;

    /**
     * 最小内转
     */
    @JsonProperty("FR_min")
    private BigDecimal trMin;

    /**
     * 保留额度
     */
    @JsonProperty("reserved_amount")
    private BigDecimal reservedAmount;

    /**
     * 日次數最大 (入+出)
     */
    @JsonProperty("day_treshold")
    private Long dayTreshold;

    /**
     * 状态 1.Available(可用) 0.Suspended(暂停) BS200
     */
    private Integer status;

    /**
     * 备注
     */
    private String note;

    /**
     * 启用手动验证码
     */
    @JsonProperty("is_captcha")
    private Integer isCaptcha;

    private String meta;

    /**
     * 创建人员
     */
    @JsonProperty("created_man")
    private String creator;

    /**
     * 更新人员
     */
    @JsonProperty("updated_man")
    private String updater;

    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp updatedAt;
}
