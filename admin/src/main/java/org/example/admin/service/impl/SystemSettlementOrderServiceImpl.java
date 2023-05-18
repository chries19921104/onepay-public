package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.dto.SettlementOrderSearchDTO;
import org.example.common.entity.SystemSettlementOrder;
import org.example.admin.mapper.SystemSettlementOrderMapper;
import org.example.admin.service.SystemSettlementOrderService;
import org.example.common.vo.SettlementOrderVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_settlement_order(Settlement_下发订单)】的数据库操作Service实现
* @createDate 2023-05-16 14:12:52
*/
@Service
public class SystemSettlementOrderServiceImpl extends ServiceImpl<SystemSettlementOrderMapper, SystemSettlementOrder>
    implements SystemSettlementOrderService {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public List<SettlementOrderVO> search(SettlementOrderSearchDTO dto) {
        return this.baseMapper.search(dto);
    }
}




