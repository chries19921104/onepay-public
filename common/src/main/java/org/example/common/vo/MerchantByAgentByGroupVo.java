package org.example.common.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MerchantByAgentByGroupVo {

    /**
     * 商户ID
     */
    @TableId
    private Long merchantId;

    /**
     * 商户名称
     */
    private String name;

    /**
     * 商户代码
     */
    private String code;

    /**
     * 币别 currency
     */
    private String currency;

    /**
     * 卡群
     */
    private Long cardGroupId;
}
