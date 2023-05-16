package org.example.common.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.example.common.entity.Admins;

@Data
@ApiModel(value = "接受前端管理员数据")
public class AdminsDTO extends Admins {
    private String token;

}
