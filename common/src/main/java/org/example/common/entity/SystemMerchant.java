package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
* <p>
* system_merchant 实体类
* </p>
*
* @author Administrator
* @since 2023-05-16 18:21:43
*/
@Getter
@Setter
@TableName("system_merchant")
@ApiModel(description = "商户实体类")
public class SystemMerchant implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商户ID
    */
    @TableId(type = IdType.AUTO)
    private Long merchantId;

    /**
    * 卡群
    */
    private Long cardGroupId;

    /**
    * 代理
    */
    private Long agentId;

    /**
    * 商户名称
    */
    private String name;

    /**
    * 商户代码
    */
    private String code;

    /**
    * 安全码
    */
    private String secCode;

    /**
    * QR商户名称
    */
    private String name4qr;

    /**
    * 币别 currency
    */
    private String currency;

    /**
    * web网址
    */
    private String website;

    /**
    * 充值费率%
    */
    private BigDecimal trRate;

    /**
    * 充值 QRPay 费率%
    */
    private BigDecimal trQrRate;

    /**
    * 充值 TrueWallet 费率%
    */
    private BigDecimal trTrueRate;

    /**
    * 充值 LocalBankTransfer 费率%
    */
    private BigDecimal trLocalbankRate;

    /**
    * 代付费率%
    */
    private BigDecimal wdRate;

    /**
    * 代付费用
    */
    private BigDecimal wdRateAmount;

    /**
    * 结算手续费
    */
    private BigDecimal settFee;

    /**
    * 最大银行收款
    */
    private BigDecimal fiBankMax;

    /**
    * 最小银行收款
    */
    private BigDecimal fiBankMin;

    /**
    * 最大qrpay收款
    */
    private BigDecimal fiQrpayMax;

    /**
    * 最小qrpay收款
    */
    private BigDecimal fiQrpayMin;

    /**
    * 最大TrueWallet收款
    */
    private BigDecimal fiTrueMax;

    /**
    * 最小TrueWallet收款
    */
    private BigDecimal fiTrueMin;

    /**
    * 最大LocalBankTransfer收款
    */
    private BigDecimal fiLocalbankMax;

    /**
    * 最小LocalBankTransfer收款
    */
    private BigDecimal fiLocalbankMin;

    /**
    * 最大代付
    */
    private BigDecimal foMax;

    /**
    * 最小代付
    */
    private BigDecimal foMin;

    /**
    * 最大下发
    */
    private BigDecimal fxMax;

    /**
    * 最小下发
    */
    private BigDecimal fxMin;

    /**
    * 下发银行帐户数量
    */
    private Integer settCardMax;

    /**
    * 状态 0 Pending(等待) 1 Active(启用) 2 Inactive(禁用) 3 Closed(关闭) BS200
    */
    private Integer status;

    /**
    * 不使用 QR Pay 金额调整的合约
    */
    private Integer noQrAdj;

    /**
    * QR随机加扣款 0: 扣款, 1:加款
    */
    private Integer noQrAdjRandom;

    /**
    * LocalBankTransfer支付商户金额
    */
    private Integer localbankAdj;

    /**
    * LocalBankTransfer随机加扣款
    */
    private Integer localbankAdjRandom;

    /**
    * 是否使用 QR 附言
    */
    private Integer useQrPairingCode;

    /**
    * 是否使用 VNPAY
    */
    private Integer vnpayEnabled;

    /**
    * 是否使用 Dapay
    */
    private Integer dapayEnabled;

    /**
    * 是否使用 H1Pay
    */
    private Integer h1payEnabled;

    /**
    * 是否使用 YouktPay
    */
    private Integer youktpayEnabled;

    /**
    * 是否使用第三方 FO
    */
    private String foTpiDriver;

    /**
    * QijiPay启用时间(起)
    */
    private String qijipayStartTime;

    /**
    * QijiPay启用时间(讫)
    */
    private String qijipayEndTime;

    /**
    * 启用FO比对账户名称
    */
    private Integer checkAccname;

    /**
    * 启用FO由商户支付银行手续费
    */
    private Integer payFoBankFee;

    /**
    * fi 入款延迟天数，0 为关闭设定
    */
    private Integer settlementTerm;

    /**
    * 创建人员
    */
    private String createdMan;

    /**
    * 更新人员
    */
    private String updatedMan;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
    * GCash费率
    */
    private BigDecimal trGcashRate;

    /**
    * 最大GCash收款
    */
    private BigDecimal fiGcashMax;

    /**
    * 最小GCash收款
    */
    private BigDecimal fiGcashMin;


}
