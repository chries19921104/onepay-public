package org.example.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 回扣方案(SystemRebateScheme)表实体类
 *
 * @author makejava
 * @since 2023-06-12 10:19:01
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_rebate_scheme")
public class SystemRebateScheme  {
    //主键ID@TableId
    private Integer rsId;

    //方案名称
    private String name;
    //1- 累进式, 2- 固定式
    private Integer type;
    //创建人员
    private String creator;
    //更新人员
    private String updater;
    //创建时间
    private Date createdAt;
    //更新时间
    private Date updatedAt;



}

