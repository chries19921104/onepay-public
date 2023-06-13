package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.admin.mapper.SystemCurrencyMapper;
import org.example.admin.service.SystemCurrencyService;
import org.example.common.entity.SystemCurrency;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 币别 服务实现类
 * </p>
 *
 * @author rj
 * @since 2023-06-08
 */
@Service
public class SystemCurrencyServiceImpl extends ServiceImpl<SystemCurrencyMapper, SystemCurrency> implements SystemCurrencyService {

}
