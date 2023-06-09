package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.entity.SystemMerchant;


/**
 * 商户资料(SystemMerchant)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-07 14:49:39
 */
@Mapper
public interface SystemMerchantMapper extends BaseMapper<SystemMerchant> {

}

