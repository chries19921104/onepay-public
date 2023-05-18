package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.dto.SystemAdjustOrdersSearchDTO;
import org.example.common.entity.SystemAdjustOrders;
import org.example.common.vo.SystemAdjustOrdersVO;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_adjust_orders(调整订单)】的数据库操作Service
* @createDate 2023-05-18 15:00:59
*/
public interface SystemAdjustOrdersService extends IService<SystemAdjustOrders> {

    // 分页查询
    List<SystemAdjustOrdersVO> search(SystemAdjustOrdersSearchDTO dto);
}
