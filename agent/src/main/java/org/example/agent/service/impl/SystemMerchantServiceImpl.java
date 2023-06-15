package org.example.agent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.mapper.SystemMerchantMapper;
import org.example.agent.service.SystemMerchantService;
import org.example.common.entity.SystemMerchant;
import org.springframework.stereotype.Service;

/**
 * 商户资料(SystemMerchant)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 14:49:47
 */
@Service("systemMerchantService")
public class SystemMerchantServiceImpl extends ServiceImpl<SystemMerchantMapper, SystemMerchant> implements SystemMerchantService {

}

