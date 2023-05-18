package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.*;
import org.example.admin.service.SystemMerchantService;
import org.example.common.base.MerchantData;
import org.example.common.base.Totals;
import org.example.common.dto.MerchantDto;
import org.example.common.entity.*;
import org.example.common.vo.AgentsByCardGroupVo;
import org.example.common.vo.AgentsVo;
import org.example.common.vo.MerchantByAgentByGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.peer.CanvasPeer;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* system_merchant Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-05-16 14:57:24
*/
@Service
@Transactional
@Slf4j
public class SystemMerchantServiceImpl extends ServiceImpl<SystemMerchantMapper, SystemMerchant> implements SystemMerchantService {

    @Autowired
    private SystemMerchantMapper systemMerchantMapper;

    @Autowired
    private SystemBankCardGroupMapper systemBankCardGroupMapper;

    @Autowired
    private SystemAgentsMapper systemAgentsMapper;

    @Autowired
    private SystemMerchantWalletMapper systemMerchantWalletMapper;

    @Autowired
    private SystemMerchantSupportBankMapper systemMerchantSupportBankMapper;

    /**
     * 选择账户群组
     * @return
     */
    @Override
    public List<AgentsByCardGroupVo> getGroup() {
        List<SystemBankCardGroup> systemBankCardGroups = systemBankCardGroupMapper.selectList(null);
        List<AgentsByCardGroupVo> collect = systemBankCardGroups.stream().map(iter -> {
            AgentsByCardGroupVo agentsByCardGroupVo = new AgentsByCardGroupVo();
            BeanUtils.copyProperties(iter, agentsByCardGroupVo);
            return agentsByCardGroupVo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 选择商户
     * @return
     */
    @Override
    public List<MerchantByAgentByGroupVo> getMerchant() {
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(null);
        List<MerchantByAgentByGroupVo> collect = systemMerchants.stream().map(iter -> {
            MerchantByAgentByGroupVo merchantByAgentByGroupVo = new MerchantByAgentByGroupVo();
            BeanUtils.copyProperties(iter, merchantByAgentByGroupVo);
            return merchantByAgentByGroupVo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 选择代理
     * @return
     */
    @Override
    public List<AgentsVo> getAgents() {
        List<SystemAgents> systemAgents = systemAgentsMapper.selectList(null);
        List<AgentsVo> collect = systemAgents.stream().map(iter -> {
            AgentsVo agentsVo = new AgentsVo();
            BeanUtils.copyProperties(iter, agentsVo);
            return agentsVo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 商户条件查询返回data
     * @param merchantDto
     * @return
     */
    @Override
    public List<MerchantData> selectData(MerchantDto merchantDto) {
        //通过条件查询商户表
        LambdaQueryWrapper<SystemMerchant> lqw = new LambdaQueryWrapper<>();
        if (merchantDto.getMerchantId()!= null && !merchantDto.getMerchantId().isEmpty()){
            lqw.in(SystemMerchant::getMerchantId,merchantDto.getMerchantId());
        }if (merchantDto.getAgentId()!= null && !merchantDto.getAgentId().isEmpty()){
            lqw.in(SystemMerchant::getAgentId,merchantDto.getAgentId());
        }if (merchantDto.getCurrency() != null && !merchantDto.getCurrency().isEmpty()){
            lqw.eq(SystemMerchant::getCurrency,merchantDto.getCurrency());
        }if (merchantDto.getStartDate() != null && !merchantDto.getStartDate().isEmpty()){
            lqw.between(SystemMerchant::getCreatedAt, Timestamp.valueOf(merchantDto.getStartDate()),Timestamp.valueOf(merchantDto.getEndDate()));
        }if (merchantDto.getStatus() != null && !merchantDto.getStatus().isEmpty()){
            lqw.in(SystemMerchant::getStatus,merchantDto.getStatus());
        }if (merchantDto.getGroupId() != null){
            lqw.eq(SystemMerchant::getCardGroupId,merchantDto.getGroupId());
        }
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(lqw);

        //将结果的部分字段信息拷贝到data结果类中
        List<MerchantData> collect = systemMerchants.stream().map(iter -> {
            MerchantData merchantData = new MerchantData();
            BeanUtils.copyProperties(iter, merchantData);
            merchantData.setAgentDisplayId(iter.getAgentId());
            return merchantData;
        }).collect(Collectors.toList());

        //银行条件筛选
        if (merchantDto.getNotAllowedTypes() != null && !merchantDto.getNotAllowedTypes().isEmpty()){
            for (MerchantData merchantData : collect) {
                LambdaQueryWrapper<SystemMerchantSupportBank> branklqw = new LambdaQueryWrapper<>();
                branklqw.eq(SystemMerchantSupportBank::getMerchantId,merchantData.getMerchantId())
                        .in(SystemMerchantSupportBank::getTxnType,merchantDto.getNotAllowedTypes());
                if (merchantDto.getNotAllowedBk100Id() != null && !merchantDto.getNotAllowedBk100Id().isEmpty())
                    branklqw.in(SystemMerchantSupportBank::getBankId,merchantDto.getNotAllowedBk100Id());
                List<SystemMerchantSupportBank> bankList = systemMerchantSupportBankMapper.selectList(branklqw);
                //如果查询存在，则不符合条件，删除当前对象
                if (bankList != null && !bankList.isEmpty()){
                    collect.remove(merchantData);
                }
            }
        }

        Totals totals = new Totals();
        BigDecimal availableBalance = null;
        BigDecimal depositOutstandingBalance= null;
        BigDecimal currentBalance= null;
        BigDecimal holdBalance= null;
        BigDecimal frozenBalance= null;
        BigDecimal todayTrFee= null;
        //遍历data结果集合
        for (MerchantData merchantData : collect) {
            //通过data类中的商户id查询对应的商户钱包信息
            SystemMerchantWallet systemMerchantWallet = systemMerchantWalletMapper.selectOne(
                    new LambdaQueryWrapper<SystemMerchantWallet>()
                    .eq(SystemMerchantWallet::getMerchantId, merchantData.getMerchantId()));
            //通过data中的代理id查询代理表的相关信息
            SystemAgents systemAgent1 = systemAgentsMapper.selectOne(
                    new LambdaQueryWrapper<SystemAgents>()
                    .eq(SystemAgents::getAgentId, merchantData.getAgentDisplayId()));
            //如果查询出来的代理信息包含父代理，那么通过父代理id查询代理信息
            if (systemAgent1 != null && systemAgent1.getBelongId() != null){
                SystemAgents systemAgent2 = systemAgentsMapper.selectOne(
                        new LambdaQueryWrapper<SystemAgents>()
                                .eq(SystemAgents::getAgentId, systemAgent1.getBelongId()));
                //将父代理信息存在data结果类中
                merchantData.setTopAgentId(systemAgent2.getAgentId());
                merchantData.setTopAgentName(systemAgent2.getFullName());
            }
            //如果查询出来对应的钱包信息那么就将其中部分字段拷贝到data结果类中
            if (systemMerchantWallet != null){
                BeanUtils.copyProperties(systemMerchantWallet,merchantData,
                        "availableBalance",
                        "depositOutstandingBalance",
                        "currentBalance",
                        "holdBalance",
                        "frozenBalance",
                        "todayTrFee");
                availableBalance = availableBalance.add(systemMerchantWallet.getAvailableBalance());
                depositOutstandingBalance = depositOutstandingBalance.add(systemMerchantWallet.getDepositOutstandingBalance());
                currentBalance = currentBalance.add(systemMerchantWallet.getCurrentBalance());
                holdBalance = holdBalance.add(systemMerchantWallet.getHoldBalance());
                frozenBalance = frozenBalance.add(systemMerchantWallet.getFrozenBalance());
                todayTrFee = todayTrFee.add(systemMerchantWallet.getTodayTrFee());
            }
            //如果查询出来对应的代理信息那么就将其中部分字段拷贝到data结果类中
            if (systemAgent1 != null){
                merchantData.setAgentDisplayId(systemAgent1.getAgentId());
                merchantData.setAgentFullName(systemAgent1.getFullName());
            }
        }
        //将totals数据存储
        totals.setCurrentBalance(currentBalance);
        totals.setAvailableBalance(availableBalance);
        totals.setFrozenBalance(frozenBalance);
        totals.setHoldBalance(holdBalance);
        totals.setTodayTrFee(todayTrFee);
        totals.setDepositOutstandingBalance(depositOutstandingBalance);



        return collect;
    }
}