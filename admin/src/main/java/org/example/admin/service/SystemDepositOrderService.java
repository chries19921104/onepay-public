package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.CommResp;
import org.example.admin.dto.DashboardDto;
import org.example.common.entity.SystemDepositOrder;
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


}