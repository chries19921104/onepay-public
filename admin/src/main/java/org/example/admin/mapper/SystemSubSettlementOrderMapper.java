package org.example.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.common.dto.SubSettlementOrderSearchDTO;
import org.example.common.entity.SystemSubSettlementOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.vo.SubSettlementOrderVO;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_sub_settlement_order(Settlement_子下发订单)】的数据库操作Mapper
* @createDate 2023-05-17 15:00:37
* @Entity org.example.common.entity.SystemSubSettlementOrder
*/
public interface SystemSubSettlementOrderMapper extends BaseMapper<SystemSubSettlementOrder> {

    // 分页查询
    List<SubSettlementOrderVO> search(@Param(value = "dto") SubSettlementOrderSearchDTO dto);
}




