package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemBankCardMapper;
import org.example.admin.service.SystemBankCardService;
import org.example.common.entity.SystemBankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* <p>
* system_bank_card Service 接口实现
* </p>
*
* @author zhangmi
* @since 2023-05-19 16:22:04
*/
@Service
@Transactional
@Slf4j
public class SystemBankCardServiceImpl extends ServiceImpl<SystemBankCardMapper, SystemBankCard> implements SystemBankCardService {

    @Autowired
    private SystemBankCardMapper systemBankCardMapper;

}