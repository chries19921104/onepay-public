package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.dto.SubSettlementOrderSearchDTO;
import org.example.common.entity.SystemSubSettlementOrder;
import org.example.admin.mapper.SystemSubSettlementOrderMapper;
import org.example.admin.service.SystemSubSettlementOrderService;
import org.example.common.vo.SubSettlementOrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_sub_settlement_order(Settlement_子下发订单)】的数据库操作Service实现
* @createDate 2023-05-17 15:00:37
*/
@Service
public class SystemSubSettlementOrderServiceImpl extends ServiceImpl<SystemSubSettlementOrderMapper, SystemSubSettlementOrder>
    implements SystemSubSettlementOrderService {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public Page<SubSettlementOrderVO> search(Page<SubSettlementOrderVO> subSettlementOrderVOPage,SubSettlementOrderSearchDTO dto) {
        return this.baseMapper.search(subSettlementOrderVOPage,dto);
    }
}




