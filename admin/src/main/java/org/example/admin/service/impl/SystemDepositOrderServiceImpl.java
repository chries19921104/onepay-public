package org.example.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.SystemDepositOrderService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SystemDepositOrderServiceImpl extends ServiceImpl<SystemDepositOrderMapper,SystemDepositOrder> implements SystemDepositOrderService {

    @Autowired
    private SystemDepositOrderMapper systemDepositOrderMapper;

    @Override
    public CommResp selectTxnModeByRegion(String currency) {
        SelectVo selectVo = getSelectVo(currency);
        return CommResp.data(selectVo);
    }
    //**今日成功金额****/****笔数**
    private SelectVo getSelectVo(String currency) {

        SelectVo selectVo = systemDepositOrderMapper.selectSuccess
                (currency, DateUtil.today(), DateUtil.formatDate(DateUtil.tomorrow()),"1","1");
        return selectVo;
    }


}