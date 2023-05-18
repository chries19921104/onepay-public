package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.service.SystemAdjustOrdersService;
import org.example.common.dto.SystemAdjustOrdersSearchDTO;
import org.example.common.entity.SystemAdjustOrders;
import org.example.admin.mapper.SystemAdjustOrdersMapper;
import org.example.common.vo.SystemAdjustOrdersVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_adjust_orders(调整订单)】的数据库操作Service实现
* @createDate 2023-05-18 15:00:59
*/
@Service
public class SystemAdjustOrdersServiceImpl extends ServiceImpl<SystemAdjustOrdersMapper, SystemAdjustOrders>
    implements SystemAdjustOrdersService {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public List<SystemAdjustOrdersVO> search(SystemAdjustOrdersSearchDTO dto) {
        return this.baseMapper.search(dto);
    }
}




