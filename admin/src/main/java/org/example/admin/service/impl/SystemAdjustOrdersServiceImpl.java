package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.service.SystemAdjustOrdersService;
import org.example.common.dto.SystemAdjustOrdersSearchDTO;
import org.example.common.entity.SystemAdjustOrders;
import org.example.admin.mapper.SystemAdjustOrdersMapper;
import org.example.common.vo.SystemAdjustOrdersVO;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * @param page
     * @param dto
     * @return
     */
    @Override
    public Page<SystemAdjustOrdersVO> search(Page<SystemAdjustOrdersVO> page, SystemAdjustOrdersSearchDTO dto) {
        return this.baseMapper.search(page,dto);
    }


    /**
     * 新增or修改
     * @param orders
     * @return
     */
    @Override
    public Boolean addOrUpdate(SystemAdjustOrders orders) {
        // 后续需要添加更新者，修改人
        if (orders.getId()!=null){ // 修改，添加更新时间
            orders.setUpdatedAt(new Date());
        }else{
            orders.setCreatedAt(new Date());
        }
        return null;
    }


}




