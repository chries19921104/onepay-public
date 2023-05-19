package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "返回群组数据")
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