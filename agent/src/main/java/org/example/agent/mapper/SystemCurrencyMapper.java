package org.example.agent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.entity.SystemCurrency;

import java.util.List;


/**
 * 币别(SystemCurrency)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-13 17:11:21
 */
@Mapper
public interface SystemCurrencyMapper extends BaseMapper<SystemCurrency> {

    List<String> selectCurrency();
}

