package org.example.common.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
* <p>
* system_deposit_order 实体类
* </p>
*
* @author zhangmi
* @since 2023-05-17 19:16:15
*/
@Data
@TableName("system_deposit_order")
@ApiModel(value = "充值订单")
public class SystemDepositOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer TXN_MODE_VNPAY_BANK = 106;
    public static final Integer TXN_MODE_QRPAY = 2;
    public static final Integer TXN_MODE_TRUE_WALLET = 3;
    public static final Integer TXN_MODE_VNPAY_ZALO_QR = 101;
    public static final Integer TXN_MODE_VNPAY_MOMO_QR = 102;
    public static final Integer TXN_MODE_VNPAY_VIETTEL_QR = 103;
    public static final Integer TXN_MODE_VNPAY_VIETTEL_FIX = 104;
    public static final Integer TXN_MODE_VNPAY_BANK_CARD = 107;
    public static final Integer TXN_MODE_VNPAY_RCGCARD_PC_VIETTEL = 108;
    public static final Integer TXN_MODE_VNPAY_RCGCARD_PC_MOBIFONE = 109;
    public static final Integer TXN_MODE_VNPAY_RCGCARD_PC_VINAPHONE = 110;
    public static final Integer TXN_MODE_VNPAY_RCGCARD_ZING = 111;

    public static final Integer TXN_MODE_EIGHTPAY_5_ONLY_WITHDRAWL = 301;
    public static final Integer TXN_MODE_QIJIPAY_WITHDRAWL = 401;
    public static final Integer TXN_MODE_VNPAY_BANK_QR = 105;
    public static final Integer TXN_MODE_DAPAY_ZFBYHK = 205;
    public static final Integer TXN_MODE_DAPAY_NETGATE = 208;
    public static final Integer TXN_MODE_DAPAY_FO = 215;
    public static final Integer TXN_MODE_DAPAY_BANK2BANK = 216;
    public static final Integer TXN_MODE_H1PAY_UNION_PAY = 501;



    /**
    * 充值ID
    */
    @TableId
    private Long fiId;

    /**
    * 商户ID
    */
    private Long merchantId;

    /**
    * 参考编号
    */
    private String reference;

    /**
    * from用户名称
    */
    private String fromMan;

    /**
    * from银行
    */
    private String fromBank;

    /**
    * from银行账户
    */
    private String fromCardNumber;

    /**
    * 银行卡ID (to)
    */
    private Long bankCardId;

    /**
    * 第三方串接ID
    */
    private Long tpi100Id;

    /**
    * 交易模式 1.Bank网银 2.QR Code二维码 3.Crypto加密
    */
    private Integer txnMode;

    /**
    * 用户原始提交金额
    */
    private BigDecimal orderAmount;

    /**
    * 用户发起金额
    */
    private BigDecimal requestAmount;

    /**
    * 实际作用金额
    */
    private BigDecimal paidAmount;

    /**
    * 损失金额
    */
    private BigDecimal lossAmount;

    /**
    * 系统手续费
    */
    private BigDecimal rate;

    /**
    * 银行手续费
    */
    private BigDecimal bankFee;

    /**
    * 余额 (to)
    */
    private BigDecimal balance;

    /**
    * 交易前余额 (to)
    */
    private BigDecimal preBalance;

    /**
    * 讯银余额 (to)
    */
    private BigDecimal balanceXy;

    /**
    * 讯银交易前余额 (to)
    */
    private BigDecimal preBalanceXy;

    /**
    * 商户余额
    */
    private BigDecimal merchantBalance;

    /**
    * 订单完成时间
    */
    private LocalDateTime completedAt;

    /**
    * 币别
    */
    private String currency;

    /**
    * 讯银步骤
    */
    private String step;

    /**
    * 讯银回传状态
    */
    private Integer status;

    /**
    * 状态明细 2:无卡
    */
    private Integer statusDescription;

    /**
    * 不使用 QR Pay 金额调整的合约
    */
    private Integer noQrAdj;

    /**
    * 手动操作状态 成功 Uploaded 失败 Unknown Error After Otp BS200
    */
    private Integer action;

    /**
    * 用户备注
    */
    private String note;

    /**
    * 迅银回传当前状态（文字）
    */
    private String message;

    /**
    * 我们的单号
    */
    private String commandId;

    /**
    * 用户安全问题
    */
    private String securityQuestions;

    /**
    * 比对方式，1: 虚拟对账单, 2: 短信对比
    */
    private Integer pairingBy;

    /**
    * 虚拟对账单主键ID
    */
    private Long vbId;

    /**
    * 虚拟对账单 比对到的时间
    */
    private LocalDateTime vbPairingTime;

    /**
    * 短信监控主键ID
    */
    private Long smId;

    /**
    * 短信监控 比对到的时间
    */
    private LocalDateTime smPairingTime;

    /**
    * 0:未进入tuna,1:已进入tuna
    */
    private Integer isTunaIn;

    /**
    * 0:未登录,1:已输入帐号密码登录
    */
    private Integer isLogIn;

    /**
    * 0:未输入OTP,1:已输入OTP
    */
    private Integer isOtpIn;

    /**
    * OTP发起时间
    */
    @TableField("receivedOTPtime")
    private LocalDateTime receivedOTPtime;

    /**
    * OTP倒数时间
    */
    private Integer otptimer;

    /**
    * OTP reference no
    */
    private String refCode;

    /**
    * 银行流水单 reference no
    */
    private String bankreferenceno;

    /**
    * 使用OTP状态: smart otp, sms otp, token card, mQR, mCode, mConnected, token
    */
    private String otp;

    /**
    * 对帐用唯一码
    */
    private String postscript;

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
    * user 发起时间
    */
    private LocalDateTime userCreatedAt;

    /**
    * 异步通知地址
    */
    private String notifyUrl;

    /**
    * xy QR Code url
    */
    private String tqrcode;

    /**
    * 服务器ID
    */
    private Long serverId;

    private Long altVpnId;

    /**
    * 异常单 0.非异常,1.异常,2.已解决
    */
    private Integer isWeird;

    /**
    * 描述
    */
    private String description;

    /**
    * 外部描述
    */
    private String externalDescription;

    /**
    * 催单 0.无动作,1.进行催单
    */
    private Integer isUrgent;

    /**
    * 审核码农确实未收到款 0.无动作,1.未收到款
    */
    private Integer isApproveNotGet;

    /**
    * 自身返点
    */
    private BigDecimal keepPoint;

    /**
    * 上级返点
    */
    private BigDecimal superiorKeepPoint;

    /**
    * 过期 0.无动作,1.过期
    */
    private Integer isExpired;

    /**
    * 异常单-处理人员
    */
    private String abnormalMan;

    /**
    * 异常单-处理时间
    */
    private Timestamp abnormaldAt;

    /**
    * 审核异常单-处理人员
    */
    private String abnormalverifyMan;

    /**
    * 审核异常单-处理时间
    */
    private Timestamp abnormalverifydAt;

    /**
    * 冻结保证金
    */
    private BigDecimal frozenGuaranteeBalance;

    /**
    * 内部人员接单，预设null，0:非内部接单，1:内部接单
    */
    private Integer insiderTakes;

    /**
    * 重新整理点击次数
    */
    private Integer runmonNumber;
    enum DepositOrder_enum{
        BANK_TNX_MODE("1","银行订单类型"),
        QR_TNX_MODE("2","QR充值订单类型"),
        TW_TNX_MODE("3","True Wallet充值订单类型"),
        LOCAL_TNX_MODE("4","本地银行订单类型");







        private String code;
        private String desc;

        DepositOrder_enum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        /**
         * 自己定义一个静态方法,通过code返回枚举常量对象
         * @param code
         * @return
         */
        public static DepositOrder_enum getValue(String code){

            for (DepositOrder_enum  color: values()) {
                if(color.getCode().equals(code)){
                    return  color;
                }
            }
            return null;

        }


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }

}

