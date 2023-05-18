package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* <p>
* system_bank 实体类
* </p>
*
* @author Administrator
* @since 2023-05-18 13:35:44
*/
@Getter
@Setter
@TableName("system_bank")
public class SystemBank implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 银行ID
    */
    @TableId
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
    private BigDecimal fiMax;

    /**
    * 最小收款
    */
    private BigDecimal fiMin;

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
    * 最大内转
    */
    private BigDecimal trMax;

    /**
    * 最小内转
    */
    private BigDecimal trMin;

    /**
    * 保留额度
    */
    private BigDecimal reservedAmount;

    /**
    * 日次數最大 (入+出)
    */
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
    private Integer isCaptcha;

    private String meta;

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
    private Timestamp createdAt;

    /**
    * 更新时间
    */
    private Timestamp updatedAt;


}
