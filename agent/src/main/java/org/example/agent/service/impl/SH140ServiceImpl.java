package org.example.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.agent.bo.SummaryReportDataBo;
import org.example.agent.bo.TotalBalanceBo;
import org.example.agent.dto.DailyReportDto;
import org.example.agent.dto.SummaryReportDto;
import org.example.agent.mapper.SystemAgentMerchantIncomeStatisticsMapper;
import org.example.agent.mapper.SystemAgentRebateStatisticsMapper;
import org.example.agent.mapper.SystemAgentsMapper;
import org.example.agent.mapper.SystemMerchantMapper;
import org.example.agent.po.DailyReportPo;
import org.example.agent.po.SummaryReportDataPo;
import org.example.agent.service.SH140Service;
import org.example.agent.vo.AgentZoneMerchantVo;
import org.example.agent.vo.DailyBaseInfoVo;
import org.example.agent.vo.DailyReportVo;
import org.example.agent.vo.SummaryReportVo;
import org.example.common.entity.SystemAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SH140ServiceImpl implements SH140Service {

    final Integer IDENTITY_GEN_AGENT = 1; //总代
    final Integer IDENTITY_AGENT = 2; //代理

    @Autowired
    private SystemAgentRebateStatisticsMapper sarsMapper;

    @Autowired
    private SystemAgentMerchantIncomeStatisticsMapper samisMapper;

    @Autowired
    private SystemAgentsMapper systemAgentsMapper;

    @Autowired
    private SystemMerchantMapper systemMerchantMapper;

    @Override
    public DailyReportVo getDailyReportList(SystemAgents agent, DailyReportDto dailyReportDto) {
        List<DailyReportPo> dailyReportPoList = samisMapper.getDailyReportList(dailyReportDto.getSH100_ID(), dailyReportDto.getStart_date(), dailyReportDto.getEnd_date());
        DailyReportVo dailyReportVo = new DailyReportVo();
        TotalBalanceBo totals = new TotalBalanceBo();
        for (DailyReportPo aReport : dailyReportPoList) {
            //计算总计金额
            totals.setFI_bank_balance(totals.getFI_bank_balance().add(aReport.getFiBankBalance()));
            totals.setFI_MOMO_QR_balance(totals.getFI_MOMO_QR_balance().add(aReport.getFiVnpayMomoQrBalance()));
            totals.setFI_BANK_CARD_balance(totals.getFI_BANK_CARD_balance().add(aReport.getFiVnpayBankCardBalance()));
            totals.setFI_qrpay_balance(totals.getFI_qrpay_balance().add(aReport.getFiQrpayBalance()));
            totals.setFI_RCGCARD_ZING_balance(totals.getFI_RCGCARD_ZING_balance().add(aReport.getFiVnpayRcgcardZingBalance()));
            totals.setFI_RCGCARD_PC_balance(totals.getFI_RCGCARD_PC_balance().add(aReport.getFiVnpayRcgcardPcBalance()));
            totals.setFO_balance(totals.getFO_balance().add(aReport.getFoBalance()));
            totals.setFI_truewallet_balance(totals.getFI_truewallet_balance().add(aReport.getFiTruewalletBalance()));
            totals.setFI_VIETTEL_FIX_balance(totals.getFI_VIETTEL_FIX_balance().add(aReport.getFiVnpayViettelFixBalance()));
            totals.setFI_VIETTEL_QR_balance(totals.getFI_VIETTEL_QR_balance().add(aReport.getFiVnpayViettelQrBalance()));
            totals.setFI_ZALO_QR_balance(totals.getFI_ZALO_QR_balance().add(aReport.getFiVnpayZaloQrBalance()));

            //计算每日总计抽成率
            aReport.setTotalMarkup(
                    aReport.getFiBankMarkupRate()
                            .add(aReport.getFiQrpayMarkupRate())
                            .add(aReport.getFiTruewalletMarkupRate())
                            .add(aReport.getFoMarkupRate())
            );

        }
        dailyReportVo.setTotals(totals);
        dailyReportVo.setData(dailyReportPoList);

        return dailyReportVo;
    }

    @Override
    public DailyBaseInfoVo getDailyBaseInfo(Integer identity, Long belongId) {
        QueryWrapper<SystemAgents> wrapper = new QueryWrapper<>();
        wrapper.eq("identity", identity).eq("belong_id", belongId);
        SystemAgents agent = systemAgentsMapper.selectOne(wrapper);
        if (agent == null) return null;
        DailyBaseInfoVo dailyBaseInfoVo = new DailyBaseInfoVo();
        dailyBaseInfoVo.setId(agent.getAgentId());
        dailyBaseInfoVo.setUsername(agent.getUsername());
        dailyBaseInfoVo.setFull_name(agent.getFullName());
        dailyBaseInfoVo.setDisplay_id(agent.getUsername());
        dailyBaseInfoVo.setBelong_id(belongId);
        dailyBaseInfoVo.setIdentity(identity);
        return dailyBaseInfoVo;
    }

    @Override
    public List<AgentZoneMerchantVo> getAgentZoneMerchantList(String currency, Long agent_id) {
        return systemMerchantMapper.getAgentZoneMerchantList(currency, agent_id);
    }

    @Override
    public SummaryReportVo getSummaryReport(SummaryReportDto summaryReportDto) {
        SummaryReportDataBo summaryReportDataBo = new SummaryReportDataBo();
        TotalBalanceBo totalBalanceBo = new TotalBalanceBo();
        Integer start = (summaryReportDto.getPage()-1) * summaryReportDto.getRp();
        List<SummaryReportDataPo> summaryReportDataPoList = samisMapper.getSummaryReportDataPoList(
                summaryReportDto.getSH100_ID(),
                summaryReportDto.getYear(),
                summaryReportDto.getMonth(),
                start, summaryReportDto.getRp(),
                summaryReportDto.getCurrency());
        summaryReportDataBo.setData(summaryReportDataPoList);
        SummaryReportVo summaryReportVo = new SummaryReportVo();
        summaryReportVo.setData(summaryReportDataBo);
        summaryReportVo.setTotalBalance(totalBalanceBo);
        return summaryReportVo;
    }

}