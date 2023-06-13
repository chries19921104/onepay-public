package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
* <p>
* system_bank_card 实体类
* </p>
*
* @author zhangmi
* @since 2023-05-19 15:50:28
*/
@Getter
@Setter
@TableName("system_bank_card")
public class SystemBankCard implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 银行卡主键ID
    */
    @TableId(type = IdType.AUTO)
    private Long cardId;

    /**
    * 卡群ID
    */
    private Long cardGroupId;

    /**
    * 银行ID
    */
    private Long bankId;

    /**
    * 卡类型 1.FI 2.FO 3.FX 4.TR BS200
    */
    private Integer type;

    /**
    * 账户名称
    */
    private String account;

    /**
    * 账户号码
    */
    private String bankNumber;

    /**
    * 币种
    */
    private String currency;

    /**
    * 支行
    */
    private String branch;

    /**
    * 代付 pin
    */
    private String withdrawPin;

    /**
    * ATM 账号
    */
    private String atmCardNumber;

    /**
    * ATM pin
    */
    private String atmPin;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String password;

    /**
    * 最初余额
    */
    private BigDecimal beginningBalance;

    /**
    * 讯银最初余额
    */
    private BigDecimal beginningBalanceXy;

    /**
    * 日收款金额上限(最大余额)
    */
    private BigDecimal dailyCollectionLimit;

    /**
    * 日出款金额上限
    */
    private BigDecimal dailyDisbursementLimit;

    /**
    * 月出款金额上限 
    */
    private BigDecimal monthDisbursementLimit;

    /**
    * 月交易次数最大 (入+出)
    */
    private Long monthTreshold;

    /**
    * 日交易次数最大 (入+出)
    */
    private Long dayTreshold;

    /**
    * 健康额度
    */
    private BigDecimal healthyBalance;

    /**
    * 最小额度
    */
    private BigDecimal minBalancePercentage;

    /**
    * 最小保留金额
    */
    private BigDecimal minReservedAmount;

    /**
    * 电话名称
    */
    private String mobileName;

    /**
    * 电话号码
    */
    private String phoneNumber;

    /**
    * sim位置 1.1 2.2 BS200
    */
    private Integer simOpt;

    /**
    * sim过期时间
    */
    private LocalDateTime simCreditExpired;

    /**
    * 备注
    */
    private String note;

    /**
    * 0.Standby-沒有Group或尚未开卡。1.Active - 启用（有group&开卡完成，财务手动更改状态）2.Blocked-银行登录被锁  3.Inactive-禁用。4.Temp. freeze-暂时冻结（temporary freeze ）5.Perm. freeze-永久冻结（Permanently freeze）6.Hold-暂停，不对外    7.Maintenance-维护
    */
    private Integer status;

    /**
    * 账户码
    */
    private String accountCode;

    /**
    * 对账单
    */
    private String statement;

    /**
    * 爬虫返回的信息
    */
    private String message;

    /**
    * 执行开卡状态 1.Running 讯银爬资料中 2.Success 已爬会第一笔资料开卡成功 3.Failed 爬不到资料, 开卡失败
    */
    private Integer openingStatus;

    /**
    * 收款模式（收款方式） 1.Bank Account 2.QR Account 3-True, 4- Qr and True ,5-IB and QR 6-本地银行
    */
    private Integer paymentMode;

    /**
    * QR pay code
    */
    private String qrPayCode;

    /**
    * PromptPay Code
    */
    private String promptpayCode;

    /**
    * 爬虫ID
    */
    private String commandId;

    /**
    * 创建时间
    */
    private Timestamp createdAt;

    /**
    * 更新时间
    */
    private Timestamp updatedAt;

    /**
    * 创建人员
    */
    private String creator;

    /**
    * 更新人员
    */
    private String updater;

    /**
    * 服务器ID
    */
    private Long serverId;

    /**
    * 上次爬虫服务器ID
    */
    private Long preServerId;

    /**
    * opencard讯银步骤
    */
    private String stepOpencard;

    /**
    * history讯银步骤
    */
    private String stepHistory;

    /**
    * VND OTP
    */
    private String vndOtp;

    /**
    * VND 出款方式
    */
    private String vndPaymentMethod;

    /**
    * VND APP密码
    */
    private String vndAppPassword;

    /**
    * 印尼mobile_id
    */
    private String mobileId;

    /**
    * 印尼BCA APP 需要参数m_pin
    */
    private String mPin;

    /**
    * 印尼pw_transfer
    */
    private String pwTransfer;

    /**
    * 印尼BCA PAN需要参数secure_answer
    */
    private String secureAnswer;

    /**
    * 电话 IP
    */
    private String phoneIp;

    /**
    * 是否为测试卡
    */
    private Integer forTesting;

    /**
    * hold时间 for自动启用
    */
    private LocalDateTime holdAt;

    /**
    * 自动启用后的状态
    */
    private Long statusPreset;

    /**
    * Email
    */
    private String email;

    /**
    * 每日次数
    */
    private Long dayNumber;


}
