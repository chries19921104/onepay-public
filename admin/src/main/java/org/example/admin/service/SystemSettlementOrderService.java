package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.common.dto.SettlementOrderSearchDTO;
import org.example.common.entity.SystemSettlementOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.vo.SettlementOrderVO;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_settlement_order(Settlement_下发订单)】的数据库操作Service
* @createDate 2023-05-16 14:12:52
*/
public interface SystemSettlementOrderService extends IService<SystemSettlementOrder> {
    // 分页查询
    Page<SettlementOrderVO> search(Page<SettlementOrderVO> objectPage,SettlementOrderSearchDTO dto);
}
