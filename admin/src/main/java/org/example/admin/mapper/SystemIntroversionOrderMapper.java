package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.admin.dto.InternalTransferDto;
import org.example.admin.vo.InternalTransferVo;
import org.example.common.entity.SystemIntroversionOrder;

import java.util.List;

/**
* @author Administrator
* @description 针对表【system_introversion_order(Transfer_内转订单)】的数据库操作Mapper
* @createDate 2023-06-12 15:30:46
* @Entity org.example.admin.entity.SystemIntroversionOrder
*/
public interface SystemIntroversionOrderMapper extends BaseMapper<SystemIntroversionOrder> {

    List<InternalTransferVo> getInternalTransfer(InternalTransferDto internalTransferDto);
}




