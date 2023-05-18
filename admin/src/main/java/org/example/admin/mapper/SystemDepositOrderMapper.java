package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.vo.SelectVo;

import java.util.List;
import java.util.Map;

/**
* <p>
* system_deposit_order Mapper 接口
* </p>
*
* @author zhangmi
* @since 2023-05-17 19:16:15
*/
@Mapper
public interface SystemDepositOrderMapper extends BaseMapper<SystemDepositOrder> {
    SelectVo selectSuccess(
            @Param("currency") String currency,
            @Param("beginTime") String beginTime,
            @Param("endTime") String endTime,
            @Param("txnMode") String txnMode,
            @Param("status") String status
    );



}