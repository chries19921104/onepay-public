package org.example.admin.service.impl;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.BankCardCreateDto;
import org.example.admin.mapper.*;
import org.example.admin.service.SystemBankCardService;
import org.example.admin.vo.Statement;
import org.example.common.base.CommResp;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankCardDto;
import org.example.common.base.Totals;
import org.example.common.constant.BankCardConstant;
import org.example.common.entity.*;
import org.example.admin.vo.BankCardAllVo;
import org.example.admin.vo.BankCardVo;
import org.example.common.utils.BaseContext;
import org.example.common.utils.PageUtils;
import org.example.common.utils.URLUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * system_bank_card ExternalStatementService 接口实现
 * </p>
 *
 * @author zhangmi
 * @since 2023-05-19 16:22:04
 */
@Service
@Transactional
@Slf4j
public class SystemBankCardServiceImpl extends ServiceImpl<SystemBankCardMapper, SystemBankCard> implements SystemBankCardService {

    @Autowired
    private SystemBankCardMapper systemBankCardMapper;

    @Autowired
    private SystemBankMapper systemBankMapper;

    @Autowired
    private SystemServerListMapper systemServerListMapper;

    @Autowired
    private SystemBankCardGroupMapper systemBankCardGroupMapper;

    @Autowired
    private SystemBankCardBillMapper systemBankCardBillMapper;

    @Autowired
    private AdminsMapper adminsMapper;

    @Autowired
    private SystemDabTokenCardMapper systemDabTokenCardMapper;

    //有关银行卡账户号码选项列表查询接口
    @Override
    public List<BankCardVo> getBranks() {
        //先查询出所有的银行卡信息
        List<SystemBankCard> systemBankCards = systemBankCardMapper.selectList(null);
        //遍历将信息拷贝到bankcardvo中
        List<BankCardVo> collect = systemBankCards.stream().map(iter -> {
            BankCardVo bankCardVo = new BankCardVo();
            BeanUtils.copyProperties(iter, bankCardVo);
            //通过其中的bankId查询银行表，查出bandCode存入其中。
            SystemBank systemBank = systemBankMapper.selectById(iter.getBankId());
            if (systemBank != null) {
                bankCardVo.setBankCode(systemBank.getCode());
            }
            return bankCardVo;
        }).collect(Collectors.toList());
        return collect;
    }

    //查找不同type，status，curreny的账户
    @Override
    public Map<String, List<BankCardAllVo>> getBrankByType(BankCardDto bankCardDto) {
        //通过typr，assigned，currency查询BankCard表
        List<SystemBankCard> systemBankCards = systemBankCardMapper.selectList(new LambdaQueryWrapper<SystemBankCard>()
                .eq(SystemBankCard::getCurrency, bankCardDto.getCurrency())
                .eq(SystemBankCard::getType, bankCardDto.getType())
                .eq(SystemBankCard::getStatus, bankCardDto.getAssigned()));
        List<BankCardAllVo> bankCardAllVos = systemBankCards.stream().map(iter -> {
            BankCardAllVo bankCardAllVo = new BankCardAllVo();
            BeanUtils.copyProperties(iter, bankCardAllVo);
            return bankCardAllVo;
        }).collect(Collectors.toList());
        Map<String, List<BankCardAllVo>> map = new HashMap<>();
        map.put("data", bankCardAllVos);
        return map;
    }

    //银行账户管理-银行账户-查询
    @Override
    public MerchantResp getBrankAccountList(BankCardDto bankCardDto, HttpServletRequest request) {
        //data
        LambdaQueryWrapper<SystemBankCard> lqw = new LambdaQueryWrapper<>();
        if (bankCardDto.getCurrency() != null && !bankCardDto.getCurrency().isEmpty()) {
            lqw.eq(SystemBankCard::getCurrency, bankCardDto.getCurrency());
        }
        if (bankCardDto.getCardGroupIds() != null && bankCardDto.getCardGroupIds().size() != 0) {
            lqw.in(SystemBankCard::getCardGroupId, bankCardDto.getCardGroupIds());
        }
        if (bankCardDto.getBankId() != null) {
            lqw.eq(SystemBankCard::getBankId, bankCardDto.getBankId());
        }
        if (bankCardDto.getTypes() != null && bankCardDto.getTypes().size() != 0) {
            lqw.in(SystemBankCard::getType, bankCardDto.getTypes());
        }
        if (bankCardDto.getOpeningStatus() != null && bankCardDto.getOpeningStatus().size() != 0) {
            lqw.in(SystemBankCard::getOpeningStatus, bankCardDto.getOpeningStatus());
        }
        if (bankCardDto.getStatus() != null && bankCardDto.getStatus().size() != 0) {
            lqw.in(SystemBankCard::getStatus, bankCardDto.getStatus());
        }
        if (bankCardDto.getBankNumber() != null && !bankCardDto.getBankNumber().isEmpty()) {
            lqw.eq(SystemBankCard::getBankNumber, bankCardDto.getBankNumber());
        }
        if (bankCardDto.getAccountCode() != null && !bankCardDto.getAccountCode().isEmpty()) {
            lqw.like(SystemBankCard::getAccountCode, bankCardDto.getAccountCode());
        }
        if (bankCardDto.getAccount() != null && !bankCardDto.getAccount().isEmpty()) {
            lqw.like(SystemBankCard::getAccount, bankCardDto.getAccount());
        }
        if (bankCardDto.getMobileName() != null && !bankCardDto.getMobileName().isEmpty()) {
            lqw.like(SystemBankCard::getMobileName, bankCardDto.getMobileName());
        }
        if (bankCardDto.getCardId() != null && !bankCardDto.getCardId().isEmpty()) {
            lqw.eq(SystemBankCard::getCardId, bankCardDto.getCardId().get(0));
        }
        if (bankCardDto.getVndOtp() != null && bankCardDto.getVndOtp().size() != 0) {
            lqw.in(SystemBankCard::getVndOtp, bankCardDto.getVndOtp());
        }
        if (bankCardDto.getVndPaymentMethod() != null && bankCardDto.getVndPaymentMethod().size() != 0) {
            lqw.in(SystemBankCard::getVndPaymentMethod, bankCardDto.getVndPaymentMethod());
        }
//        Page<SystemBankCard> page = new Page<>(bankCardDto.getPage(),bankCardDto.getRp());
        List<SystemBankCard> systemBankCards = systemBankCardMapper.selectList(lqw);
        if (systemBankCards.size() == 0) {
            Totals totals = new Totals();
            getTotal(totals);
            return GetNoResp.getNoMerchantResp(request, bankCardDto.getRp(), totals);
        }
        List<SystemBankCard> pageRecords = PageUtils.getPageRecords(bankCardDto.getPage(), bankCardDto.getRp(), systemBankCards);

        //total & subtotal
        Totals subtotals = new Totals();
        getTotal(subtotals);

        Totals totals = new Totals();
        getTotal(totals);

        //遍历
        List<BankCardAllVo> bankCardAllVos = pageRecords.stream().map(iter -> {
            BankCardAllVo bankCardAllVo = new BankCardAllVo();
            //1.首先拷贝system_bank_card表中的数据
            BeanUtils.copyProperties(iter, bankCardAllVo);
            bankCardAllVo.setStep(iter.getStepOpencard());
            bankCardAllVo.setMaxBalance(iter.getDailyCollectionLimit());

            //2.通过iter中的server_id去查询system_server_list表中数据
            SystemServerList systemServerList = systemServerListMapper.selectById(iter.getServerId());
            if (systemServerList != null) {
                bankCardAllVo.setPreVPN100ID(null);
                bankCardAllVo.setVpnName(systemServerList.getName());
            }

            //3.通过iter中的cardGroupId去查询system_bank_card_group表中数据
            SystemBankCardGroup systemBankCardGroup = systemBankCardGroupMapper.selectById(iter.getCardGroupId());
            bankCardAllVo.setGroupName(systemBankCardGroup.getGroupName());

            //4.通过iter中的bankid去查询system_bank表中数据
            SystemBank systemBank = systemBankMapper.selectById(iter.getBankId());
            bankCardAllVo.setFullName(systemBank.getCode() + "-" + iter.getAccount() + "-" + iter.getBankNumber());
            bankCardAllVo.setBankName(systemBank.getName());

            //5.通过iter中的bankcardid去查询system_bank_card_bill表中数据
            SystemBankCardBill systemBankCardBill = systemBankCardBillMapper.selectOne(new LambdaQueryWrapper<SystemBankCardBill>()
                    .eq(SystemBankCardBill::getBankCardId, iter.getCardId()));
            bankCardAllVo.setEtHoldBalance(systemBankCardBill.getEtHoldBalance());
            bankCardAllVo.setFiHoldBalance(systemBankCardBill.getFiHoldBalance());
            bankCardAllVo.setFoHoldBalance(systemBankCardBill.getFoHoldBalance());
            bankCardAllVo.setFxHoldBalance(systemBankCardBill.getFxHoldBalance());
            bankCardAllVo.setTrInHoldBalance(systemBankCardBill.getTrInHoldBalance());
            bankCardAllVo.setTrOutHoldBalance(systemBankCardBill.getTrOutHoldBalance());

            //6.需要计算的数据
            BigDecimal d_i = iter.getDailyDisbursementLimit().subtract(systemBankCardBill.getDayFlowIn());
            BigDecimal d_o = iter.getDailyDisbursementLimit().subtract(systemBankCardBill.getDayFlowOut());
            BigDecimal m_i = iter.getMonthDisbursementLimit().subtract(systemBankCardBill.getMonthFlowIn());
            BigDecimal m_o = iter.getDailyDisbursementLimit().subtract(systemBankCardBill.getMonthFlowOut());
            bankCardAllVo.setDRemainingLimitI(d_i);
            bankCardAllVo.setDRemainingLimitO(d_o);
            bankCardAllVo.setMRemainingLimitI(m_i);
            bankCardAllVo.setDRemainingLimitO(m_o);

            bankCardAllVo.setDIWarning(iter.getDailyDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(d_i) >= 0);
            bankCardAllVo.setDOWarning(iter.getDailyDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(d_o) >= 0);
            bankCardAllVo.setMIWarning(iter.getMonthDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(m_i) >= 0);
            bankCardAllVo.setMOWarning(iter.getMonthDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(m_o) >= 0);

            long remainingNumberofTimes = iter.getMonthTreshold()
                    - systemBankCardBill.getMonthCurrentSuccessIn()
                    - systemBankCardBill.getMonthHoldSuccessIn()
                    - systemBankCardBill.getMonthCurrentSuccessOut()
                    - systemBankCardBill.getMonthHoldSuccessOut();
            bankCardAllVo.setRemainingNumberOfTimes(remainingNumberofTimes);

            bankCardAllVo.setTimesWarning(remainingNumberofTimes <= 5);
            bankCardAllVo.setIsPromptpayCode(iter.getPromptpayCode().isEmpty() ? 0 : 1);
            bankCardAllVo.setIsQrPayCode(iter.getQrPayCode().isEmpty() ? 0 : 1);
            bankCardAllVo.setLoss(iter.getStatus() == 5 ? systemBankCardBill.getCurrentBalance() : BigDecimal.valueOf(0.0));

            bankCardAllVo.setMaxBalanceRatio(systemBankCardBill.getCurrentBalance()
                    .divide(iter.getDailyDisbursementLimit(), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100)).intValue());


            BigDecimal remainingBalance = iter.getStatus() == 5 ? BigDecimal.valueOf(0.0) : systemBankCardBill.getCurrentBalance();
            bankCardAllVo.setRemainingBalance(remainingBalance);


            Statement statement = new Statement();
            if (iter.getStatement() != null && !iter.getStatement().isEmpty()) {
                JSONObject jsonObject = new JSONObject(iter.getStatement());
                statement.setStatus(jsonObject.getInt("status", null));
                statement.setCount(jsonObject.getInt("count", null));
                statement.setHistoryStatus(jsonObject.getInt("historStatus", null));
                statement.setIsErrurl(jsonObject.getInt("isErrurl", null));
                statement.setLCount(jsonObject.getInt("lCount", null));
                statement.setManualPlay(jsonObject.getInt("manualPlay", null));
                statement.setPCount(jsonObject.getInt("pCount", null));
                bankCardAllVo.setStatement(statement);
                bankCardAllVo.setMsgOpencard(jsonObject.getInt("msg_opencard", null));
                bankCardAllVo.setIsErrurlOpencard(jsonObject.getInt("is_errurl_opencard", 0));
            } else {
                bankCardAllVo.setStatement(statement);
                bankCardAllVo.setMsgOpencard(null);
                bankCardAllVo.setIsErrurlOpencard(0);
            }

            List<Integer> list = new ArrayList<>();
            list.add(BankCardConstant.TYPE_FO);
            list.add(BankCardConstant.TYPE_FX);
            bankCardAllVo.setBalanceLowerWarning(list.contains(iter.getType())
                    && remainingBalance.compareTo(iter.getDailyCollectionLimit().multiply(BigDecimal.valueOf(0.05))) <= 0);
            bankCardAllVo.setBalanceUpperWarning(iter.getType() == BankCardConstant.TYPE_FI
                    && remainingBalance.compareTo(iter.getDailyCollectionLimit().multiply(BigDecimal.valueOf(0.95))) >= 0);

            bankCardAllVo.setCheckAccount(d_i.compareTo(iter.getDailyDisbursementLimit().multiply(BigDecimal.valueOf(0.05))) <= 0 ||
                    d_o.compareTo(iter.getDailyDisbursementLimit().multiply(BigDecimal.valueOf(0.05))) <= 0 ||
                    m_i.compareTo(iter.getMonthDisbursementLimit().multiply(BigDecimal.valueOf(0.05))) <= 0 ||
                    m_o.compareTo(iter.getMonthDisbursementLimit().multiply(BigDecimal.valueOf(0.05))) <= 0 ||
                    remainingNumberofTimes <= 5 ||
                    bankCardAllVo.getBalanceLowerWarning() || bankCardAllVo.getBalanceUpperWarning());

            //计算subtotal
            subtotals.setEtHoldBalance(subtotals.getEtHoldBalance().add(systemBankCardBill.getEtHoldBalance()));
            subtotals.setFiHoldBalance(subtotals.getFiHoldBalance().add(systemBankCardBill.getFiHoldBalance()));
            subtotals.setFoHoldBalance(subtotals.getFoHoldBalance().add(systemBankCardBill.getFoHoldBalance()));
            subtotals.setFxHoldBalance(subtotals.getFxHoldBalance().add(systemBankCardBill.getFxHoldBalance()));
            subtotals.setTrInHoldBalance(subtotals.getTrInHoldBalance().add(systemBankCardBill.getTrInHoldBalance()));
            subtotals.setTrOutHoldBalance(subtotals.getTrOutHoldBalance().add(systemBankCardBill.getTrOutHoldBalance()));
            subtotals.setRemainingBalance(subtotals.getRemainingBalance().add(remainingBalance));
            if (iter.getType() == BankCardConstant.STATUS_PERM_FREEZE) {
                subtotals.setLoss(subtotals.getLoss().add(systemBankCardBill.getCurrentBalance()));
            }

            return bankCardAllVo;
        }).collect(Collectors.toList());

        if (systemBankCards.size() <= bankCardDto.getRp()) {
            totals = subtotals;
        } else {
            for (SystemBankCard systemBankCard : systemBankCards) {
                //通过iter中的bankcardid去查询system_bank_card_bill表中数据
                SystemBankCardBill systemBankCardBill = systemBankCardBillMapper.selectOne(new LambdaQueryWrapper<SystemBankCardBill>()
                        .eq(SystemBankCardBill::getBankCardId, systemBankCard.getCardId()));
                BigDecimal remainingBalance = systemBankCard.getStatus() == 5 ? BigDecimal.valueOf(0.0) : systemBankCardBill.getCurrentBalance();
                //计算total
                totals.setEtHoldBalance(totals.getEtHoldBalance().add(systemBankCardBill.getEtHoldBalance()));
                totals.setFiHoldBalance(totals.getFiHoldBalance().add(systemBankCardBill.getFiHoldBalance()));
                totals.setFoHoldBalance(totals.getFoHoldBalance().add(systemBankCardBill.getFoHoldBalance()));
                totals.setFxHoldBalance(totals.getFxHoldBalance().add(systemBankCardBill.getFxHoldBalance()));
                totals.setTrInHoldBalance(totals.getTrInHoldBalance().add(systemBankCardBill.getTrInHoldBalance()));
                totals.setTrOutHoldBalance(totals.getTrOutHoldBalance().add(systemBankCardBill.getTrOutHoldBalance()));
                totals.setRemainingBalance(totals.getRemainingBalance().add(remainingBalance));
                if (systemBankCard.getType() == BankCardConstant.STATUS_PERM_FREEZE) {
                    totals.setLoss(totals.getLoss().add(systemBankCardBill.getCurrentBalance()));
                }
            }
        }
        //返回结果集
        return getResp(request, bankCardDto, bankCardAllVos, totals, subtotals, systemBankCards);
    }

    //银行账户管理-银行账户-新增
    @Override
    public Map<String, BankCardAllVo> createBrankAccount(BankCardCreateDto bankCardDto) {
        //复制属性
        BankCardAllVo bankCardAllVo = new BankCardAllVo();
        SystemBankCard systemBankCard = new SystemBankCard();
        BeanUtils.copyProperties(bankCardDto, systemBankCard);
        //创建与更新
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);
        systemBankCard.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        systemBankCard.setCreator(admins.getUsername());
        systemBankCard.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        systemBankCard.setUpdater(admins.getUsername());
        systemBankCardMapper.insert(systemBankCard);
        BeanUtils.copyProperties(bankCardDto, bankCardAllVo);
        bankCardAllVo.setCardId(systemBankCard.getCardId());

        //新建账户流水表
        SystemBankCardBill systemBankCardBill = new SystemBankCardBill();
        systemBankCardBill.setCreatedAt(LocalDateTime.now());
        systemBankCardBill.setCreator(admins.getUsername());
        systemBankCardBill.setUpdatedAt(LocalDateTime.now());
        systemBankCardBill.setUpdater(admins.getUsername());
        systemBankCardBill.setBankCardId(systemBankCard.getCardId());
        systemBankCardBillMapper.insert(systemBankCardBill);

        bankCardAllVo.setBankCardBill(systemBankCardBillMapper.selectById(systemBankCardBill.getWalletId()));
        return CommResp.respData(bankCardAllVo);
    }

    //银行账户管理-银行账户-详情
    @Override
    public Map<String, BankCardAllVo> getBrankAccount(Long id) {
        //1、根据id查出bankcard表
        SystemBankCard systemBankCard = systemBankCardMapper.selectById(id);

        //2.拷贝属性
        BankCardAllVo bankCardAllVo = new BankCardAllVo();
        bankCardAllVo.setMaxBalance(systemBankCard.getDailyCollectionLimit());
        bankCardAllVo.setIdentity(systemBankCard.getWithdrawPin());
        bankCardAllVo.setYoutapPin(systemBankCard.getWithdrawPin());
        BeanUtils.copyProperties(systemBankCard, bankCardAllVo);

        //4.通过iter中的bankid去查询system_bank表中数据
        SystemBank systemBank = systemBankMapper.selectById(systemBankCard.getBankId());

        //3.通过iter中的bankcardid去查询system_bank_card_bill表中数据
        SystemBankCardBill systemBankCardBill = systemBankCardBillMapper.selectOne(new LambdaQueryWrapper<SystemBankCardBill>()
                .eq(SystemBankCardBill::getBankCardId, id));
        bankCardAllVo.setEtHoldBalance(systemBankCardBill.getEtHoldBalance());
        bankCardAllVo.setFiHoldBalance(systemBankCardBill.getFiHoldBalance());
        bankCardAllVo.setFoHoldBalance(systemBankCardBill.getFoHoldBalance());
        bankCardAllVo.setFxHoldBalance(systemBankCardBill.getFxHoldBalance());
        bankCardAllVo.setTrInHoldBalance(systemBankCardBill.getTrInHoldBalance());
        bankCardAllVo.setTrOutHoldBalance(systemBankCardBill.getTrOutHoldBalance());

        //4.通过iter中的server_id去查询system_server_list表中数据
        SystemServerList systemServerList = systemServerListMapper.selectById(systemBankCard.getServerId());
        if (systemServerList != null) {
            bankCardAllVo.setPreVPN100ID(null);
            bankCardAllVo.setVpnName(systemServerList.getName());
        }

        //5.需要计算的数据
        BigDecimal d_i = systemBankCard.getDailyDisbursementLimit().subtract(systemBankCardBill.getDayFlowIn());
        BigDecimal d_o = systemBankCard.getDailyDisbursementLimit().subtract(systemBankCardBill.getDayFlowOut());
        BigDecimal m_i = systemBankCard.getMonthDisbursementLimit().subtract(systemBankCardBill.getMonthFlowIn());
        BigDecimal m_o = systemBankCard.getDailyDisbursementLimit().subtract(systemBankCardBill.getMonthFlowOut());
        bankCardAllVo.setDRemainingLimitI(d_i);
        bankCardAllVo.setDRemainingLimitO(d_o);
        bankCardAllVo.setMRemainingLimitI(m_i);
        bankCardAllVo.setDRemainingLimitO(m_o);

        bankCardAllVo.setDIWarning(systemBankCard.getDailyDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(d_i) >= 0);
        bankCardAllVo.setDOWarning(systemBankCard.getDailyDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(d_o) >= 0);
        bankCardAllVo.setMIWarning(systemBankCard.getMonthDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(m_i) >= 0);
        bankCardAllVo.setMOWarning(systemBankCard.getMonthDisbursementLimit().multiply(BigDecimal.valueOf(0.05)).compareTo(m_o) >= 0);

        long remainingNumberofTimes = systemBankCard.getMonthTreshold()
                - systemBankCardBill.getMonthCurrentSuccessIn()
                - systemBankCardBill.getMonthHoldSuccessIn()
                - systemBankCardBill.getMonthCurrentSuccessOut()
                - systemBankCardBill.getMonthHoldSuccessOut();
        bankCardAllVo.setRemainingNumberOfTimes(remainingNumberofTimes);

        bankCardAllVo.setTimesWarning(remainingNumberofTimes <= 5);

        BigDecimal remainingBalance = systemBankCard.getStatus() == 5 ? BigDecimal.valueOf(0.0) : systemBankCardBill.getCurrentBalance();
        bankCardAllVo.setRemainingBalance(remainingBalance);

        Statement statement = new Statement();
        if (systemBankCard.getStatement() != null && !systemBankCard.getStatement().isEmpty()) {
            JSONObject jsonObject = new JSONObject(systemBankCard.getStatement());
            statement.setStatus(jsonObject.getInt("status", null));
            statement.setCount(jsonObject.getInt("count", null));
            statement.setHistoryStatus(jsonObject.getInt("historStatus", null));
            statement.setIsErrurl(jsonObject.getInt("isErrurl", null));
            statement.setLCount(jsonObject.getInt("lCount", null));
            statement.setManualPlay(jsonObject.getInt("manualPlay", null));
            statement.setPCount(jsonObject.getInt("pCount", null));
            bankCardAllVo.setStatement(statement);
        } else {
            bankCardAllVo.setStatement(statement);
        }

        bankCardAllVo.setDRemainingNumberOfTimes(systemBank.getDayTreshold() > 0 ? (int) (systemBank.getDayTreshold()
                - systemBankCardBill.getDayCurrentSuccessIn()
                - systemBankCardBill.getDayHoldSuccessIn() - systemBankCardBill.getDayCurrentSuccessOut()
                - systemBankCardBill.getDayHoldSuccessOut() - systemBankCardBill.getDayUnknownVbs()) : 0);

        List<Integer> list = new ArrayList<>();
        list.add(BankCardConstant.TYPE_FO);
        list.add(BankCardConstant.TYPE_FX);
        bankCardAllVo.setBalanceWarning(list.contains(systemBankCard.getType())
                && remainingBalance.compareTo(systemBankCard.getDailyCollectionLimit().multiply(BigDecimal.valueOf(0.05))) <= 0);
        bankCardAllVo.setBalanceUpperWarning(systemBankCard.getType() == BankCardConstant.TYPE_FI
                && remainingBalance.compareTo(systemBankCard.getDailyCollectionLimit().multiply(BigDecimal.valueOf(0.95))) >= 0);

        bankCardAllVo.setHoldTime(getHoldTime(systemBank.getCode(), systemBankCard.getHoldAt()));

        List<SystemDabTokenCard> systemDabTokenCards = systemDabTokenCardMapper.selectList(new LambdaQueryWrapper<SystemDabTokenCard>()
                .eq(SystemDabTokenCard::getBankCardId, systemBankCard.getCardId()));
        bankCardAllVo.setIsDabTokenCard(systemDabTokenCards.size() > 0);

        BigDecimal reservedBalance = systemBankCardBill.getFoHoldBalance().add(systemBankCardBill.getFxHoldBalance())
                .add(systemBankCardBill.getEtHoldBalance()).add(systemBankCardBill.getTrOutHoldBalance());
        bankCardAllVo.setRealBalance(systemBankCardBill.getCurrentBalance().subtract(reservedBalance));

        return CommResp.respData(bankCardAllVo);
    }

    //银行账户管理-银行账户-修改
    @Override
    public Map<String, Boolean> updateBrankAccount(BankCardAllVo bankCardAllVo) {
        //修改的数据包含两张表的数据，将不同的数据拷贝各自的表中
        SystemBankCard systemBankCard = new SystemBankCard();
        SystemBankCardBill systemBankCardBill = new SystemBankCardBill();
        BeanUtils.copyProperties(bankCardAllVo, systemBankCard);
        BeanUtils.copyProperties(bankCardAllVo, systemBankCardBill);
        systemBankCardMapper.updateById(systemBankCard);
        systemBankCardBillMapper.updateById(systemBankCardBill);
        return CommResp.success();
    }


    private MerchantResp getResp(HttpServletRequest request,
                                 BankCardDto bankCardDto,
                                 List<BankCardAllVo> bankCardAllVo,
                                 Totals totals,
                                 Totals subtotals,
                                 List<SystemBankCard> systemBankCards) {
        MerchantResp merchantResp = new MerchantResp();
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        int page = (bankCardAllVo.size() / bankCardDto.getRp()) + 1;
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + page);
        if (page > bankCardDto.getPage()) {
            merchantResp.setNext_page_url(url + "?page=" + (bankCardDto.getPage() + 1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) bankCardDto.getPage());
        merchantResp.setData(bankCardAllVo);
        if (systemBankCards.size() != 0) {
            merchantResp.setFrom((int) bankCardDto.getPage());
        }
        merchantResp.setLast_page(page);
        merchantResp.setPer_page(bankCardDto.getRp() + "");
        merchantResp.setPrev_page_url(null);
        if (systemBankCards.size() != 0) {
            merchantResp.setTo(bankCardAllVo.size());
        }
        merchantResp.setTotal(bankCardAllVo.size());
        merchantResp.setTotals(totals);
        merchantResp.setSubtotals(subtotals);
        return merchantResp;
    }

    private void getTotal(Totals t) {
        t.setEtHoldBalance(BigDecimal.valueOf(0));
        t.setFiHoldBalance(BigDecimal.valueOf(0));
        t.setFoHoldBalance(BigDecimal.valueOf(0));
        t.setFxHoldBalance(BigDecimal.valueOf(0));
        t.setTrOutHoldBalance(BigDecimal.valueOf(0));
        t.setTrInHoldBalance(BigDecimal.valueOf(0));
        t.setLoss(BigDecimal.valueOf(0));
        t.setRemainingBalance(BigDecimal.valueOf(0));
    }

    private String getHoldTime(String code, LocalDateTime holdAt) {
        Yaml yaml = new Yaml();
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/application.yml");
            Map<String, Object> config = yaml.load(inputStream);

            //1.得到hold_time_limit
            String s = "hold_time_limit";
            Integer holdTimeLimit = null;
            Integer i = (Integer) config.get(s + code);
            if (i != null) {
                holdTimeLimit = i;
            } else {
                holdTimeLimit = 0;
            }

            //2.得到hold_time
            Long holdTime = null;
            LocalDateTime localDateTime = holdAt.plusMinutes(holdTimeLimit);
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(localDateTime, now);
            holdTime = duration.getSeconds();

            //3.将hold_time经过计算得到分钟和秒数
            long minute = (holdTime % 86400 % 3600) / 60;
            long second = (holdTime % 86400 % 3600) % 60;

            //返回hold_time的数据
            if (minute < 1) {
                return "01:00";
            } else {
                return String.format("%02d:%02d", minute, second);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("getHoldTime方法错误", e);
        }
        return null;
    }
}