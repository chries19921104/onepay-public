package org.example.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.SystemDepositOrderService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.vo.SelectVo;
import org.example.common.vo.SystemDepositOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * system_deposit_order Service 接口实现
 * </p>
 *
 * @author zhangmi
 * @since 2023-05-17 19:16:15
 */
@Service
@Transactional
@Slf4j
public class SystemDepositOrderServiceImpl extends ServiceImpl<SystemDepositOrderMapper, SystemDepositOrder> implements SystemDepositOrderService {

    @Autowired
    private SystemDepositOrderMapper systemDepositOrderMapper;

    @Override
    public CommResp selectTxnModeByRegion(String currency) {





        //昨日成功的数据
        List<SystemDepositOrderVo> list = systemDepositOrderMapper
                .selectMoneyAndCount(currency, DateUtil.formatDate(DateUtil.yesterday()),DateUtil.today(),"1");
        //昨日失败数据
        List<SystemDepositOrderVo> list1 = systemDepositOrderMapper
                .selectMoneyAndCount(currency, DateUtil.formatDate(DateUtil.yesterday()),DateUtil.today(),"4,5");
        //今日成功数据
        //今日失败数据
        //整理数据返回前端
        return CommResp.data(list1);
    }

}