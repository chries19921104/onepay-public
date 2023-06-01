package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.common.dto.DashboardDto;
import org.example.common.dto.SystemDepositOrderDto;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.vo.SystemDepositOrderVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
}