package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.*;
import org.example.admin.service.SystemBankCardService;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankCardDto;
import org.example.common.entity.*;
import org.example.admin.vo.BankCardAllVo;
import org.example.admin.vo.BankCardVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>
* system_bank_card Service 接口实现
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
        Map<String,List<BankCardAllVo>> map = new HashMap<>();
        map.put("data",bankCardAllVos);
        return map;
    }

    @Override
    public MerchantResp getBrankAccount(BankCardDto bankCardDto) {
        //data
        LambdaQueryWrapper<SystemBankCard> lqw = new LambdaQueryWrapper<>();
        if (bankCardDto.getCurrency()!= null && !bankCardDto.getCurrency().isEmpty()){
            lqw.eq(SystemBankCard::getCurrency,bankCardDto.getCurrency());
        }if (bankCardDto.getCardGroupIds() != null && bankCardDto.getCardGroupIds().size() != 0){
            lqw.in(SystemBankCard::getCardGroupId,bankCardDto.getCardGroupIds());
        }if (bankCardDto.getBankId() != null){
            lqw.eq(SystemBankCard::getBankId,bankCardDto.getBankId());
        }if (bankCardDto.getTypes() != null && bankCardDto.getTypes().size() != 0){
            lqw.in(SystemBankCard::getType,bankCardDto.getTypes());
        }if (bankCardDto.getOpeningStatus() != null && bankCardDto.getOpeningStatus().size() != 0){
            lqw.in(SystemBankCard::getOpeningStatus,bankCardDto.getOpeningStatus());
        }if (bankCardDto.getStatus() != null && bankCardDto.getStatus().size() != 0){
            lqw.in(SystemBankCard::getStatus,bankCardDto.getStatus());
        }if (bankCardDto.getBankNumber() != null && !bankCardDto.getBankNumber().isEmpty()){
            lqw.eq(SystemBankCard::getBankNumber,bankCardDto.getBankNumber());
        }if (bankCardDto.getAccountCode() != null && !bankCardDto.getAccountCode().isEmpty()){
            lqw.like(SystemBankCard::getAccountCode,bankCardDto.getAccountCode());
        }if (bankCardDto.getAccount() != null && !bankCardDto.getAccount().isEmpty()){
            lqw.like(SystemBankCard::getAccount,bankCardDto.getAccount());
        }if (bankCardDto.getMobileName() != null && !bankCardDto.getMobileName().isEmpty()){
            lqw.like(SystemBankCard::getMobileName,bankCardDto.getMobileName());
        }if (bankCardDto.getCardId() != null && !bankCardDto.getCardId().isEmpty()){
            lqw.eq(SystemBankCard::getCardId,bankCardDto.getCardId().get(0));
        }if (bankCardDto.getVndOtp()!= null && bankCardDto.getVndOtp().size() != 0){
            lqw.in(SystemBankCard::getVndOtp,bankCardDto.getVndOtp());
        }if (bankCardDto.getVndPaymentMethod()!= null && bankCardDto.getVndPaymentMethod().size() != 0){
            lqw.in(SystemBankCard::getVndPaymentMethod,bankCardDto.getVndPaymentMethod());
        }
        Page<SystemBankCard> page = new Page<>(bankCardDto.getPage(),bankCardDto.getRp());
        Page<SystemBankCard> bankCardPage = systemBankCardMapper.selectPage(page, lqw);

        bankCardPage.getRecords().stream().map(iter -> {
            BankCardAllVo bankCardAllVo = new BankCardAllVo();
            //1.首先拷贝system_bank_card表中的数据
            BeanUtils.copyProperties(iter,bankCardAllVo);
            bankCardAllVo.setStep(iter.getStepOpencard());
            bankCardAllVo.setMaxBalance(iter.getDailyDisbursementLimit());

            //2.通过iter中的server_id去查询system_server_list表中数据
            SystemServerList systemServerList = systemServerListMapper.selectById(iter.getServerId());
            bankCardAllVo.setPreVPN100ID(null);
            bankCardAllVo.setVpnName(systemServerList.getName());

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

            long remaining_Number_of_Times = iter.getMonthTreshold()
                    - systemBankCardBill.getMonthCurrentSuccessIn()
                    - systemBankCardBill.getMonthHoldSuccessIn()
                    - systemBankCardBill.getMonthCurrentSuccessOut()
                    - systemBankCardBill.getMonthHoldSuccessOut();
            bankCardAllVo.setRemainingNumberOfTimes(remaining_Number_of_Times);

            bankCardAllVo.setTimesWarning(remaining_Number_of_Times <= 5);
            bankCardAllVo.setIsPromptpayCode(iter.getPromptpayCode().isEmpty() ? 0 : 1);
            bankCardAllVo.setIsQrPayCode(iter.getQrPayCode().isEmpty() ? 0 : 1);
            bankCardAllVo.setLoss(iter.getStatus() == 5 ? systemBankCardBill.getCurrentBalance() : BigDecimal.valueOf(0.0));


            return null;
        });

        return null;
    }
}