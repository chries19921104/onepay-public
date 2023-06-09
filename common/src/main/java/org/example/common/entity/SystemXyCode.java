package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * XY验证码
 * </p>
 *
 * @author rj
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemXyCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 短信验证码主键ID
     */
    @TableId(value = "code_id", type = IdType.AUTO)
    private Integer codeId;

    /**
     * 1:FI,2:FO,3:FX,4:TR,5:auto play
     */
    private Integer type;

    /**
     * 币种/地区
     */
    private String currency;

    /**
     * 银行卡ID
     */
    private Integer bankCardId;

    /**
     * 虚拟对账单主键ID
     */
    private Integer virtualId;

    /**
     * 单号
     */
    private String commandId;

    /**
     * 内容
     */
    private String data;

    /**
     * 验证码输入字串
     */
    private String captcha;

    /**
     * 是否点选过
     */
    @TableField("is_press_button")
    private Boolean pressButton;

    /**
     * 创建人员
     */
    private String creator;

    /**
     * 更新人员
     */
    private String updater;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
