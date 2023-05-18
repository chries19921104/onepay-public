package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
* system_bank_card_group 实体类
* </p>
*
* @author Administrator
* @since 2023-05-16 16:37:08
*/
@Getter
@Setter
@TableName("system_bank_card_group")
public class SystemBankCardGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 银行卡群组ID
    */
    @TableId
    private Long groupId;

    /**
    * 群名称
    */
    private String groupName;

    /**
    * 币别
    */
    private String currency;

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
