package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 爬虫服务器配置
 * </p>
 *
 * @author author
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("aas100")
@ApiModel(value="Aas100对象", description="爬虫服务器配置")
public class Aas100 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = UpdateGroup.class,message = "更新时ID不能为空")
    private Integer id;

    private String name;

    private String ipAddress;

    private Integer bc100Id;

    @ApiModelProperty(value = "主机 ID")
    private Integer hostId;

    @ApiModelProperty(value = "连线状态 (0 Not Connected, 1 Connecting, 2 Connected)")
    private Integer conn;

    @ApiModelProperty(value = "最后XY请求时间")
    private LocalDateTime lastConnectedAt;

    @ApiModelProperty(value = "连线失败原因")
    private String connFailedReason;

    @ApiModelProperty(value = "创建人员")
    private String createdMan;

    @ApiModelProperty(value = "更新人员")
    private String updatedMan;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
