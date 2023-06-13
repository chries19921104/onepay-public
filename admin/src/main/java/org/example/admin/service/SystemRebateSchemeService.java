package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.RebateSchemeDto;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemRebateScheme;

public interface SystemRebateSchemeService extends IService<SystemRebateScheme> {

    //代理-返点设置-条件查询
    CommResp getScheme(RebateSchemeDto rebateSchemeDto);
}
