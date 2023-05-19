package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.*;
import org.example.admin.service.SystemMerchantService;
import org.example.common.base.MerchantData;
import org.example.common.dto.*;
import org.example.common.entity.*;
import org.example.common.utils.BaseContext;
import org.example.common.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    @Autowired
    private BaseMapper<MerchantData> MerchantDataMapper;

    @Autowired
    private SystemBankMapper systemBankMapper;

    @Autowired
    private SystemMerchantBankCardMapper systemMerchantBankCardMapper;

    @Autowired
    private SystemMerchantWhiteListMapper systemMerchantWhiteListMapper;

    @Autowired
    private AdminsMapper adminsMapper;

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
     * 商户条件查询返回data信息
     * @param merchantDto
     * @return
     */
    @Override
    public Page<MerchantData> selectData(MerchantDto merchantDto) {
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
            }
            //如果查询出来对应的代理信息那么就将其中部分字段拷贝到data结果类中
            if (systemAgent1 != null){
                merchantData.setAgentDisplayId(systemAgent1.getAgentId());
                merchantData.setAgentFullName(systemAgent1.getFullName());
            }
        }
        //分页处理
        Page<MerchantData> page = new Page<>(merchantDto.getPage(),merchantDto.getRp());
        page.setRecords(collect);
        Page<MerchantData> merchantDataPage = MerchantDataMapper.selectPage(page, new QueryWrapper<MerchantData>());
        return merchantDataPage;
    }

    /**
     * 银行信息
     * @param status
     * @return
     */
    @Override
    public List<BrankVo> getBranks(Integer status) {
        List<SystemBank> systemBanks = systemBankMapper.selectList(new LambdaQueryWrapper<SystemBank>()
                .eq(SystemBank::getStatus, status));
        //拷贝属性
        List<BrankVo> collect = systemBanks.stream().map(iter -> {
            BrankVo brankVo = new BrankVo();
            BeanUtils.copyProperties(systemBanks, brankVo);
            return brankVo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 新增商户信息
     * @param merchantBodyDto
     */
    @Override
    public Long saveMerchant(MerchantBodyDto merchantBodyDto) {
        SystemMerchant systemMerchant = new SystemMerchant();
        //将接收的信息部分存在systemMerchant中
        systemMerchant.setFiBankMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_bank_max())));
        systemMerchant.setFiBankMin(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_bank_min())));
        systemMerchant.setFiLocalbankMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_local_max())));
        systemMerchant.setFiLocalbankMin(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_local_min())));
        systemMerchant.setFiQrpayMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_qrpay_max())));
        systemMerchant.setFiQrpayMin(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_qrpay_min())));
        systemMerchant.setFiTrueMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_true_max())));
        systemMerchant.setFiTrueMin(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_True_min())));
        systemMerchant.setFoMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFO_max())));
        systemMerchant.setFoMin(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFO_min())));
        systemMerchant.setFxMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFX_max())));
        systemMerchant.setFxMin(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFX_min())));
        systemMerchant.setCardGroupId((long)merchantBodyDto.getCardGroupId());
        systemMerchant.setVnpayEnabled(merchantBodyDto.getVNPAY_enabled());
        systemMerchant.setAgentId((long)merchantBodyDto.getAgent_id());
        systemMerchant.setCheckAccname(("true".equals(merchantBodyDto.getCheck_accname()))?1:0);
        systemMerchant.setCode(merchantBodyDto.getCode());
        systemMerchant.setCurrency(merchantBodyDto.getCurrency());
        systemMerchant.setName(merchantBodyDto.getName());
        systemMerchant.setName4qr(merchantBodyDto.getName4qr());
        systemMerchant.setNoQrAdj(merchantBodyDto.getNo_qr_adj());
        systemMerchant.setNoQrAdjRandom(("true".equals(merchantBodyDto.getNo_qr_adj_random()))?1:0);
        systemMerchant.setPayFoBankFee(("true".equals(merchantBodyDto.getPay_fo_bank_fee()))?1:0);
        systemMerchant.setSecCode(merchantBodyDto.getSec_code());
        systemMerchant.setSettCardMax(Integer.valueOf(merchantBodyDto.getSett_card_max()));
        systemMerchant.setSettFee(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getSett_fee())));
        systemMerchant.setSettlementTerm(merchantBodyDto.getSettlement_term());
        systemMerchant.setTrQrRate(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getTr_qr_rate())));
        systemMerchant.setTrRate(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getTr_rate())));
        systemMerchant.setTrTrueRate(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getTr_true_rate())));
        systemMerchant.setWdRate(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getWd_rate())));

        //可能还需要补充一些数据
        systemMerchantMapper.insert(systemMerchant);
        return systemMerchant.getMerchantId();
    }

    /**
     * 银行账户条件查询返回data信息
     * @param merchant
     * @return
     */
    @Override
    public Page<SystemMerchantBankCard> selectBrandData(MerchantByBrandDto merchant) {
        Page<SystemMerchantBankCard> page = new Page<>(merchant.getPage(),merchant.getRp());
        //先查询system_merchant_bank_card表
        LambdaQueryWrapper<SystemMerchantBankCard> lqw = new LambdaQueryWrapper<>();
        if (merchant.getMerchantId()!= null && !merchant.getMerchantId().isEmpty()){
            lqw.in(SystemMerchantBankCard::getMerchantId,merchant.getMerchantId());
        }if (merchant.getStatus()!= null && !merchant.getStatus().isEmpty()){
            lqw.in(SystemMerchantBankCard::getStatus,merchant.getStatus());
        }if (merchant.getCardNumber() != null){
            lqw.eq(SystemMerchantBankCard::getCardNumber,merchant.getCardNumber());
        }if (merchant.getStartDate() != null && !merchant.getStartDate().isEmpty()) {
            lqw.between(SystemMerchantBankCard::getCreatedAt, Timestamp.valueOf(merchant.getStartDate()), Timestamp.valueOf(merchant.getEndDate()));
        }
        Page<SystemMerchantBankCard> iPage = systemMerchantBankCardMapper.selectPage(page, lqw);
        return iPage;
    }

    /**
     * 银行账户更新
     * @param merchant
     */
    @Override
    public void updateStatus(MerchantByBrandVo merchant) {
        SystemMerchantBankCard merchantBankCard = systemMerchantBankCardMapper.selectById(merchant.getMbId());
        if (merchantBankCard.getStatus() != merchant.getStatus()){
            merchantBankCard.setStatus(merchant.getStatus());
            systemMerchantBankCardMapper.updateById(merchantBankCard);
        }
    }

    /**
     * 选择商户代理
     * @return
     */
    @Override
    public List<MerchantByAgentByGroupVo> getMerchantByAgent() {
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(null);
        List<MerchantByAgentByGroupVo> collect = systemMerchants.stream().map(iter -> {
            MerchantByAgentByGroupVo merchantByAgentByGroupVo = new MerchantByAgentByGroupVo();
            BeanUtils.copyProperties(iter, merchantByAgentByGroupVo);
            merchantByAgentByGroupVo.setAgent_id(iter.getAgentId());
            return merchantByAgentByGroupVo;
        }).collect(Collectors.toList());
        return collect;
    }

    //商户资讯-白名单-查询接口
    @Override
    public Page<SystemMerchantWhiteList> getMerchantByWhite(MerchantByWhiteDto merchantByWhiteDto) {
        Page<SystemMerchantWhiteList> page = new Page<>(merchantByWhiteDto.getPage(),merchantByWhiteDto.getRp());
        //先查询system_merchant_white_list表
        LambdaQueryWrapper<SystemMerchantWhiteList> lqw = new LambdaQueryWrapper<>();
        if (merchantByWhiteDto.getMerchantId() != null && !merchantByWhiteDto.getMerchantId().isEmpty()){
            lqw.in(SystemMerchantWhiteList::getMerchantId,merchantByWhiteDto.getMerchantId());
        }if (merchantByWhiteDto.getType() != null && !merchantByWhiteDto.getType().isEmpty()){
            lqw.in(SystemMerchantWhiteList::getType,merchantByWhiteDto.getType());
        }if (merchantByWhiteDto.getIp() != null && !merchantByWhiteDto.getIp().isEmpty()){
            lqw.eq(SystemMerchantWhiteList::getIp,merchantByWhiteDto.getIp());
        }if (merchantByWhiteDto.getStartDate() != null && !merchantByWhiteDto.getStartDate().isEmpty()) {
            lqw.between(SystemMerchantWhiteList::getCreatedAt, Timestamp.valueOf(merchantByWhiteDto.getStartDate()), Timestamp.valueOf(merchantByWhiteDto.getEndDate()));
        }
        Page<SystemMerchantWhiteList> iPage = systemMerchantWhiteListMapper.selectPage(page, lqw);
        return iPage;
    }

    //商户资讯-白名单-新增接口
    @Override
    public SystemMerchantWhiteList saveWhite(WhiteBodyDto whiteBodyDto) {
        SystemMerchantWhiteList merchantWhiteList = new SystemMerchantWhiteList();
        merchantWhiteList.setMerchantId(whiteBodyDto.getMerchantId());
        merchantWhiteList.setIp(whiteBodyDto.getIp());
        merchantWhiteList.setStatus(whiteBodyDto.getStatus());
        merchantWhiteList.setType(Integer.valueOf(whiteBodyDto.getType()));
        //更新与创建
        Long current = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(current);
        merchantWhiteList.setCreatedAt(LocalDateTime.now());
        merchantWhiteList.setCreator(admins.getUsername());
        merchantWhiteList.setUpdatedAt(LocalDateTime.now());
        merchantWhiteList.setUpdater(admins.getUsername());

        systemMerchantWhiteListMapper.insert(merchantWhiteList);
        Long whiteId = merchantWhiteList.getWhiteId();
        merchantWhiteList.setWhiteId(whiteId);
        return merchantWhiteList;
    }

    /**
     * 白名单更新
     * @param merchantByWhiteVo
     */
    @Override
    public void updateWhite(MerchantByWhiteVo merchantByWhiteVo) {
        SystemMerchantWhiteList merchantWhiteList = systemMerchantWhiteListMapper.selectById(merchantByWhiteVo.getWhiteId());
        if (merchantWhiteList.getStatus() != merchantByWhiteVo.getStatus()){
            merchantWhiteList.setStatus(merchantByWhiteVo.getStatus());
        }
        systemMerchantWhiteListMapper.updateById(merchantWhiteList);
    }
}