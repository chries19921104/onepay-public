package org.example.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.SystemBankCardService;
import org.example.admin.service.SystemDepositOrderService;
import org.example.common.base.CommResp;
import org.example.common.dto.DashboardDto;
import org.example.common.entity.SystemBankCard;
import org.example.common.entity.SystemDepositOrder;
import org.example.common.vo.DataVo;
import org.example.common.vo.SystemDepositOrderVo;
import org.example.common.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * system_deposit_order Service 接口实现
 * </p>
 *
 * @author zhangmi
 * @since 2023-05-17 19:16:15
 */
@Service
@Transactional
@Slf4j
public class SystemDepositOrderServiceImpl extends ServiceImpl<SystemDepositOrderMapper, SystemDepositOrder> implements SystemDepositOrderService {

    @Autowired
    private SystemDepositOrderMapper systemDepositOrderMapper;
    @Autowired
    private SystemBankCardService systemBankCardService;
    String today = DateUtil.today();
    String tomorrow = DateUtil.formatTime(DateUtil.tomorrow());
    String yesterday = DateUtil.formatDate(DateUtil.yesterday());
    String successStatus = "1";
    String failStatus = "4,5";
    private DataVo dataVo =new DataVo();


    @Override
    public CommResp selectTxnModeByRegion(DashboardDto dashboardDto) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if (dashboardDto.getCardGroupId() != null) {
            List<SystemBankCard> cards = systemBankCardService
                    .query().eq("card_group_id", dashboardDto.getCardGroupId())
                    .select("card_id").list();
            if (cards != null) {
                for (SystemBankCard card : cards) {
                    sb1.append(card.getCardId() + ",");
                }
                sb1.deleteCharAt(sb1.length() - 1);
            }
        }
        if (dashboardDto.getMerchantId() != null) {
            for (String s : dashboardDto.getMerchantId()) {
                sb2.append(s + ",");
            }
            sb2.deleteCharAt(sb2.length() - 1);
        }
        //昨日失败数据
        List<SystemDepositOrderVo> list1 = systemDepositOrderMapper
                .selectMoneyAndCount(dashboardDto.getCurrency(), DateUtil.formatDate(DateUtil.yesterday()), DateUtil.today(), " 4,5", sb1.toString(), sb2.toString());
        //今日成功数据
        //今日失败数据
        //整理数据返回前端
        return CommResp.data(list1);
    }

    //今日,昨日成功数据
    public void todaySuccess(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, successStatus, cardId, merchantId);
        //银行
        dataVo.setTodaySuccessBankMoney(todayList.get(0).getSuccessMoney());
        dataVo.setTodaySuccessBankCount(todayList.get(0).getSuccessCount());
        //QR
        dataVo.setTodaySuccessQRMoney(todayList.get(1).getSuccessMoney());
        dataVo.setTodaySuccessQRCount(todayList.get(1).getSuccessCount());
        //True Wallet
        dataVo.setTodaySuccessTWMoney(todayList.get(2).getSuccessMoney());
        dataVo.setTodaySuccessTWCount(todayList.get(2).getSuccessCount());
        //本地银行
        dataVo.setTodaySuccessBDBankMoney(todayList.get(3).getSuccessMoney());
        dataVo.setTodaySuccessBDBankCount(todayList.get(3).getSuccessCount());



        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, successStatus, cardId, merchantId);

        //银行 Yesterday
        dataVo.setYesterdaySuccessBankMoney(yesterdayList.get(0).getSuccessMoney());
        dataVo.setYesterdaySuccessBankCount(yesterdayList.get(0).getSuccessCount());
        //QR
        dataVo.setYesterdaySuccessQRMoney(yesterdayList.get(1).getSuccessMoney());
        dataVo.setYesterdaySuccessQRCount(yesterdayList.get(1).getSuccessCount());
        //True Wallet
        dataVo.setYesterdaySuccessTWMoney(yesterdayList.get(2).getSuccessMoney());
        dataVo.setYesterdaySuccessTWCount(yesterdayList.get(2).getSuccessCount());
        //本地银行
        dataVo.setYesterdaySuccessBDBankMoney(yesterdayList.get(3).getSuccessMoney());
        dataVo.setYesterdaySuccessBDBankCount(yesterdayList.get(3).getSuccessCount());


        //代付
        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, successStatus, cardId);
        dataVo.setTodaySuccessDFMoney(todayDF.getSuccessMoney());
        dataVo.setTodaySuccessDFCount(todayDF.getSuccessCount());
        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, successStatus, cardId);
        dataVo.setYesterdaySuccessDFMoney(yesterdayDF.getSuccessMoney());
        dataVo.setYesterdaySuccessDFCount(yesterdayDF.getSuccessCount());
        //下发
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, successStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, successStatus, cardId);

        //虚拟币
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, successStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, successStatus, cardId);
    }

    //今日,昨日失败数据
    public void todayFail(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, failStatus, cardId, merchantId);

        //银行
        dataVo.setTodayFailBankMoney(todayList.get(0).getFailMoney());
        dataVo.setTodayFailBankCount(todayList.get(0).getFailCount());
        //QR
        dataVo.setTodayFailQRMoney(todayList.get(1).getFailMoney());
        dataVo.setTodayFailQRCount(todayList.get(1).getFailCount());
        //True Wallet
        dataVo.setTodayFailTWMoney(todayList.get(2).getFailMoney());
        dataVo.setTodayFailTWCount(todayList.get(2).getFailCount());
        //本地银行
        dataVo.setTodayFailBDBankMoney(todayList.get(3).getFailMoney());
        dataVo.setTodayFailBDBankCount(todayList.get(3).getFailCount());


        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, failStatus, cardId, merchantId);

        //银行 Yesterday
        dataVo.setYesterdayFailBankMoney(yesterdayList.get(0).getFailMoney());
        dataVo.setYesterdayFailBankCount(yesterdayList.get(0).getFailCount());
        //QR
        dataVo.setYesterdayFailQRMoney(yesterdayList.get(1).getFailMoney());
        dataVo.setYesterdayFailQRCount(yesterdayList.get(1).getFailCount());
        //True Wallet
        dataVo.setYesterdayFailTWMoney(yesterdayList.get(2).getFailMoney());
        dataVo.setYesterdayFailTWCount(yesterdayList.get(2).getFailCount());
        //本地银行
        dataVo.setYesterdayFailBDBankMoney(yesterdayList.get(3).getFailMoney());
        dataVo.setYesterdayFailBDBankCount(yesterdayList.get(3).getFailCount());
        //代付
        systemDepositOrderMapper.selectWithdrawal(currency,today,tomorrow,failStatus,cardId);
        systemDepositOrderMapper.selectWithdrawal(currency,yesterday,today,failStatus,cardId);
        //下发
        systemDepositOrderMapper.selectSettlement(currency,today,tomorrow,failStatus,cardId);
        systemDepositOrderMapper.selectSettlement(currency,yesterday,today,failStatus,cardId);
        //虚拟币
        systemDepositOrderMapper.selectCrypto(currency,today,tomorrow,failStatus,cardId);
        systemDepositOrderMapper.selectCrypto(currency,yesterday,today,failStatus,cardId);
    }

}

