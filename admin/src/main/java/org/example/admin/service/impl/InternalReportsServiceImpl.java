package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.InternalReportsMapper;
import org.example.admin.mapper.SystemBankCardMapper;
import org.example.admin.service.InternalReportsService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemBankCard;
import org.example.common.vo.BankCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 16:15:56
 */
@Service
@Transactional
@Slf4j
public class InternalReportsServiceImpl implements InternalReportsService {

    @Autowired
    private InternalReportsMapper internalReportsMapper;
    @Override
    public CommResp getBankAccountList() {


        return null;
    }
}
