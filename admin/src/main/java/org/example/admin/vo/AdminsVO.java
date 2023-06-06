package org.example.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "返回前端的管理员数据")
public class AdminsVO {
    private Long id;

    private String username;

    private String token;


}
