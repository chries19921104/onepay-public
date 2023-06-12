package org.example.agent.service.impl;

import io.swagger.models.auth.In;
import org.example.agent.dto.OverviewDto;
import org.example.agent.mapper.AgentOverviewMapper;
import org.example.agent.po.SuccessfulPo;
import org.example.agent.service.DashBoardService;
import org.example.agent.vo.OverviewVo;
import org.example.common.entity.SystemDepositOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DashBoardServiceImpl implements DashBoardService {
    @Autowired
    private AgentOverviewMapper agentOverviewMapper;

    @Override
    public OverviewVo getTodayOverView(OverviewDto overviewDto,Long agent_id) {
        String currency = overviewDto.getCurrency();
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
        LocalDateTime start = LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime end = LocalDateTime.of(year,month,day+1,0,0,0);
        ArrayList<Integer> modelList = new ArrayList<>();
        SuccessfulPo successfulFi = agentOverviewMapper.getSuccessfulFi(modelList,currency, start,end,agent_id);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_BANK);
        SuccessfulPo successTPIFi = agentOverviewMapper.getSuccessfulFi(modelList,currency, start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_QRPAY);
        SuccessfulPo successFiQr = agentOverviewMapper.getSuccessfulFi(modelList,currency, start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_TRUE_WALLET);
        SuccessfulPo successFiTrueWallet = agentOverviewMapper.getSuccessfulFi(modelList,currency, start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_ZALO_QR);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_MOMO_QR);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_VIETTEL_QR);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_VIETTEL_FIX);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_BANK_QR);
        SuccessfulPo successTPIFiQr = agentOverviewMapper.getSuccessfulFi(modelList,currency, start,end,agent_id);

        return null;
    }
}
