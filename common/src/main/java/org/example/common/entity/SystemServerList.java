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
* system_server_list 实体类
* </p>
*
* @author Administrator
* @since 2023-06-05 18:59:22
*/
@Getter
@Setter
@TableName("system_server_list")
public class SystemServerList implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 服务器主键ID
    */
    @TableId
    private Long serverId;

    /**
    * 服务器名称
    */
    private String name;

    /**
    * 服务器IP
    */
    private String ip;

    /**
    * 内容
    */
    private String content;

    /**
    * 创建时间
    */
    private LocalDateTime createdAt;

    /**
    * 更新时间
    */
    private LocalDateTime updatedAt;

    /**
    * 地区/币种
    */
    private String currency;

    /**
    * 状态
    */
    private Integer active;


}
