package org.example.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.agent.base.Result;
import org.example.agent.constants.SystemConstants;
import org.example.agent.dto.SummaryDto;
import org.example.agent.mapper.AgentCommissionSettingsMapper;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.mapper.SystemMerchantMapper;
import org.example.agent.service.AgentCommissionSettingsService;
import org.example.agent.service.SystemMerchantService;
import org.example.agent.service.SystemRebateSchemeService;
import org.example.agent.utils.BeanCopyUtils;
import org.example.agent.utils.TokenUtils;
import org.example.agent.vo.CurrentRebateVo;
import org.example.agent.vo.PlanSummaryVo;
import org.example.agent.vo.SummaryVo;
import org.example.common.entity.SystemAgentCommissionSettings;
import org.example.common.entity.SystemMerchant;
import org.example.common.entity.SystemRebateScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    private SystemAgentsMapper systemAgentsMapper;
    @Autowired
    private AgentCommissionSettingsMapper agentCommissionSettingsMapper;
    @Autowired
    private AgentCommissionSettingsService agentCommissionSettingsService;
    @Autowired
    private SystemRebateSchemeService systemRebateScheme;
    @Autowired
    private HttpServletRequest request;

    @Override
    public Result loadPlanSummaryTable(SummaryDto summaryDto) {
        String token = request.getHeader("token");
//        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhZ2VudElkIjoxMSwibmJmIjoxNjg2NzA4NDY4LCJleHAiOjE2ODY3MTIwNjgsImlhdCI6MTY4NjcwODQ2OCwidXNlcm5hbWUiOiJhZG1pbiJ9.M2FFgkZkw-11hSwAuRxKu345HjGuYk2xy6H8oX0aJDk";
        Long agentId = TokenUtils.getAgentId(token);
        if (!(summaryDto.getAgentId() == null || summaryDto.getAgentId().toString().isEmpty())) {
            agentId = summaryDto.getAgentId();
        }
        PlanSummaryVo info = systemAgentsMapper.info(agentId);
        LambdaQueryWrapper<SystemMerchant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemMerchant::getAgentId, agentId)
                .eq(SystemMerchant::getStatus, SystemConstants.STATUS)
                .eq(SystemMerchant::getCurrency, summaryDto.getCurrency());
        wrapper.in(!(summaryDto.getSH100ID() == null || summaryDto.getSH100ID().isEmpty())
                , SystemMerchant::getMerchantId, summaryDto.getSH100ID());

        List<SystemMerchant> merchants = list(wrapper);
        List<CurrentRebateVo> currentRebates = agentCommissionSettingsMapper
                .currentRebate(agentId, summaryDto.getCurrency());
        List<SummaryVo> summarys = BeanCopyUtils.copyBeanList(merchants, SummaryVo.class);
        List<SystemRebateScheme> rebateSchemes = systemRebateScheme.list();

        for (SummaryVo summaryVo : summarys) {
            for (CurrentRebateVo currentRebate : currentRebates) {
                for (SystemRebateScheme rebateScheme : rebateSchemes) {
                    if (currentRebate.getBdrsRpId().equals(rebateScheme.getRsId())) {
                        currentRebate.setBankDepositRebatePlan(rebateScheme);
                    }
                    if (currentRebate.getQdrsRpId().equals(rebateScheme.getRsId())) {
                        currentRebate.setQrDepositRebatePlan(rebateScheme);
                    }
                    if (currentRebate.getWrsRpId().equals(rebateScheme.getRsId())) {
                        currentRebate.setWithdrawRebatePlan(rebateScheme);
                    }
                    if (currentRebate.getTwdrsRpId().equals(rebateScheme.getRsId())) {
                        currentRebate.setTrueWalletDepositRebatePlan(rebateScheme);
                    }
                }
                if (summaryVo.getMerchantId().equals(currentRebate.getSh100Id())) {
                    summaryVo.setCurrentRebate(currentRebate);
                    break;
                }
            }
        }

        LambdaQueryWrapper<SystemAgentCommissionSettings> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemAgentCommissionSettings::getAgentId, agentId)
                .eq(SystemAgentCommissionSettings::getActive, SystemConstants.ACTIVE);
        List<SystemAgentCommissionSettings> agentCommissionSettings
                = agentCommissionSettingsService.list(queryWrapper);
        LocalDate currentDate = LocalDate.now();
        for (SummaryVo summary : summarys) {
            List<SystemAgentCommissionSettings> nearest = new ArrayList<>();
            for (SystemAgentCommissionSettings date : agentCommissionSettings) {
                if (summary.getMerchantId().equals(date.getSh100Id())) {
                    if (date.getYear() >= currentDate.getYear() && date.getMonth() > currentDate.getMonthValue()) {
                        nearest.add(date);
                    }
                }
                List<SystemAgentCommissionSettings> agentCommissionListAsc = nearest.stream()
                        .sorted(Comparator.comparing(SystemAgentCommissionSettings::getYear)
                                .thenComparing(SystemAgentCommissionSettings::getMonth))
                        .collect(Collectors.toList());
                if (!agentCommissionListAsc.isEmpty()) {
                    summary.setNearestRebate(agentCommissionListAsc.get(SystemConstants.FIRST));
                }
            }
        }

        info.setPlanSummary(summarys);
        return Result.success(info);
    }
}

