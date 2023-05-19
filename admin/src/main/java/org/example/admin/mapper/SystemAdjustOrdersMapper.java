package org.example.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.common.dto.SystemAdjustOrdersSearchDTO;
import org.example.common.entity.SystemAdjustOrders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.vo.SystemAdjustOrdersVO;

import java.util.List;

/**
* @author 26708
* @description 针对表【system_adjust_orders(调整订单)】的数据库操作Mapper
* @createDate 2023-05-18 15:00:59
* @Entity org.example.common.entity.SystemAdjustOrders
*/
public interface SystemAdjustOrdersMapper extends BaseMapper<SystemAdjustOrders> {

    // 分页查询
    Page<SystemAdjustOrdersVO> search(Page<SystemAdjustOrdersVO> page, @Param(value = "dto") SystemAdjustOrdersSearchDTO dto);
}




