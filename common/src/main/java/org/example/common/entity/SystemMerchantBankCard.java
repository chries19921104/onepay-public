package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* system_merchant_bank_card 实体类
* </p>
*
* @author Administrator
* @since 2023-05-18 18:57:31
*/
@Getter
@Setter
@TableName("system_merchant_bank_card")
@ApiModel(description = "商户银行账户实体类")
public class SystemMerchantBankCard implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商户银行账户ID
    */
    @TableId
    private Long mbId;

    /**
    * 商户ID
    */
    private Long merchantId;

    /**
    * 银行
    */
    private String bankCode;

    /**
    * 银行名称
    */
    private String bankName;

    /**
    * 支行
    */
    private String branch;

    /**
    * 户名
    */
    private String accName;

    /**
    * 卡号
    */
    private String cardNumber;

    /**
    * 状态 1 Available(可用) 0 Suspended(暂停) BS200"
    */
    private Integer status;

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


}
