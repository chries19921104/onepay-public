package org.example.merchant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.merchant.dto.SubSettlementOrderSearchDTO;
import org.example.merchant.entity.SystemSubSettlementOrder;
import org.example.merchant.service.SystemSubSettlementOrderService;
import org.example.merchant.mapper.SystemSubSettlementOrderMapper;
import org.example.merchant.vo.SubSettlementOrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_sub_settlement_order(Settlement_子下发订单)】的数据库操作Service实现
* @createDate 2023-05-17 15:00:37
*/
@Service
public class SystemSubSettlementOrderServiceImpl extends ServiceImpl<SystemSubSettlementOrderMapper, SystemSubSettlementOrder>
    implements SystemSubSettlementOrderService{

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public List<SubSettlementOrderVO> search(SubSettlementOrderSearchDTO dto) {
        return this.baseMapper.search(dto);
    }
}




