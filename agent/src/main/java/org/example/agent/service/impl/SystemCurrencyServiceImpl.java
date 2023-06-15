package org.example.agent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.mapper.SystemCurrencyMapper;
import org.example.agent.service.SystemCurrencyService;
import org.example.common.entity.SystemCurrency;
import org.springframework.stereotype.Service;


/**
 * 币别(SystemCurrency)表服务实现类
 *
 * @author makejava
 * @since 2023-06-13 17:05:53
 */
@Service("systemCurrencyService")
public class SystemCurrencyServiceImpl extends ServiceImpl<SystemCurrencyMapper, SystemCurrency> implements SystemCurrencyService {

}

