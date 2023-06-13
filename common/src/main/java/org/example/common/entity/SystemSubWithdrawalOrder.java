package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * Withdrawal_子代付订单
 * </p>
 *
 * @author rj
 * @since 2023-06-06
 */
@Data
public class SystemSubWithdrawalOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 子代付ID
     */
    @TableId(value = "sub_fo_id", type = IdType.AUTO)
    private Integer subFoId;

    /**
     * 代付ID
     */
    private Integer foId;

    /**
     * 过往子代付ID
     */
    private Integer foPreId;

    /**
     * 银行卡ID (from)
     */
    private Integer bankCardId;

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
     * 第三方代付银行手续费 壹支付承担
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
    private BigDecimal walletBalance;

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
    @TableField("is_press_button")
    private Boolean pressButton;

    /**
     * "讯银回传状态
            1.Pending–等待机器人跑
            2.Processing–机器人正在跑
            3.Completed–成功交易
            4.Manual process–失败后的手动操作
            人工可操作成成功或失败（如果交易试成功的）
            5.Failed–失败. 不可更改状态
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
     * 虚拟银行对账单ID
     */
    private Integer virtualBankId;

    /**
     * OTP发起时间
     */
    @TableField("receivedOTPtime")
    private LocalDateTime receivedotptime;

    /**
     * OTP倒数时间
     */
    private Integer otptimer;

    private String refCode;

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

    private String postscript;

    /**
     * 备注
     */
    private String note;

    /**
     * U盾token input
     */
    private String tokenInput;

    private String xyToMan;

    /**
     * XY备註
     */
    private String description;

    /**
     * 创建人员
     */
    private String createdMan;

    /**
     * 更新人员
     */
    private String updatedMan;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 当地创建时间
     */
    private LocalDateTime localCreatedAt;

    /**
     * 当地创建时间
     */
    private LocalDateTime localUpdatedAt;

    /**
     * 服务器ID
     */
    private Integer serverId;

    private Integer altVpnId;

    /**
     * 回到市场次数
     */
    private Integer isLateTimes;

    /**
     * 异常单 0.非异常,1.异常,2.已解决
     */
    private Integer isWeird;

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
     * 冻结保证金
     */
    private BigDecimal frozenGuaranteeBalance;

    /**
     * 重新整理点击次数
     */
    private Integer runmonNumber;


}
