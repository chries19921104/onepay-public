package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* system_merchant_white_list 实体类
* </p>
*
* @author Administrator
* @since 2023-05-19 13:37:58
*/
@Getter
@Setter
@TableName("system_merchant_white_list")
@ApiModel(description = "商户白名单实体类")
public class SystemMerchantWhiteList implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商户白名单ID
    */
    @TableId
    private Long whiteId;

    /**
    * 商户ID
    */
    private Long merchantId;

    /**
    * 白名单IP
    */
    private String ip;

    /**
    * IP 格式, 1: IPv4, 2: IPv4 CIDR
    */
    private Integer ipFormat;

    /**
    * 类型: 1 Withdraw(充值) 2 Deposit(提现) BS200
    */
    private Integer type;

    /**
    * 类型: 1 Active(启用) 0 Inactive(禁用) BS200"
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
