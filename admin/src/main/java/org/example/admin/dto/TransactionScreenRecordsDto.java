package org.example.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;

/**
 * @author Txd
 * @since 2023-06-09 10:05:28
 */

@Data
@ApiModel(description = "接收前端交易画面记录查询参数")
@Validated
public class TransactionScreenRecordsDto {

    @Max(value = 255,message = "type不能大于255")
    private Integer type;

    @JsonProperty("art_id")
    private String art_id;

    private Integer rp;

    private Integer page;

}
