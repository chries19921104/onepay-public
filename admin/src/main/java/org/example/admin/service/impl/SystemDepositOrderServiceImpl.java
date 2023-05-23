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
import org.example.common.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        //整理数据返回前端
        return CommResp.data(dataVo);
    }

    //今日,昨日成功数据
    public void todaySuccess(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, successStatus, cardId, merchantId);
        //银行
        dataVo.setTodaySuccessBankMoney(todayList.get(0).getSuccessMoney());
        dataVo.setTodaySuccessBankCount(todayList.get(0).getSuccessCount());

        dataVo.setTodayBankIncome(amountFee(todayList.get(0).getFailMoney(), todayList.get(0).getBankFee()));

        //QR
        dataVo.setTodaySuccessQRMoney(todayList.get(1).getSuccessMoney());
        dataVo.setTodaySuccessQRCount(todayList.get(1).getSuccessCount());

        dataVo.setTodayQRIncome(amountFee(todayList.get(1).getFailMoney(), todayList.get(1).getBankFee()));
        //True Wallet
        dataVo.setTodaySuccessTWMoney(todayList.get(2).getSuccessMoney());
        dataVo.setTodaySuccessTWCount(todayList.get(2).getSuccessCount());

        dataVo.setTodayTWIncome(amountFee(todayList.get(2).getFailMoney(), todayList.get(2).getBankFee()));
        //本地银行
        dataVo.setTodaySuccessBDBankMoney(todayList.get(3).getSuccessMoney());
        dataVo.setTodaySuccessBDBankCount(todayList.get(3).getSuccessCount());

        dataVo.setTodayBDBankIncome(amountFee(todayList.get(3).getFailMoney(), todayList.get(3).getBankFee()));


        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, successStatus, cardId, merchantId);

        //银行 Yesterday
        dataVo.setYesterdaySuccessBankMoney(yesterdayList.get(0).getSuccessMoney());
        dataVo.setYesterdaySuccessBankCount(yesterdayList.get(0).getSuccessCount());
        dataVo.setYesterdayBankIncome(amountFee(yesterdayList.get(0).getFailMoney(), yesterdayList.get(0).getBankFee()));
        //QR
        dataVo.setYesterdaySuccessQRMoney(yesterdayList.get(1).getSuccessMoney());
        dataVo.setYesterdaySuccessQRCount(yesterdayList.get(1).getSuccessCount());

        dataVo.setYesterdayBankIncome(amountFee(yesterdayList.get(0).getFailMoney(), yesterdayList.get(0).getBankFee()));
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

    private BigDecimal amountFee(BigDecimal money, BigDecimal fee) {
        return money.subtract(money.multiply(fee));
    }

    //收入
    private void income(String currency, String cardId, String merchantId) {
        //今日收入
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, incomeStatus, cardId, merchantId);

        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, incomeStatus, cardId);
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, incomeStatus, cardId);
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, incomeStatus, cardId);
        dataVo.setTodayBankIncome(amountFee(todayList.get(0).getFailMoney(), todayList.get(0).getBankFee()));
        dataVo.setTodayQRIncome(amountFee(todayList.get(1).getFailMoney(), todayList.get(1).getBankFee()));
        dataVo.setTodayTWIncome(amountFee(todayList.get(2).getFailMoney(), todayList.get(2).getBankFee()));
        dataVo.setTodayBDBankIncome(amountFee(todayList.get(3).getFailMoney(), todayList.get(3).getBankFee()));
        dataVo.setTodayDFIncome(amountFee(todayDF.getFailMoney(), todayDF.getBankFee()));
        dataVo.setTodayXFIncome(amountFee(todayXF.getFailMoney(), todayXF.getBankFee()));
        dataVo.setTodayXNIncome(amountFee(todayXN.getFailMoney(), todayXN.getBankFee()));

        //昨日收入 Yesterday
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, incomeStatus, cardId, merchantId);
        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, incomeStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, incomeStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, incomeStatus, cardId);
        dataVo.setYesterdayBankIncome(amountFee(yesterdayList.get(0).getFailMoney(), yesterdayList.get(0).getBankFee()));
        dataVo.setYesterdayQRIncome(amountFee(yesterdayList.get(1).getFailMoney(), yesterdayList.get(1).getBankFee()));
        dataVo.setYesterdayTWIncome(amountFee(yesterdayList.get(2).getFailMoney(), yesterdayList.get(2).getBankFee()));
        dataVo.setYesterdayBDBankIncome(amountFee(yesterdayList.get(3).getFailMoney(), yesterdayList.get(3).getBankFee()));
        dataVo.setYesterdayDFIncome(amountFee(yesterdayDF.getFailMoney(), yesterdayDF.getBankFee()));
        dataVo.setYesterdayXFIncome(amountFee(yesterdayXF.getFailMoney(), yesterdayXF.getBankFee()));
        dataVo.setYesterdayXNIncome(amountFee(yesterdayXN.getFailMoney(), yesterdayXN.getBankFee()));
        //本月
        List<SystemDepositOrderVo> thisMonthList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, getThisMonth(), getXMonth(), incomeStatus, cardId, merchantId);
        SystemDepositOrderVo thisMonthDF = systemDepositOrderMapper.selectWithdrawal(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);
        SystemDepositOrderVo thisMonthXF = systemDepositOrderMapper.selectSettlement(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);
        SystemDepositOrderVo thisMonthXN = systemDepositOrderMapper.selectCrypto(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);

        dataVo.setThisMonthBankIncome(amountFee(thisMonthList.get(0).getFailMoney(), thisMonthList.get(0).getBankFee()));
        dataVo.setThisMonthQRIncome(amountFee(thisMonthList.get(1).getFailMoney(), thisMonthList.get(1).getBankFee()));
        dataVo.setThisMonthTWIncome(amountFee(thisMonthList.get(2).getFailMoney(), thisMonthList.get(2).getBankFee()));
        dataVo.setThisMonthBDBankIncome(amountFee(thisMonthList.get(3).getFailMoney(), thisMonthList.get(3).getBankFee()));
        dataVo.setThisMonthDFIncome(amountFee(thisMonthDF.getFailMoney(), thisMonthDF.getBankFee()));
        dataVo.setThisMonthXFIncome(amountFee(thisMonthXF.getFailMoney(), thisMonthXF.getBankFee()));
        dataVo.setThisMonthXNIncome(amountFee(thisMonthXN.getFailMoney(), thisMonthXN.getBankFee()));

        //上月
        List<SystemDepositOrderVo> lastMonthList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId, merchantId);
        SystemDepositOrderVo lastMonthDF = systemDepositOrderMapper.selectWithdrawal(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);
        SystemDepositOrderVo lastMonthXF = systemDepositOrderMapper.selectSettlement(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);
        SystemDepositOrderVo lastMonthXN = systemDepositOrderMapper.selectCrypto(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);

        dataVo.setLastMonthBankIncome(amountFee(lastMonthList.get(0).getFailMoney(), lastMonthList.get(0).getBankFee()));
        dataVo.setLastMonthQRIncome(amountFee(lastMonthList.get(1).getFailMoney(), lastMonthList.get(1).getBankFee()));
        dataVo.setLastMonthTWIncome(amountFee(lastMonthList.get(2).getFailMoney(), lastMonthList.get(2).getBankFee()));
        dataVo.setLastMonthBDBankIncome(amountFee(lastMonthList.get(3).getFailMoney(), lastMonthList.get(3).getBankFee()));
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
    public static final String getLastMonth() {
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
    public static final String getXMonth() {
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
        dataVo.setTodayMyCommission(todayCommission.get(0).getMoney().multiply(todayCommission.get(0).getBankFee()));
        dataVo.setTodayMyCommission(todayCommission.get(1).getMoney().multiply(todayCommission.get(1).getMerchantFee()));
        //结算
        SystemDepositOrderCommissionVo todayJS = systemDepositOrderMapper.selectSettlementCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayJSCommission(todayJS.getMoney().multiply(todayJS.getBankFee()));
        //内转
        SystemDepositOrderCommissionVo todayNZ = systemDepositOrderMapper.selectIntroversionCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayNZCommission(todayNZ.getMoney().multiply(todayNZ.getBankFee()));
        //外传
        SystemDepositOrderCommissionVo todayWZ = systemDepositOrderMapper.selectExternalTradeCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayWZCommission(todayWZ.getMoney().multiply(todayWZ.getBankFee()));
        //昨日
        List<SystemDepositOrderCommissionVo> yesterdayCommission =
                systemDepositOrderMapper.selectWithdrawalrCommission(currency, yesterday, today, cardId, merchantId);
        dataVo.setYesterdayMyCommission(yesterdayCommission.get(0).getMoney().multiply(yesterdayCommission.get(0).getBankFee()));
        dataVo.setYesterdayMyCommission(yesterdayCommission.get(1).getMoney().multiply(yesterdayCommission.get(1).getMerchantFee()));
        //结算
        SystemDepositOrderCommissionVo yesterdayJS = systemDepositOrderMapper.selectSettlementCommission(currency, yesterday, today, cardId, merchantId);
        dataVo.setYesterdayJSCommission(yesterdayJS.getMoney().multiply(yesterdayJS.getBankFee()));
        //内转
        SystemDepositOrderCommissionVo yesterdayNZ = systemDepositOrderMapper.selectIntroversionCommission(currency, yesterday, today, cardId, merchantId);
        dataVo.setYesterdayNZCommission(yesterdayNZ.getMoney().multiply(yesterdayNZ.getBankFee()));
        //外传
        SystemDepositOrderCommissionVo yesterdayWZ = systemDepositOrderMapper.selectExternalTradeCommission(currency, yesterday, today, cardId, merchantId);
        dataVo.setTodayWZCommission(yesterdayWZ.getMoney().multiply(yesterdayWZ.getBankFee()));
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
    private void merchant(Long cardGroupId, String currency) {
        dataVo.setTodayMerchantRegister(systemDepositOrderMapper.selectMerchantRegister(currency, today, tomorrow, cardGroupId));
        dataVo.setYesterdayMerchantRegister(systemDepositOrderMapper.selectMerchantRegister(currency, yesterday, today, cardGroupId));
        dataVo.setSuccessMerchantExamine(systemDepositOrderMapper.selectMerchantExamine(currency, "1", cardGroupId));
        dataVo.setFailMerchantExamine(systemDepositOrderMapper.selectMerchantExamine(currency, "0", cardGroupId));


    }

}

