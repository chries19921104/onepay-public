package org.example.common.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* <p>
* system_bank_card_log 实体类
* </p>
*
* @author Administrator
* @since 2023-06-14 18:53:08
*/
@Getter
@Setter
@TableName("system_bank_card_log")
public class SystemBankCardLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 银行卡操作日志主键ID
    */
    @TableId
    private Long bankCardLogId;

    /**
    * 银行卡ID
    */
    private Long bankCardId;

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
    private Timestamp createdAt;

    /**
    * 创建人员
    */
    private String creator;


}
