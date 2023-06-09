package org.example.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.base.Result;
import org.example.agent.dto.SummaryDto;
import org.example.agent.mapper.AgentCommissionSettingsMapper;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.mapper.SystemMerchantMapper;
import org.example.agent.service.AgentCommissionSettingsService;
import org.example.agent.service.SystemMerchantService;
import org.example.agent.utils.BeanCopyUtils;
import org.example.agent.vo.CurrentRebateVo;
import org.example.agent.vo.PlanSummaryVo;
import org.example.agent.vo.SummaryVo;
import org.example.common.entity.SystemAgentCommissionSettings;
import org.example.common.entity.SystemMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商户资料(SystemMerchant)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 14:49:47
 */
@Service("systemMerchantService")
public class SystemMerchantServiceImpl extends ServiceImpl<SystemMerchantMapper, SystemMerchant> implements SystemMerchantService {


    @Autowired
    private SystemMerchantMapper merchantMapper;
    @Autowired
    private SystemAgentsMapper systemAgentsMapper;
    @Autowired
    private AgentCommissionSettingsMapper agentCommissionSettingsMapper;
    @Autowired
    private AgentCommissionSettingsService agentCommissionSettingsService;

    @Override
    public Result loadPlanSummaryTable(SummaryDto summaryDto) {

        PlanSummaryVo info = systemAgentsMapper.info(summaryDto);

        LambdaQueryWrapper<SystemMerchant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemMerchant::getAgentId, summaryDto.getAgentId());
        wrapper.eq(SystemMerchant::getCurrency, summaryDto.getCurrency());
        wrapper.in(!CollectionUtils.isEmpty(summaryDto.getSH100ID()), SystemMerchant::getMerchantId, summaryDto.getSH100ID());
        List<SystemMerchant> merchants = list(wrapper);

        List<CurrentRebateVo> currentRebates = agentCommissionSettingsMapper
                .currentRebate(summaryDto.getAgentId(), summaryDto.getCurrency());
        List<SummaryVo> summarys = BeanCopyUtils.copyBeanList(merchants, SummaryVo.class);
        List<SummaryVo> summaryVos = new ArrayList<>();
        summarys.forEach(summaryVo -> {
            currentRebates.stream()
                    .filter(currentRebateVo -> summaryVo.getMerchantId().equals(currentRebateVo.getSh100Id()))
                    .forEach(currentRebateVo -> {
                        summaryVo.setCurrentRebate(currentRebateVo);
                        summaryVos.add(summaryVo);
                    });
        });

        LambdaQueryWrapper<SystemAgentCommissionSettings> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemAgentCommissionSettings::getAgentId,summaryDto.getAgentId());
        List<SystemAgentCommissionSettings> agentCommissionSettings
                = agentCommissionSettingsService.list(queryWrapper);

        LocalDate currentDate = LocalDate.now();
        summaryVos.forEach(summary -> {
            List<SystemAgentCommissionSettings>  nearest= new ArrayList<>();
            agentCommissionSettings.forEach(date -> {
                if (summary.getMerchantId().equals(date.getSh100Id())) {
                    if (date.getYear() >= currentDate.getYear() && date.getMonth() > currentDate.getMonthValue()) {
                        nearest.add(date);
                    }
                }
            });
           List<SystemAgentCommissionSettings> agentCommissionListAsc = nearest.stream()
                    .sorted(Comparator.comparing(SystemAgentCommissionSettings::getYear)
                            .thenComparing(SystemAgentCommissionSettings::getMonth))
                    .collect(Collectors.toList());
           summary.setNearestRebate(agentCommissionListAsc.get(0));
        });


        info.setPlanSummary(summaryVos);
        return Result.success(info);
    }
}

