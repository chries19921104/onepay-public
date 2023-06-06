package org.example.admin.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "返回群组信息")
public class SystemBankCardGroupVO {
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
