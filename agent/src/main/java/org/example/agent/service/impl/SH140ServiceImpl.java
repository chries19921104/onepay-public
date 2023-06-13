package org.example.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.agent.vo.SummaryReportDataVo;
import org.example.agent.vo.SummaryReportTotalsVo;
import org.example.agent.vo.TotalBalanceVo;
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
        TotalBalanceVo totals = new TotalBalanceVo();
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
        SummaryReportDataVo summaryReportDataVo = new SummaryReportDataVo();
        TotalBalanceVo totalBalanceVo = new TotalBalanceVo();
        SummaryReportTotalsVo subTotals =  new SummaryReportTotalsVo();
        SummaryReportTotalsVo totals = new SummaryReportTotalsVo();
        summaryReportDataVo.setSubtotalas(subTotals);
        summaryReportDataVo.setTotals(totals);
        Integer page = summaryReportDto.getPage();
        String prefix_url = "https://api-ta-outside.100scrop.tech/api/sh140/summaryReport?page=";
        summaryReportDataVo.setCurrent_page(page);
        //查询数据的总条数，计算页面信息
        Integer count_records = samisMapper.getTotalNumberOfPages(summaryReportDto.getSH100_ID(),
                summaryReportDto.getYear(),
                summaryReportDto.getMonth(),
                summaryReportDto.getCurrency());
        Integer count_page = (int) Math.ceil((double) count_records/ page);
        summaryReportDataVo.setLast_page(count_page);
        summaryReportDataVo.setTotal(count_records);
        Integer start = (summaryReportDto.getPage()-1) * summaryReportDto.getRp();
        List<SummaryReportDataPo> summaryReportDataPoList = samisMapper.getSummaryReportDataPoList(
                summaryReportDto.getSH100_ID(),
                summaryReportDto.getYear(),
                summaryReportDto.getMonth(),
                start, summaryReportDto.getRp(),
                summaryReportDto.getCurrency());
        summaryReportDataVo.setData(summaryReportDataPoList);

        // 计算total_balance的值
        for (SummaryReportDataPo one: summaryReportDataPoList) {
            totalBalanceVo.setFO_balance(totalBalanceVo.getFO_balance().add(one.getFoBalance()));
            totalBalanceVo.setFI_qrpay_balance(totalBalanceVo.getFI_qrpay_balance().add(one.getFiQrpayBalance()));
            totalBalanceVo.setFI_bank_balance(totalBalanceVo.getFI_bank_balance().add(one.getFiBankBalance()));
            totalBalanceVo.setFI_MOMO_QR_balance(totalBalanceVo.getFI_MOMO_QR_balance().add(one.getFiVnpayMomoQrBalance()));
            totalBalanceVo.setFI_RCGCARD_PC_balance(totalBalanceVo.getFI_RCGCARD_PC_balance().add(one.getFiVnpayRcgcardPcBalance()));
            totalBalanceVo.setFI_RCGCARD_ZING_balance(totalBalanceVo.getFI_RCGCARD_ZING_balance().add(one.getFiVnpayRcgcardZingBalance()));
            totalBalanceVo.setFI_BANK_CARD_balance(totalBalanceVo.getFI_BANK_CARD_balance().add(one.getFiVnpayBankCardBalance()));
            totalBalanceVo.setFI_VIETTEL_FIX_balance(totalBalanceVo.getFI_VIETTEL_FIX_balance().add(one.getFiVnpayViettelFixBalance()));
            totalBalanceVo.setFI_VIETTEL_QR_balance(totalBalanceVo.getFI_VIETTEL_QR_balance().add(one.getFiVnpayViettelQrBalance()));
            totalBalanceVo.setFI_truewallet_balance(totalBalanceVo.getFI_truewallet_balance().add(one.getFiTruewalletBalance()));
            totalBalanceVo.setFI_ZALO_QR_balance(totalBalanceVo.getFI_ZALO_QR_balance().add(one.getFiVnpayZaloQrBalance()));
        }
        SummaryReportVo summaryReportVo = new SummaryReportVo();
        summaryReportVo.setData(summaryReportDataVo);
        summaryReportVo.setTotalBalance(totalBalanceVo);
        return summaryReportVo;
    }



}
