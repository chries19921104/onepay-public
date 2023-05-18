package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.entity.SystemDepositOrder;
import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.SystemDepositOrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> selectTxnModeByRegion() {
//        List<Map<String, Object>> maps = this.systemDepositOrderMapper.selectTxnModeByRegion();
        return null;
    }
}