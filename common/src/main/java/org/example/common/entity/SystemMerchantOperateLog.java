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
* system_merchant_operate_log 实体类
* </p>
*
* @author Administrator
* @since 2023-05-22 14:50:02
*/
@Getter
@Setter
@TableName("system_merchant_operate_log")
public class SystemMerchantOperateLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 商户logID
    */
    @TableId
    private Long merchantLogId;

    /**
    * 商户ID
    */
    private Long merchantId;

    /**
    * 1 insert(新增) 2 update(更新) 3 delete(刪除)
    */
    private Integer type;

    /**
    * 操作内容
    */
    private String content;

    /**
    * 创建时间
    */
    private LocalDateTime createdAt;

    /**
    * 创建人员
    */
    private String operator;


}
