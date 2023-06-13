package org.example.agent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.mapper.SystemRebateSchemeMapper;
import org.example.agent.service.SystemRebateSchemeService;
import org.example.common.entity.SystemRebateScheme;
import org.springframework.stereotype.Service;

/**
 * 回扣方案(SystemRebateScheme)表服务实现类
 *
 * @author makejava
 * @since 2023-06-12 10:20:13
 */
@Service("systemRebateSchemeService")
public class SystemRebateSchemeServiceImpl extends ServiceImpl<SystemRebateSchemeMapper, SystemRebateScheme> implements SystemRebateSchemeService {

}

