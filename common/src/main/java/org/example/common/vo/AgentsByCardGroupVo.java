package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class AgentsByCardGroupVo {
    //返回的数据类型：{PG100_ID: 1, PG100_name: "DEV-THB", currency: "THB"}

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
}