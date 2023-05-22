package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.vo.SelectVo;
import org.example.common.vo.SystemDepositOrderVo;

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

    List<SystemDepositOrderVo> selectMoneyAndCount(@Param("currency") String currency,
                                                   @Param("beginTime") String beginTime,
                                                   @Param("endTime")String endTime,
                                                   @Param("status")String status,
                                                   @Param("cardId")String cardId,
                                                   @Param("merchantId")String merchantId
    );
    SystemDepositOrderVo selectSettlement(@Param("currency") String currency,
                                                   @Param("beginTime") String beginTime,
                                                   @Param("endTime")String endTime,
                                                   @Param("status")String status,
                                                   @Param("cardId")String cardId

    );
    SystemDepositOrderVo selectWithdrawal(@Param("currency") String currency,
                                                   @Param("beginTime") String beginTime,
                                                   @Param("endTime")String endTime,
                                                   @Param("status")String status,
                                                   @Param("cardId")String cardId

    );
    SystemDepositOrderVo selectCrypto(@Param("currency") String currency,
                                                   @Param("beginTime") String beginTime,
                                                   @Param("endTime")String endTime,
                                                   @Param("status")String status,
                                                   @Param("cardId")String cardId
    );


}