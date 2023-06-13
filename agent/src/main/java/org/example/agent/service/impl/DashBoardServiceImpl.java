package org.example.agent.service.impl;

import org.example.agent.dto.OverviewDto;
import org.example.agent.mapper.AgentOverviewMapper;
import org.example.agent.po.SuccessfulPo;
import org.example.agent.service.DashBoardService;
import org.example.agent.vo.OverviewVo;
import org.example.common.entity.SystemDepositOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        // 获取查询使时间段
        LocalDateTime start = LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime end = LocalDateTime.of(year,month,day+1,0,0,0);
        ArrayList<Integer> modelList = new ArrayList<>();
        // 查询计算时的中间数据
        SuccessfulPo successFi = agentOverviewMapper.getSuccessfulFi(modelList,currency, start,end,agent_id);
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
        modelList.clear();
        Integer countAllSuccessfulFo = agentOverviewMapper.getSuccessfulFoAmount(modelList,currency,start,end,agent_id);
        SuccessfulPo successFo = agentOverviewMapper.getSuccessfulFo(modelList,currency,start,end,agent_id);
        modelList.add(SystemDepositOrder.TXN_MODE_EIGHTPAY_5_ONLY_WITHDRAWL);
        modelList.add(SystemDepositOrder.TXN_MODE_QIJIPAY_WITHDRAWL);
        modelList.add(SystemDepositOrder.TXN_MODE_DAPAY_FO);
        Integer successTPIFoAmount = agentOverviewMapper.getSuccessfulFoAmount(modelList,currency,start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_BANK_CARD);
        SuccessfulPo successFiBankTransfer = agentOverviewMapper.getSuccessfulFi(modelList,currency,start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_RCGCARD_PC_VIETTEL);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_RCGCARD_PC_MOBIFONE);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_RCGCARD_PC_VINAPHONE);
        modelList.add(SystemDepositOrder.TXN_MODE_VNPAY_RCGCARD_ZING);
        SuccessfulPo successFiPrepaid = agentOverviewMapper.getSuccessfulFi(modelList,currency,start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_DAPAY_ZFBYHK);
        SuccessfulPo successFiAlipayToBank = agentOverviewMapper.getSuccessfulFi(modelList,currency,start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_DAPAY_NETGATE);
        SuccessfulPo successFiUnionPayToBank = agentOverviewMapper.getSuccessfulFi(modelList,currency,start,end,agent_id);
        modelList.clear();
        modelList.add(SystemDepositOrder.TXN_MODE_DAPAY_BANK2BANK);
        modelList.add(SystemDepositOrder.TXN_MODE_H1PAY_UNION_PAY);
        SuccessfulPo successFiBankToBank = agentOverviewMapper.getSuccessfulFi(modelList,currency,start,end,agent_id);

        OverviewVo overviewVo = new OverviewVo();
        // 计算返回数据
        BigDecimal revenue = successFi.getRate().add(successFiQr.getRate()).add(successFiQr.getRate()).add(successFiTrueWallet.getRate()).add(successFo.getRate());
        Integer today_transaction = successFi.getCount() +
                successTPIFi.getCount() +
                successFiQr.getCount() +
                successFiTrueWallet.getCount() +
                successTPIFiQr.getCount() +
                countAllSuccessfulFo +
                successFiBankTransfer.getCount() +
                successFiPrepaid.getCount() +
                successFiAlipayToBank.getCount() +
                successFiUnionPayToBank.getCount() +
                successFiBankToBank.getCount();
        BigDecimal today_bank_deposit = successFi.getAmount().add(successTPIFi.getAmount());
        BigDecimal today_qr_deposit = successFiQr.getAmount().add(successTPIFiQr.getAmount());
        BigDecimal today_truewallet_deposit = successFiTrueWallet.getAmount();
        BigDecimal today_withdraw = successFo.getAmount().add(BigDecimal.valueOf(successTPIFoAmount));
        BigDecimal today_bank_transfer_deposit = successFiBankTransfer.getAmount();
        BigDecimal today_prepaid_deposit = successFiPrepaid.getAmount();
        BigDecimal today_AlipayToBank_deposit = successFiAlipayToBank.getAmount();
        BigDecimal today_UnionPayToBank_deposit = successFiUnionPayToBank.getAmount();
        BigDecimal today_BankToBank_deposit = successFiBankToBank.getAmount();
        overviewVo.setRevenue(revenue);
        overviewVo.setToday_ransacation(today_transaction);
        overviewVo.setToday_qr_deposit(today_qr_deposit);
        overviewVo.setToday_withdraw(today_withdraw);
        overviewVo.setToday_bank_deposit(today_bank_deposit);
        overviewVo.setToday_prepaid_deposit(today_prepaid_deposit);
        overviewVo.setToday_AlipayToBank_deposit(today_AlipayToBank_deposit);
        overviewVo.setToday_BankToBank_deposit(today_BankToBank_deposit);
        overviewVo.setToday_truewallet_deposit(today_truewallet_deposit);
        overviewVo.setToday_UnionPayToBank_deposit(today_UnionPayToBank_deposit);
        overviewVo.setToday_bank_transfer_deposit(today_bank_transfer_deposit);

        return overviewVo;
    }
}
