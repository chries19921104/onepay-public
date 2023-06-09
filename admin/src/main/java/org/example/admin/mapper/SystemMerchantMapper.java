package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.common.entity.SystemMerchant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
* <p>
* system_merchant Mapper 接口
* </p>
*
* @author Administrator
* @since 2023-05-16 14:56:27
*/
@Mapper
public interface SystemMerchantMapper extends BaseMapper<SystemMerchant> {


    @MapKey("merchant_id")
    Map<Integer,String> selectListMap(HashSet<Integer> set);
}