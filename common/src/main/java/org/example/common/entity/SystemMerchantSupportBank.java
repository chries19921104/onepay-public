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
* system_merchant_support_bank 实体类
* </p>
*
* @author Administrator
* @since 2023-05-17 17:01:04
*/
@Getter
@Setter
@TableName("system_merchant_support_bank")
@ApiModel(description = "商户开放银行实体类")
public class SystemMerchantSupportBank implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商户支持银行主键 ID
    */
    @TableId
    private Long msId;

    /**
    * 商户ID
    */
    private Integer merchantId;

    /**
    * 银行ID
    */
    private Integer bankId;

    /**
    * 交易类别, 1: FI bank, 2: FI QRPay, 3: FO
    */
    private Integer txnType;

    /**
    * 是否启用
    */
    private Integer isEnabled;

    /**
    * 商户接口不支持此银行时返回的错误信息
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
    private LocalDateTime createdAt;

    /**
    * 更新时间
    */
    private LocalDateTime updatedAt;


}
