package org.example.merchant.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.merchant.dto.SettlementOrderSearchDTO;
import org.example.merchant.entity.SystemSettlementOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.merchant.vo.SettlementOrderVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_settlement_order(Settlement_下发订单)】的数据库操作Mapper
* @createDate 2023-05-16 14:12:52
* @Entity org.example.merchant.entity.SystemSettlementOrder
*/
@Mapper
public interface SystemSettlementOrderMapper extends BaseMapper<SystemSettlementOrder> {

    // 分页查询
    List<SettlementOrderVO> search(@Param(value = "dto") SettlementOrderSearchDTO dto);
}




