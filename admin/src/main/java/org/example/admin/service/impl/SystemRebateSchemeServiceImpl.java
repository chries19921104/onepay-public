package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.RebateSchemeDto;
import org.example.admin.mapper.SystemAgentsMapper;
import org.example.admin.mapper.SystemRebateSchemeMapper;
import org.example.admin.service.SystemAgentsService;
import org.example.admin.service.SystemRebateSchemeService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemAgents;
import org.example.common.entity.SystemRebateScheme;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class SystemRebateSchemeServiceImpl extends ServiceImpl<SystemRebateSchemeMapper, SystemRebateScheme> implements SystemRebateSchemeService {


    @Override
    public CommResp getAll(RebateSchemeDto rebateSchemeDto) {


        return null;
    }
}
