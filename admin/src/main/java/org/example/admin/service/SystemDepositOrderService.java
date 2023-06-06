package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.CommResp;
import org.example.admin.dto.DashboardDto;
import org.example.admin.dto.SystemDepositOrderDto;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.exception.MsgException;
import org.example.admin.vo.SystemDepositOrderVo;
import org.springframework.stereotype.Service;

/**
* <p>
* system_deposit_order Service 接口
* </p>
*
* @author zhangmi
* @since 2023-05-17 19:16:15
*/
@Service
public interface SystemDepositOrderService extends IService<SystemDepositOrder> {
    CommResp selectTxnModeByRegion(DashboardDto dashboardDto);

    CommResp infoText();

    /**
     * 通过条件搜索交易记录
     * @param systemDepositOrderDto
     * @return
     */
    Page<SystemDepositOrderVo> searchByCondition(SystemDepositOrderDto systemDepositOrderDto);

    /**
     * 保存数据为xls格式到服务器上
     * @param systemDepositOrderDto
     * @return
     * @throws MsgException
     */
    String download(SystemDepositOrderDto systemDepositOrderDto) throws MsgException;

}