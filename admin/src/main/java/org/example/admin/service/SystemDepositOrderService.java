package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.entity.SystemDepositOrder;

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
public interface SystemDepositOrderService extends IService<SystemDepositOrder> {
    List<Map<String,Object>> selectTxnModeByRegion();

}