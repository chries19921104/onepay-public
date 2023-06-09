package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SystemNoticeRecord {
    /**
     * 通知主键ID
     */
    @TableId
    private Long noticeId;

    /**
     * 模型类
     */
    private String modelClass;

    /**
     * 外键
     */
    private Long foreignKey;

    /**
     * 通知结果 1: 完成, 2: 请求失败, 3: 回应状态码错误, 4: 回应解密失败, 9999: 未知
     */
    private String result;

    /**
     * 通知内容
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
}
