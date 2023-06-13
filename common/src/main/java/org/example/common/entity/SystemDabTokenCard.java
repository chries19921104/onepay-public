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
* system_dab_token_card 实体类
* </p>
*
* @author Administrator
* @since 2023-06-09 17:36:00
*/
@Getter
@Setter
@TableName("system_dab_token_card")
public class SystemDabTokenCard implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /**
    * 银行卡ID
    */
    private Long bankCardId;

    /**
    * 对应数值
    */
    private String content;

    /**
    * 创建时间
    */
    private LocalDateTime createdAt;

    /**
    * 创建人员
    */
    private String createdMan;

    /**
    * 序列号
    */
    private String serial;

    /**
    * 更新时间
    */
    private LocalDateTime updatedAt;

    /**
    * 更新人员
    */
    private String updatedMan;


}
