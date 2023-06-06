package org.example.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.SystemBankCardService;
import org.example.admin.service.SystemDepositOrderService;
import org.example.common.base.CommResp;
import org.example.admin.dto.DashboardDto;
import org.example.common.entity.SystemBankCard;
import org.example.common.entity.SystemDepositOrder;
import org.example.admin.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
    String tomorrow = DateUtil.formatDate(DateUtil.tomorrow());
    String yesterday = DateUtil.formatDate(DateUtil.yesterday());
    private final static String successStatus = "1";
    private final static String failStatus = "4,5";
    private final static String incomeStatus = "3";
    private DataVo dataVo = new DataVo();


    @Override
    public CommResp selectTxnModeByRegion(DashboardDto dashboardDto) {
        StringBuilder cardId = new StringBuilder();
        StringBuilder merchantId = new StringBuilder();
        if (dashboardDto.getCardGroupId() != null) {
            List<SystemBankCard> cards = systemBankCardService
                    .query().eq("card_group_id", dashboardDto.getCardGroupId())
                    .select("card_id").list();
            if (cards != null) {
                for (SystemBankCard card : cards) {
                    cardId.append(card.getCardId() + ",");
                }
                cardId.deleteCharAt(cardId.length() - 1);
            }
        }
        if (dashboardDto.getMerchantId() != null) {
            for (String s : dashboardDto.getMerchantId()) {
                merchantId.append(s + ",");
            }
            merchantId.deleteCharAt(merchantId.length() - 1);
        }
        todaySuccess(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        todayFail(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        income(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        commission(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        loss(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        merchant(dashboardDto.getCardGroupId(), dashboardDto.getCurrency());
        freezeLoss(dashboardDto.getCardGroupId(), dashboardDto.getCurrency());
        //整理数据返回前端
        return CommResp.data(dataVo);
    }

    @Override
    public CommResp infoText() {
        HashMap<String, HashMap<String, DepositQRLossCommissionVo>> hs = info();
        return CommResp.data(hs);
    }


    //今日,昨日成功数据
    public void todaySuccess(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, successStatus, cardId, merchantId);
        //银行
        if (todayList != null && todayList.size() != 0) {
            for (SystemDepositOrderVo systemDepositOrderVo : todayList) {
                switch (systemDepositOrderVo.getTxnMode()) {
                    case 1: {
                        dataVo.setTodaySuccessBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessBankCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setTodayBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                    case 2:{
                        //QR
                        dataVo.setTodaySuccessQRMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessQRCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setTodayQRIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 3:{
                        //True Wallet
                        dataVo.setTodaySuccessTWMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessTWCount(systemDepositOrderVo.getSuccessCount());

                        dataVo.setTodayTWIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 4:{
                        //本地银行
                        dataVo.setTodaySuccessBDBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessBDBankCount(systemDepositOrderVo.getSuccessCount());

                        dataVo.setTodayBDBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                        ;
                }
            }
        }
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, successStatus, cardId, merchantId);
        if (yesterdayList != null && yesterdayList.size() != 0) {
            for (SystemDepositOrderVo systemDepositOrderVo : yesterdayList) {

                switch (systemDepositOrderVo.getTxnMode()) {
                    case 1: {
                        dataVo.setYesterdaySuccessBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessBankCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setYesterdayBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                    case 2:{
                        //QR
                        dataVo.setYesterdaySuccessQRMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessQRCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setYesterdayQRIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 3:{
                        //True WalYesterday
                        dataVo.setYesterdaySuccessTWMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessTWCount(systemDepositOrderVo.getSuccessCount());

                        dataVo.setYesterdayTWIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 4:{
                        //本地银行
                        dataVo.setYesterdaySuccessBDBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessBDBankCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setYesterdayBDBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                    ;
                }
            }
        }
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
        dataVo.setTodaySuccessXFMoney(todayXF.getSuccessMoney());
        dataVo.setTodaySuccessXFCount(todayXF.getSuccessCount());

        dataVo.setYesterdaySuccessXFMoney(yesterdayXF.getSuccessMoney());
        dataVo.setYesterdaySuccessXFCount(yesterdayXF.getSuccessCount());
        //虚拟币
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, successStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, successStatus, cardId);
        dataVo.setTodaySuccessXNMoney(todayXN.getSuccessMoney());
        dataVo.setTodaySuccessXNCount(todayXN.getSuccessCount());

        dataVo.setYesterdaySuccessXNMoney(yesterdayXN.getSuccessMoney());
        dataVo.setYesterdaySuccessXNCount(yesterdayXN.getSuccessCount());
    }
    //今日,昨日失败数据
    public void todayFail(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, failStatus, cardId, merchantId);
        if (todayList != null && todayList.size() != 0) {
            for (SystemDepositOrderVo systemDepositOrderVo : todayList) {
                switch (systemDepositOrderVo.getTxnMode()){
                    case 1:{
                        //银行
                        dataVo.setTodayFailBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailBankCount(systemDepositOrderVo.getFailCount());
                    }
                    case 2:{
                        //QR
                        dataVo.setTodayFailQRMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailQRCount(systemDepositOrderVo.getFailCount());
                    }
                    case 3:{
                        //True Wallet
                        dataVo.setTodayFailTWMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailTWCount(systemDepositOrderVo.getFailCount());
                    }
                    case 4:{
                        //本地银行
                        dataVo.setTodayFailBDBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailBDBankCount(systemDepositOrderVo.getFailCount());
                    }
                }
            }
        }
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, failStatus, cardId, merchantId);
        if (yesterdayList != null && yesterdayList.size() != 0) {

            for (SystemDepositOrderVo systemDepositOrderVo : yesterdayList) {
                switch (systemDepositOrderVo.getTxnMode()){
                    case 1:{
                        //银行
                        dataVo.setYesterdayFailBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailBankCount(systemDepositOrderVo.getFailCount());
                    }
                    case 2:{
                        //QR
                        dataVo.setYesterdayFailQRMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailQRCount(systemDepositOrderVo.getFailCount());
                    }
                    case 3:{
                        //True WalYesterday
                        dataVo.setYesterdayFailTWMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailTWCount(systemDepositOrderVo.getFailCount());
                    }
                    case 4:{
                        //本地银行
                        dataVo.setYesterdayFailBDBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailBDBankCount(systemDepositOrderVo.getFailCount());
                    }
                }
        }
        //代付
        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, failStatus, cardId);
        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, failStatus, cardId);
        dataVo.setTodayFailDFMoney(todayDF.getFailMoney());
        dataVo.setTodayFailDFCount(todayDF.getFailCount());

        dataVo.setYesterdayFailDFMoney(yesterdayDF.getFailMoney());
        dataVo.setYesterdayFailDFCount(yesterdayDF.getFailCount());

        //下发
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, failStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, failStatus, cardId);
        dataVo.setTodayFailXFMoney(todayXF.getFailMoney());
        dataVo.setTodayFailXFCount(todayXF.getFailCount());

        dataVo.setYesterdayFailXFMoney(yesterdayXF.getFailMoney());
        dataVo.setYesterdayFailXFCount(yesterdayXF.getFailCount());
        //虚拟币
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, failStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, failStatus, cardId);
        dataVo.setTodayFailXNMoney(todayXN.getFailMoney());
        dataVo.setTodayFailXNCount(todayXN.getFailCount());

        dataVo.setYesterdayFailXNMoney(yesterdayXN.getFailMoney());
        dataVo.setYesterdayFailXNCount(yesterdayXN.getFailCount());
    }
    }



    //收入
    private void income(String currency, String cardId, String merchantId) {
        //今日收入
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, incomeStatus, cardId, merchantId);

        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, incomeStatus, cardId);
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, incomeStatus, cardId);
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, incomeStatus, cardId);

        if (todayList != null && todayList.size() != 0) {
            dataVo.setTodayBankIncome(amountFee(todayList.get(0).getFailMoney(), todayList.get(0).getBankFee()));
            dataVo.setTodayQRIncome(amountFee(todayList.get(1).getFailMoney(), todayList.get(1).getBankFee()));
            dataVo.setTodayTWIncome(amountFee(todayList.get(2).getFailMoney(), todayList.get(2).getBankFee()));
            dataVo.setTodayBDBankIncome(amountFee(todayList.get(3).getFailMoney(), todayList.get(3).getBankFee()));
            dataVo.setTodayDFIncome(amountFee(todayDF.getFailMoney(), todayDF.getBankFee()));
            dataVo.setTodayXFIncome(amountFee(todayXF.getFailMoney(), todayXF.getBankFee()));
            dataVo.setTodayXNIncome(amountFee(todayXN.getFailMoney(), todayXN.getBankFee()));
        }

        //昨日收入 Yesterday
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, incomeStatus, cardId, merchantId);
        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, incomeStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, incomeStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, incomeStatus, cardId);
        if (yesterdayList != null && yesterdayList.size() != 0) {
            dataVo.setYesterdayBankIncome(amountFee(yesterdayList.get(0).getFailMoney(), yesterdayList.get(0).getBankFee()));
            dataVo.setYesterdayQRIncome(amountFee(yesterdayList.get(1).getFailMoney(), yesterdayList.get(1).getBankFee()));
            dataVo.setYesterdayTWIncome(amountFee(yesterdayList.get(2).getFailMoney(), yesterdayList.get(2).getBankFee()));
            dataVo.setYesterdayBDBankIncome(amountFee(yesterdayList.get(3).getFailMoney(), yesterdayList.get(3).getBankFee()));
        }
        dataVo.setYesterdayDFIncome(amountFee(yesterdayDF.getFailMoney(), yesterdayDF.getBankFee()));
        dataVo.setYesterdayXFIncome(amountFee(yesterdayXF.getFailMoney(), yesterdayXF.getBankFee()));
        dataVo.setYesterdayXNIncome(amountFee(yesterdayXN.getFailMoney(), yesterdayXN.getBankFee()));
        //本月
        List<SystemDepositOrderVo> thisMonthList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, getThisMonth(), getXMonth(), incomeStatus, cardId, merchantId);
        SystemDepositOrderVo thisMonthDF = systemDepositOrderMapper.selectWithdrawal(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);
        SystemDepositOrderVo thisMonthXF = systemDepositOrderMapper.selectSettlement(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);
        SystemDepositOrderVo thisMonthXN = systemDepositOrderMapper.selectCrypto(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);

        if (thisMonthList != null && thisMonthList.size() != 0) {
            dataVo.setThisMonthBankIncome(amountFee(thisMonthList.get(0).getFailMoney(), thisMonthList.get(0).getBankFee()));
            dataVo.setThisMonthQRIncome(amountFee(thisMonthList.get(1).getFailMoney(), thisMonthList.get(1).getBankFee()));
            dataVo.setThisMonthTWIncome(amountFee(thisMonthList.get(2).getFailMoney(), thisMonthList.get(2).getBankFee()));
            dataVo.setThisMonthBDBankIncome(amountFee(thisMonthList.get(3).getFailMoney(), thisMonthList.get(3).getBankFee()));
        }
        dataVo.setThisMonthDFIncome(amountFee(thisMonthDF.getFailMoney(), thisMonthDF.getBankFee()));
        dataVo.setThisMonthXFIncome(amountFee(thisMonthXF.getFailMoney(), thisMonthXF.getBankFee()));
        dataVo.setThisMonthXNIncome(amountFee(thisMonthXN.getFailMoney(), thisMonthXN.getBankFee()));

        //上月
        List<SystemDepositOrderVo> lastMonthList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId, merchantId);
        SystemDepositOrderVo lastMonthDF = systemDepositOrderMapper.selectWithdrawal(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);
        SystemDepositOrderVo lastMonthXF = systemDepositOrderMapper.selectSettlement(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);
        SystemDepositOrderVo lastMonthXN = systemDepositOrderMapper.selectCrypto(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);

        if (lastMonthList != null && lastMonthList.size() != 0) {
            dataVo.setLastMonthBankIncome(amountFee(lastMonthList.get(0).getFailMoney(), lastMonthList.get(0).getBankFee()));
            dataVo.setLastMonthQRIncome(amountFee(lastMonthList.get(1).getFailMoney(), lastMonthList.get(1).getBankFee()));
            dataVo.setLastMonthTWIncome(amountFee(lastMonthList.get(2).getFailMoney(), lastMonthList.get(2).getBankFee()));
            dataVo.setLastMonthBDBankIncome(amountFee(lastMonthList.get(3).getFailMoney(), lastMonthList.get(3).getBankFee()));
        }
        dataVo.setLastMonthDFIncome(amountFee(lastMonthDF.getFailMoney(), lastMonthDF.getBankFee()));
        dataVo.setLastMonthXFIncome(amountFee(lastMonthXF.getFailMoney(), lastMonthXF.getBankFee()));
        dataVo.setLastMonthXNIncome(amountFee(lastMonthXN.getFailMoney(), lastMonthXN.getBankFee()));


    }

    //获取本月
    public static final String getThisMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        return format.format(date);
    }
    //获取上月

    /**
     * 获取上个月月份
     *
     * @return
     */
    public static String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        // 设置为上一个月
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * 获取下个月月份
     *
     * @return
     */
    public static String getXMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        // 设置为上一个月
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        return format.format(date);
    }

    //银行手续费
    private void commission(String currency, String cardId, String merchantId) {
        //今日
        List<SystemDepositOrderCommissionVo> todayCommission =
                systemDepositOrderMapper.selectWithdrawalrCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayCommission != null && todayCommission.size() != 0) {
            dataVo.setTodayMyCommission(todayCommission.get(0).getMoney().multiply(todayCommission.get(0).getBankFee()));
            dataVo.setTodayMyCommission(todayCommission.get(1).getMoney().multiply(todayCommission.get(1).getMerchantFee()));
        }
        //结算
        SystemDepositOrderCommissionVo todayJS = systemDepositOrderMapper.selectSettlementCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayJS!=null){
            dataVo.setTodayJSCommission(todayJS.getMoney().multiply(todayJS.getBankFee()));
        }

        //内转
        SystemDepositOrderCommissionVo todayNZ = systemDepositOrderMapper.selectIntroversionCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayNZ!=null){
            dataVo.setTodayNZCommission(todayNZ.getMoney().multiply(todayNZ.getBankFee()));
        }

        //外传
        SystemDepositOrderCommissionVo todayWZ = systemDepositOrderMapper.selectExternalTradeCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayWZ!=null){
            dataVo.setTodayWZCommission(todayWZ.getMoney().multiply(todayWZ.getBankFee()));
        }

        //昨日
        List<SystemDepositOrderCommissionVo> yesterdayCommission =
                systemDepositOrderMapper.selectWithdrawalrCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayCommission != null && yesterdayCommission.size() != 0
        ) {
            dataVo.setYesterdayMyCommission(yesterdayCommission.get(0).getMoney().multiply(yesterdayCommission.get(0).getBankFee()));
            dataVo.setYesterdayMyCommission(yesterdayCommission.get(1).getMoney().multiply(yesterdayCommission.get(1).getMerchantFee()));
        }
        //结算
        SystemDepositOrderCommissionVo yesterdayJS = systemDepositOrderMapper.selectSettlementCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayJS!=null){
            dataVo.setYesterdayJSCommission(yesterdayJS.getMoney().multiply(yesterdayJS.getBankFee()));
        }

        //内转
        SystemDepositOrderCommissionVo yesterdayNZ = systemDepositOrderMapper.selectIntroversionCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayNZ!=null){
            dataVo.setYesterdayNZCommission(yesterdayNZ.getMoney().multiply(yesterdayNZ.getBankFee()));
        }

        //外传
        SystemDepositOrderCommissionVo yesterdayWZ = systemDepositOrderMapper.selectExternalTradeCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayWZ!=null){
            dataVo.setTodayWZCommission(yesterdayWZ.getMoney().multiply(yesterdayWZ.getBankFee()));
        }

    }

    //损失
    private void loss(String currency, String cardId, String merchantId) {
        //QR
        DepositQRLossCommissionVo todayQRLoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayQRLoss(todayQRLoss.getMoney());
        dataVo.setTodayQRCount(todayQRLoss.getCount());
        DepositQRLossCommissionVo thisQRLoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, getThisMonth(), getXMonth(), cardId, merchantId);
        dataVo.setThisQRLoss(thisQRLoss.getMoney());
        dataVo.setThisQRCount(thisQRLoss.getCount());
        DepositQRLossCommissionVo lastQRLoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, getLastMonth(), getThisMonth(), cardId, merchantId);
        dataVo.setLastQRLoss(lastQRLoss.getMoney());
        dataVo.setLastQRCount(lastQRLoss.getCount());
        DepositQRLossCommissionVo allLQRoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, null, null, cardId, merchantId);
        dataVo.setAllQRLoss(allLQRoss.getMoney());
        dataVo.setAllQRCount(allLQRoss.getCount());
        //True
        DepositQRLossCommissionVo todayTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayTrueLoss(todayTrueLoss.getMoney());
        dataVo.setTodayTrueCount(todayTrueLoss.getCount());
        DepositQRLossCommissionVo thisTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, getThisMonth(), getXMonth(), cardId, merchantId);
        dataVo.setThisTrueLoss(thisTrueLoss.getMoney());
        dataVo.setThisTrueCount(thisTrueLoss.getCount());
        DepositQRLossCommissionVo lastTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, getLastMonth(), getThisMonth(), cardId, merchantId);
        dataVo.setLastTrueLoss(lastTrueLoss.getMoney());
        dataVo.setLastTrueCount(lastTrueLoss.getCount());
        DepositQRLossCommissionVo allTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, null, null, cardId, merchantId);
        dataVo.setAllTrueLoss(allTrueLoss.getMoney());
        dataVo.setAllTrueCount(allTrueLoss.getCount());
    }

    //商户
    private void merchant(Integer cardGroupId, String currency) {
        dataVo.setTodayMerchantRegister(systemDepositOrderMapper.selectMerchantRegister(currency, today, tomorrow, cardGroupId));
        dataVo.setYesterdayMerchantRegister(systemDepositOrderMapper.selectMerchantRegister(currency, yesterday, today, cardGroupId));
        dataVo.setSuccessMerchantExamine(systemDepositOrderMapper.selectMerchantExamine(currency, "1", cardGroupId));
        dataVo.setFailMerchantExamine(systemDepositOrderMapper.selectMerchantExamine(currency, "0", cardGroupId));
    }

    //损失/数量
    private void freezeLoss(Integer cardGroupId, String currency) {
        SystemDepositOrderInfoVo today = systemDepositOrderMapper.selectBankCardFreeze(currency, this.today, tomorrow, cardGroupId);

        dataVo.setTodayFreezeLoss(today.getAmount());
        dataVo.setTodayFreezeCount(today.getCount());
        SystemDepositOrderInfoVo all = systemDepositOrderMapper.selectBankCardFreeze(currency, null, null, cardGroupId);
        dataVo.setAllFreezeLoss(all.getAmount());
        dataVo.setAllFreezeCount(all.getCount());
    }
    private BigDecimal amountFee(@NotNull  BigDecimal money, @NotNull BigDecimal fee) {
        if (money==null){
            money=new BigDecimal(0);
        }
        if (fee==null){
            fee =new BigDecimal(0);
        }
        return money.subtract(money.multiply(fee));
    }


    private HashMap<String, HashMap<String, DepositQRLossCommissionVo>> info() {
        HashMap<String, HashMap<String, DepositQRLossCommissionVo>> hm = new HashMap<>();
        String[] currency = new String[]{"THB", "kVND", "kIDR", "CNY"};
        String[] table = new String[]{"system_withdrawal_order", "system_sub_settlement_order"};
        String[] ss = new String[]{"processing_fo", "processing_fx"};
        for (int i = 0; i < table.length; i++) {
            HashMap<String, DepositQRLossCommissionVo> hs = new HashMap<>();
            for (int j = 0; j < currency.length; j++) {
                DepositQRLossCommissionVo vo = systemDepositOrderMapper.processingFoAmount(currency[j], table[i]);
                hs.put(currency[j], vo);
            }
            hm.put(ss[i], hs);
        }
        String[] confirmable = new String[]{"1", "2"};
        String[] sss = new String[]{"confirmable_fo", "mer_confirm_fo"};
        for (int i = 0; i < confirmable.length; i++) {
            HashMap<String, DepositQRLossCommissionVo> hs = new HashMap<>();
            for (int j = 0; j < currency.length; j++) {
                DepositQRLossCommissionVo vo = systemDepositOrderMapper.confirmableFoAmount(currency[j], confirmable[i]);
                hs.put(currency[j], vo);
            }
            hm.put(sss[i], hs);

        }
        HashMap<String, DepositQRLossCommissionVo> hs = new HashMap<>();
        for (int i = 0; i < currency.length; i++) {

            DepositQRLossCommissionVo vo = systemDepositOrderMapper.approvalEtAmount(currency[i]);
            hs.put(currency[i], vo);
        }
        hm.put("approval_external", hs);

        return hm;
    }

}

