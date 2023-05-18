package org.example.merchant.service;

import org.example.merchant.dto.SettlementOrderSearchDTO;
import org.example.merchant.entity.SystemSettlementOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.merchant.vo.SettlementOrderVO;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_settlement_order(Settlement_下发订单)】的数据库操作Service
* @createDate 2023-05-16 14:12:52
*/
public interface SystemSettlementOrderService extends IService<SystemSettlementOrder> {

    // 分页查询
    List<SettlementOrderVO> search(SettlementOrderSearchDTO dto);

}
