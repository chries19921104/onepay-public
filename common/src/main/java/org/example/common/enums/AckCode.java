package org.example.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * @author 14001
 */

@NoArgsConstructor
@AllArgsConstructor
public enum AckCode {

        /**
         * 通用提示
         **/
    COMMON_FRE_OPERATION(203, "服务器频繁,请重试"),
    RATE_LIMATE(201,"访问过于频繁，请稍候再试"),


    /*-------------------编辑资料-------------------*/
    NICK_NAME_TOO_LONG(300, "昵称最多8个字符"),
    ILLEGAL_MONOLOGUE(301, "内心独白违规"),
    ILLEGAL_NICKNAME(302, "昵称涉及敏感词"),
    ILLEGAL_SPECIAL(303, "昵称不能包含特殊符号"),
    ADMIN_VALUE_NOT_CHANGE(304,"该笔数据不允许修改的"),
    Modi_DICTTYPE_NOT_ALLOWED(507,"字典类型的值不允许修改"),
    UPDATE_PWD_OK(305,"密码已修改,请重新登录"),

    /*-----Basic-----*/
    SUCCESS(200, "ok"),
    LOGOUT_SUCCESS(200,"登出成功"),
    EXISTS_VALUE(506,"该值已被使用"),
    DATA_EXISTS(507,"{}该值已被使用"),

    FAIL(500, "操作失败"),
    SYSTEM_PARAM_FAIL(400, "参数错误"),
    SYSTEM_TOKEN_FAIL(401, "会话无效，请重新登录"),
    SYSTEM_SIGNATURE_FAIL(402, "签名错误"),
    SYSTEM_DATA_FAIL(500, "系统数据异常"),
    SYSTEM_SERVER_BUSY(501, "服务器繁忙"),
    SYSTEM_SERVER_MAINTAINING(503, "系统维护中"),
    NOT_FOUND_DATA(504, "查询不到数据"),
    NO_PERMISSION_TO_ACCESS(505, "没有权限访问该请求"),
    PERMISSION_NOT_NULL(506,"RequiredPermission权限标识不允许为空"),
    PERMISSION_NOT_ACCESS(507,"没有权限访问该接口"),
    NOT_ALLOW_CHANGED(508,"不能操作改字段"),
    INSUFFICIENT_BALANCE(509,"余额不足"),



    /*-------------------登录-------------------*/
    LOGIN_URL_ERROR(600, "登录回调路径错误"),
    JPUSH_LOGIN_ERROR(601, "极光登录失败，请重新登录"),
    ALIYUN_LOGIN_ERROR(602, "阿里云登录失败，请重新登录"),
    GENDER_TYPE_ERROR(603, "性别类型错误"),
    DEVICE_NOT_EXIST(604, "设备不存在"),
    DEVICE_REGISTER_TOO_MUCH(605, "一个设备只能注册10个账号"),
//    LOGIN_BANNED(606, "账号ID：*已被锁定，请联系客服。\n客服QQ：80056867"),
    LOGIN_BANNED(606, "您的账号因违规操作现已被%s，如有疑问，请联系客服！客服QQ：80056867"),
    WITHDRAW_BANNED(607, "提现受限，请联系客服"),
    LOG_OFF(608, "您账号已经注销"),
    DEVICE_BANNED(609, "帐号异常：*已被锁定，请联系客服。\n客服QQ：80056867"),
    APPLE_EXPIRED(610, "token过期"),
    APPLE_ILLEGAL(611, "token非法"),
    APPLE_FAIL(612, "token验证失败"),
    INVITATION_CODE_ERROR(613, "邀请码不正确"),
    TOKEN_FAIL(614,"令牌过期"),
    USERNAME_NOT_BLANK(615,"用户名不能为空"),
    PASSWORD_NOT_BLANK(616,"密码不能为空"),
    CODE_NOT_BLANK(617,"验证码不能为空"),
    CODEUUID_NOT_BLANK(618,"验证码标识不能为空"),
    TOKEN_NOT_BLANK(619,"令牌不能为空"),




    /*-------------------短信验证-------------------*/
    SMS_SEND_ERROR(700, "验证码发送失败"),
    SMS_SYSTEM_ERROR(701, "验证码发送系统异常"),
    SMS_CODE_WRONG(702, "验证码错误"),
    SMS_CODE_OR_MOBILE_WRONG(702, "验证码或者旧手机号错误"),
    SMS_DEVICE_WRONG(703, "设备型号不匹配"),
    SMS_CODE_OVERTIME(704, "验证码超时"),
    SMS_CODE_STATUS_USED(705, "验证码已使用"),
    SMS_SEND_OVERTIMES(706, "今日发送次数已用完"),
    SMS_SEND_TYPE_WRONG(707, "发送验证码类型错误"),
    SMS_SEND_COLD_TIME(708, "两次验证码发送间隔不能小于2分钟"),
    /**
     * 身份证验证性别
     */
    IDCARD_GENDER_ERROR(708, "身份证性别不一致"),
    SMS_NOT_SAVE(709, "验证码发送中"),


    /*--------------------文件-----------------*/
    FILE_NOT_EXIST(800, "文件不存在"),
    FILE_UPLOAD_OSS_TOKEN_ERROR(801, "获取上传令牌失败"),
    UPLOAD_TYPE_ERROR(802, "上传文件类型有误"),
    UPLOAD_TYPE_ERROR_IMAGE(802, "上传文件类型有误，支持的图片格式包括\"JPG\", \"JPEG\", \"PNG\" "),
    UPLOAD_FILE_NAME_ERROR(803, "上传文件名称有误"),
    UPLOAD_FILE_CORRUPTED(804, "文件损坏，请重新上传"),
    UPLOAD_PHOTO_LIMIT(805, "照片最多上传5张"),
    UPLOAD_VOICE_LIMIT(806, "语音时长太短"),
    FILE_NOT_EMPTY(807, "文件不能为空"),
    UPLOAD_IMAGE_SIZE_LIMIT(808, "图片大小不能超过1MB"),
    FILE_UPLOAD_FAILED(809,"文件上传失败"),

    /*-----User (StartCode: 1000)--------------*/
    USER_PASSWORD_ERROR(1000, "密码错误"),
    USER_NOT_FOUND(1001, "用户不存在"),
    USER_HAS_ACCOUNT(1016, "该账户已存在"),
    USER_PAY_ACCOUNT_IS_BIND(1017, "支付账户已绑定"),
    USER_LOGIN_ACCOUNT_IS_BIND(1018, "该登录账户已绑定"),
    USER_ACCOUNT_AUTH_EXCEPTION(1019, "认证查询异常，接口调用失败"),
    USER_PAY_IS_FAIL(1020, "支付失败"),
    USER_PAY_IS_EXCEPTION(1021, "支付异常"),
    USER_WITHDRAW_LIMIT(1022, "提现受限"),
    USER_ACCOUNT_IS_NOT_BIND(1023, "支付账户未绑定"),
    USER_ACCOUNT_IS_NOT_FOUND(1024, "未找到支付账户"),
    USER_PARAM_IS_NOT_NULL(1025, "参数不能为空"),
    USER_ACCOUNT_UPDATE_COUNT_LIMIT(1026, "账户修改次数当月达到上限"),
    USER_FIRST_CHARGE_COUNT_LIMIT(1027, "首充已达上限"),
    USER_WITHDRAW_STATUS_ERROR(1028, "只有未审核的才能进行操作哟"),
    USER_VIOLATION(1034, "用户涉嫌违规"),
    USER_IS_NOT_MAN(1035, "抱歉您不是男用户"),
    USER_LOG_OFF(1036, "用户已注销"),
    USER_FACE_VERIFY(1037, "您已真人认证"),
    USER_BANNED(1038, "该用户已被封禁"),
	FAMILY_SHARE_LINK_IS_EXPIRE(1114, "家族分享链接已过期"),
	LOGIN_ACCOUNT_PASSWORD_ERROR(1115, "账号或密码错误"),
	LOGIN_PASSWORD_ERROR(1116, "密码错误"),
	LOGIN_PASSWORD_INVALID(1117, "密码不正确"),
	SET_PASSWORD_INVALID(1118, "两次输入密码不一致"),
	OLD_PASSWORD_NOTNULL(1119, "原密码不能为空"),
	OLD_PASSWORD_INVALID(1120, "原密码不正确"),
	OLD_PASSWORD_ERROR(1121, "原密码错误"),
	PHONE_NUMBER_SET_ACCOUNT_INVALID(1122, "该手机号码已经被其他用户绑定"),
	PLEASE_BIND_PHONE_NUMBER(1123, "请先绑定手机号码"),
	PHONE_NUMBER_NOTNULL(1124, "手机号码不能为空"),
	PASSWORD_VERIFY_FAILED(1125, "密码必须是6~16位的数字、字母组合"),
	BUY_LIMIT(1126, "购买受限"),
    TOKEN_SAVE_ERROR(1127,"保留令牌失败"),
    USERNAME_PASSWORD_ERROR(1128,"用户名或密码错误,你还剩{}次机会，账户会被锁定{}分钟"),
    NOT_MODIFY_OTHER_USER(1129,"你不允许修改别人的数据"),
    PASSWORD_INCRRENT_RETRY_COUNT(620,"密码已经输入错误，超过{}次，该账户会被锁定{}分钟"),

    /** --商家2xxx开头-- **/
    ENTERPRISE_NOT_VALIDATED(2000,   "商家信息还未通过审核，不允许登录"),
    ENTERPRISE_NO_ASSISGNED(2001,"用户还未分配商家信息，不允许进行该项操作"),

    /** 商品分类3xx开头 */
    CATEGORY_NOT_EMPTY(3000,"商品分类存在相关的数据，不能删除"),

    /** 收货地址 4xx开头 */
    ADDRESSBOOK_NOT_DELETE(4000,"默认收货地址，不允许删除"),

    /** 下单 5xx开头 */
    ORDER_EMPTY(5000,"订单下单失败"),
    ORDER_STATUA_ERROR(5001,"订单状态异常，不能支付"),
    ORDER_PAY_FAIL(5002,"订单支付失败"),
    ORDER_PAY_CheckSignature(5003,"支付成功，数据签名认证失败")
    ;



    public Integer code;

    public String msg;

    /**
     * 获取所有回复码
     *
     * @return
     */
    public static LinkedHashMap<Integer, String> getArrayMessage() {
        LinkedHashMap<Integer, String> responseMessages = new LinkedHashMap<>();
        for (AckCode statusEnum : AckCode.values()) {
            responseMessages.put(statusEnum.code, statusEnum.msg);
        }
        return responseMessages;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
