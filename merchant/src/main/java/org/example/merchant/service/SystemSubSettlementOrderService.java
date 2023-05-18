package org.example.merchant.service;

import org.example.merchant.dto.SubSettlementOrderSearchDTO;
import org.example.merchant.entity.SystemSubSettlementOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.merchant.vo.SubSettlementOrderVO;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_sub_settlement_order(Settlement_子下发订单)】的数据库操作Service
* @createDate 2023-05-17 15:00:37
*/
public interface SystemSubSettlementOrderService extends IService<SystemSubSettlementOrder> {

    // 分页查询
    List<SubSettlementOrderVO> search(SubSettlementOrderSearchDTO dto);
}
