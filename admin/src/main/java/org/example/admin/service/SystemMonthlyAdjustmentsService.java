package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.MonthlyAdjustmentsDto;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemMonthlyAdjustments;

public interface SystemMonthlyAdjustmentsService extends IService<SystemMonthlyAdjustments> {

    //代理-每月调整-条件查询
    CommResp getAdjustmentsAll(MonthlyAdjustmentsDto monthlyAdjustmentsDto);
}
