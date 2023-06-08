package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.CommResp;
import org.example.admin.dto.DashboardDto;
import org.example.admin.dto.DepositOrderDto;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.exception.MsgException;
import org.example.admin.vo.DepositOrderVo;
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
     * @param depositOrderDto
     * @return
     */
    Page<DepositOrderVo> searchByCondition(DepositOrderDto depositOrderDto);

    /**
     * 保存数据为xls格式到服务器上
     * @param depositOrderDto
     * @return
     * @throws MsgException
     */
    String download(DepositOrderDto depositOrderDto) throws MsgException;

}