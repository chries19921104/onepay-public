package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.*;
import org.example.admin.service.SystemMerchantService;
import org.example.admin.vo.MerchantDataVo;
import org.example.admin.dto.*;
import org.example.common.entity.*;
import org.example.common.utils.BaseContext;
import org.example.common.utils.RandomStringGenerator;
import org.example.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
* <p>
* system_merchant ExternalStatementService 接口实现
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
    private BaseMapper<MerchantDataVo> MerchantDataMapper;

    @Autowired
    private SystemBankMapper systemBankMapper;

    @Autowired
    private SystemMerchantBankCardMapper systemMerchantBankCardMapper;

    @Autowired
    private SystemMerchantWhiteListMapper systemMerchantWhiteListMapper;

    @Autowired
    private AdminsMapper adminsMapper;

    @Autowired
    private SystemMerchantAdminMapper systemMerchantAdminMapper;

    @Autowired
    private SystemMerchantOperateLogMapper systemMerchantOperateLogMapper;

    /**
     * 选择商户
     * @return
     */
    @Override
    public List<MerchantByAgentByGroupVo> getMerchant() {
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(new LambdaQueryWrapper<SystemMerchant>()
                .eq(SystemMerchant::getStatus, 1));
        List<MerchantByAgentByGroupVo> collect = systemMerchants.stream().map(iter -> {
            MerchantByAgentByGroupVo merchantByAgentByGroupVo = new MerchantByAgentByGroupVo();
            BeanUtils.copyProperties(iter, merchantByAgentByGroupVo);
            return merchantByAgentByGroupVo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 商户条件查询返回data信息
     * @param merchantDto
     * @return
     */
    @Override
    public Page<MerchantDataVo> selectData(MerchantDto merchantDto) {
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

        if (systemMerchants.size()== 0){
            return null;
        }
        //将结果的部分字段信息拷贝到data结果类中
        List<MerchantDataVo> collect = systemMerchants.stream().map(iter -> {
            MerchantDataVo merchantData = new MerchantDataVo();
            BeanUtils.copyProperties(iter, merchantData);
            merchantData.setAgentDisplayId(iter.getAgentId());
            return merchantData;
        }).collect(Collectors.toList());

        //银行条件筛选
        if (merchantDto.getNotAllowedTypes() != null && !merchantDto.getNotAllowedTypes().isEmpty()){
            for (MerchantDataVo merchantData : collect) {
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
        for (MerchantDataVo merchantData : collect) {
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
        Page<MerchantDataVo> page = new Page<>(merchantDto.getPage(),merchantDto.getRp(),collect.size());
        page.setRecords(collect);
        return page;
    }

    /**
     * 新增商户信息
     * @param merchantBodyDto
     */
    @Override
    public SystemMerchant saveMerchant(MerchantBodyDto merchantBodyDto) {
        SystemMerchant systemMerchant = new SystemMerchant();
        //1.将接收的信息部分存在systemMerchant中
//        systemMerchant.setFiBankMax(BigDecimal.valueOf(Double.parseDouble(merchantBodyDto.getFI_bank_max())));
        systemMerchant.setFiBankMax(merchantBodyDto.getFI_bank_max());
        systemMerchant.setFiBankMin(merchantBodyDto.getFI_bank_min());
        systemMerchant.setFiLocalbankMax(merchantBodyDto.getFI_local_max());
        systemMerchant.setFiLocalbankMin(merchantBodyDto.getFI_local_min());
        systemMerchant.setFiQrpayMax(merchantBodyDto.getFI_qrpay_max());
        systemMerchant.setFiQrpayMin(merchantBodyDto.getFI_qrpay_min());
        systemMerchant.setFiTrueMax(merchantBodyDto.getFI_true_max());
        systemMerchant.setFiTrueMin(merchantBodyDto.getFI_true_min());
        systemMerchant.setFoMax(merchantBodyDto.getFO_max());
        systemMerchant.setFoMin(merchantBodyDto.getFO_min());
        systemMerchant.setFxMax(merchantBodyDto.getFX_max());
        systemMerchant.setFxMin(merchantBodyDto.getFX_min());
        systemMerchant.setCardGroupId(merchantBodyDto.getCardGroupId());
        systemMerchant.setVnpayEnabled(merchantBodyDto.getVNPAY_enabled());
        systemMerchant.setAgentId(merchantBodyDto.getAgent_id());
        systemMerchant.setCheckAccname((merchantBodyDto.getCheck_accname())?1:0);
        systemMerchant.setCode(merchantBodyDto.getCode());
        systemMerchant.setCurrency(merchantBodyDto.getCurrency());
        systemMerchant.setName(merchantBodyDto.getName());
        systemMerchant.setName4qr(merchantBodyDto.getName4qr());
        systemMerchant.setNoQrAdj(merchantBodyDto.getNo_qr_adj());
        systemMerchant.setNoQrAdjRandom((merchantBodyDto.getNo_qr_adj_random())?1:0);
        systemMerchant.setPayFoBankFee((merchantBodyDto.getPay_fo_bank_fee())?1:0);
        systemMerchant.setSettCardMax(merchantBodyDto.getSett_card_max());
        systemMerchant.setSettFee(merchantBodyDto.getSett_fee());
        systemMerchant.setSettlementTerm(merchantBodyDto.getSettlement_term());
        systemMerchant.setTrQrRate(merchantBodyDto.getTr_qr_rate());
        systemMerchant.setTrRate(merchantBodyDto.getTr_rate());
        systemMerchant.setTrTrueRate(merchantBodyDto.getTr_true_rate());
        systemMerchant.setWdRate(merchantBodyDto.getWd_rate());
        //安全码
        String sceCode = getSceCode(16);
        systemMerchant.setSecCode(sceCode);
        //更新与创建
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);
        systemMerchant.setCreatedAt(LocalDateTime.now());
        systemMerchant.setCreatedMan(admins.getUsername());
        systemMerchant.setUpdatedAt(LocalDateTime.now());
        systemMerchant.setUpdatedMan(admins.getUsername());
        //可能还需要补充一些数据
        systemMerchantMapper.insert(systemMerchant);
        //2.将商户开放银行信息进行添加
        List<BrankDto> openBank = merchantBodyDto.getOpen_bank();
        for (BrankDto brankDto : openBank) {
            //补充信息
            SystemMerchantSupportBank merchantSupportBank = new SystemMerchantSupportBank();
            BeanUtils.copyProperties(brankDto,merchantSupportBank);
            merchantSupportBank.setBankId(brankDto.getBankId());
            merchantSupportBank.setMerchantId(systemMerchant.getMerchantId());
            //更新与创建
            merchantSupportBank.setCreatedAt(LocalDateTime.now());
            merchantSupportBank.setCreator(admins.getUsername());
            merchantSupportBank.setUpdatedAt(LocalDateTime.now());
            merchantSupportBank.setUpdater(admins.getUsername());
            //新增
            systemMerchantSupportBankMapper.insert(merchantSupportBank);
        }
        //3.新增管理员账号，先查询填的管理员账号是否存在，如果不存在则添加
        LambdaQueryWrapper<SystemMerchantAdmin> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemMerchantAdmin::getUsername,merchantBodyDto.getUsername());
        SystemMerchantAdmin merchantAdmin = systemMerchantAdminMapper.selectOne(lqw);
        if (merchantAdmin == null){
            SystemMerchantAdmin merchantAdmin1 = new SystemMerchantAdmin();
            merchantAdmin1.setMerchantId(systemMerchant.getMerchantId());
            merchantAdmin1.setUsername(merchantBodyDto.getUsername());
            merchantAdmin1.setPassword(RandomStringGenerator.generateRandomString());
            merchantAdmin1.setFullName(merchantBodyDto.getUsername());
            merchantAdmin1.setStatus(1);
            merchantAdmin1.setSuperAdmin(0);
            //更新与创建
            merchantAdmin1.setCreatedAt(LocalDateTime.now());
            merchantAdmin1.setCreator(admins.getUsername());
            merchantAdmin1.setUpdatedAt(LocalDateTime.now());
            merchantAdmin1.setUpdater(admins.getUsername());
            systemMerchantAdminMapper.insert(merchantAdmin1);
        }

        return systemMerchant;
    }

    /**
     * 银行账户条件查询返回data信息
     * @param merchant
     * @return
     */
    @Override
    public Page<SystemMerchantBankCard> selectBrandData(MerchantByBrandDto merchant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss");
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
            LocalDateTime startDate = LocalDateTime.parse(merchant.getStartDate(), formatter);
            LocalDateTime endtDate = LocalDateTime.parse(merchant.getEndDate(), formatter);
            lqw.between(SystemMerchantBankCard::getCreatedAt, startDate,endtDate);
        }
        return systemMerchantBankCardMapper.selectPage(page, lqw);
    }

    /**
     * 银行账户更新
     * @param merchant
     */
    @Override
    public void updateStatus(MerchantByBrandVo merchant) {
        SystemMerchantBankCard merchantBankCard = systemMerchantBankCardMapper.selectById(merchant.getMbId());
        if (!merchant.getStatus().equals(merchantBankCard.getStatus())){
            merchantBankCard.setStatus(merchant.getStatus());
            systemMerchantBankCardMapper.updateById(merchantBankCard);
        }
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
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);
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
        if (!merchantByWhiteVo.getStatus().equals(merchantWhiteList.getStatus())){
            merchantWhiteList.setStatus(merchantByWhiteVo.getStatus());
        }
        systemMerchantWhiteListMapper.updateById(merchantWhiteList);
    }

    //商户列表-详情-商户资讯-重置密码接口
    @Override
    public String rechargePassword(Long id) {
        //随机生成8位密码，包含数字（0-9）和大小写英文字母
        String s = RandomStringGenerator.generateRandomString();
        //修改密码
        SystemMerchantAdmin systemMerchantAdmin = systemMerchantAdminMapper.selectById(id);
        if (systemMerchantAdmin != null){
            systemMerchantAdmin.setPassword(s);
            systemMerchantAdminMapper.updateById(systemMerchantAdmin);
        }
        return s;
    }

    //商户列表-详情-商户资讯-重置2FA接口
    @Override
    public void recharge2FA(Long id) {
        SystemMerchantAdmin systemMerchantAdmin = systemMerchantAdminMapper.selectById(id);
        if (systemMerchantAdmin != null){
            systemMerchantAdmin.setTotpSecret("");
            systemMerchantAdminMapper.updateById(systemMerchantAdmin);
        }
    }

    //商户列表-详情-Log-搜索接口
    @Override
    public Page<SystemMerchantOperateLog> getMerchantByLog(MerchantByLogDto merchantByLogDto) {
        Page<SystemMerchantOperateLog> page = new Page<>(merchantByLogDto.getPage(),merchantByLogDto.getRp());
        LambdaQueryWrapper<SystemMerchantOperateLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemMerchantOperateLog::getMerchantId,merchantByLogDto.getMerchantId());
        if (merchantByLogDto.getAdmin_name() != null && !merchantByLogDto.getAdmin_name().isEmpty()){
            lqw.eq(SystemMerchantOperateLog::getOperator,merchantByLogDto.getAdmin_name());
        }if (merchantByLogDto.getType() != null && !merchantByLogDto.getType().isEmpty()){
            lqw.like(SystemMerchantOperateLog::getContent,merchantByLogDto.getType());
        }if (merchantByLogDto.getStartDate() != null && !merchantByLogDto.getStartDate().isEmpty()) {
            lqw.between(SystemMerchantOperateLog::getCreatedAt, Timestamp.valueOf(merchantByLogDto.getStartDate()), Timestamp.valueOf(merchantByLogDto.getEndDate()));
        }
        Page<SystemMerchantOperateLog> operateLogPage = systemMerchantOperateLogMapper.selectPage(page, lqw);
        return operateLogPage;
    }

    //商户资讯-商户列表-单个详情
    @Override
    public MerchantVo selectMerchantById(Long id) {
        MerchantVo merchantVo = new MerchantVo();
        //1.system_merchant   通过id查询商户信息
        SystemMerchant systemMerchant = systemMerchantMapper.selectById(id);
        //将其中部分信息拷贝vo中
        BeanUtils.copyProperties(systemMerchant,merchantVo);
        //2.system_agents     通过systemMerchant中的代理id查询代理表的相关信息
        SystemAgents systemAgent1 = systemAgentsMapper.selectOne(
                new LambdaQueryWrapper<SystemAgents>()
                        .eq(SystemAgents::getAgentId, systemMerchant.getAgentId()));
        if (systemAgent1 != null){
            merchantVo.setAgentDisplayId(systemAgent1.getAgentId());
            merchantVo.setAgentFullName(systemAgent1.getFullName());
        }
        //如果查询出来的代理信息包含父代理，那么通过父代理id查询代理信息
        if (systemAgent1 != null && systemAgent1.getBelongId() != null){
            SystemAgents systemAgent2 = systemAgentsMapper.selectOne(
                    new LambdaQueryWrapper<SystemAgents>()
                            .eq(SystemAgents::getAgentId, systemAgent1.getBelongId()));
            //将父代理信息存在vo结果类中
            if (systemAgent2 != null){
                merchantVo.setTopAgentId(systemAgent2.getAgentId());
                merchantVo.setTopAgentName(systemAgent2.getFullName());
            }
        }
        //3.system_merchant_wallet   通过id查询出钱包信息
        SystemMerchantWallet merchantWallet = systemMerchantWalletMapper.selectOne(new LambdaQueryWrapper<SystemMerchantWallet>()
                .eq(SystemMerchantWallet::getMerchantId, id));
        if (merchantWallet != null){
            merchantVo.setAvailableBalance(merchantWallet.getAvailableBalance());
            merchantVo.setCurrentBalance(merchantWallet.getCurrentBalance());
            merchantVo.setDepositOutstandingBalance(merchantWallet.getDepositOutstandingBalance());
        }
        //4.system_merchant_admin
        SystemMerchantAdmin merchantAdmin = systemMerchantAdminMapper.selectOne(new LambdaQueryWrapper<SystemMerchantAdmin>()
                .eq(SystemMerchantAdmin::getMerchantId, id));
        if (merchantAdmin != null){
            merchantVo.setUserId(merchantAdmin.getId());
            merchantVo.setUsername(merchantAdmin.getUsername());
        }
        //5.system_merchant_support_bank
        List<SystemMerchantSupportBank> supportBankList = systemMerchantSupportBankMapper.selectList(
                new LambdaQueryWrapper<SystemMerchantSupportBank>()
                        .eq(SystemMerchantSupportBank::getMerchantId, id));
        if (supportBankList.size()>0){
            List<BrankDto> collect = supportBankList.stream().map(iter -> {
                BrankDto brankDto = new BrankDto();
                BeanUtils.copyProperties(iter, brankDto);
                return brankDto;
            }).collect(Collectors.toList());
            merchantVo.setOpenBank(collect);
        }
        //6.补充其他属性
        merchantVo.setDapayServicesEnable(new ArrayList<>());
        merchantVo.setFiTpiDriver(systemMerchant.getFoTpiDriver());
        merchantVo.setFiTpiServicesEnable(new ArrayList<>());
        merchantVo.setFoTpiServicesEnable(new ArrayList<>());
        merchantVo.setFxTpiServicesEnable(new ArrayList<>());
        merchantVo.setVnpayServicesEnable(new ArrayList<>());
        merchantVo.setPresetSettlementTerm(null);
        merchantVo.setTunaDomains(new ArrayList<>());
        merchantVo.setApiEnDoc(null);
        merchantVo.setApiCnDoc(null);
        return merchantVo;
    }

    //商户资讯-商户列表-单个详情编辑
    @Override
    public void updateMerchant(MerchantDto1 merchantDto1) {
        //将整个结果集分别进行对商户信息表和商户银行表的拷贝，将属于自己的那部分拷贝过去。再进行修改。
        SystemMerchant merchant = new SystemMerchant();
        SystemMerchantSupportBank merchantSupportBank = new SystemMerchantSupportBank();
        //更新
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);

        //修改商户资料表
        BeanUtils.copyProperties(merchantDto1,merchant);
        merchant.setUpdatedAt(LocalDateTime.now());
        merchant.setUpdatedMan(admins.getUsername());
        systemMerchantMapper.updateById(merchant);

        //修改商户开放银行表
        LambdaQueryWrapper<SystemMerchantSupportBank> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemMerchantSupportBank::getMerchantId,merchantDto1.getMerchantId());

        for (BrankDto brankDto : merchantDto1.getOpenBank()) {
            lqw.eq(SystemMerchantSupportBank::getBankId,brankDto.getBankId())
                            .eq(SystemMerchantSupportBank::getTxnType,brankDto.getTxnType());
            SystemMerchantSupportBank merchantSupportBank1 = systemMerchantSupportBankMapper.selectOne(lqw);
            if (!brankDto.getIsEnabled().equals(merchantSupportBank1.getIsEnabled())){
                merchantSupportBank1.setIsEnabled(brankDto.getIsEnabled());
                merchantSupportBank1.setMessage(brankDto.getMessage());
                systemMerchantSupportBankMapper.updateById(merchantSupportBank1);
            }
        }

    }

    //按商户status查询
    @Override
    public List<Merchant1Vo> getMerchantByStatus(MerchantDto merchantDto) {
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(new LambdaQueryWrapper<SystemMerchant>()
                .eq(SystemMerchant::getCurrency, merchantDto.getCurrency())
                .eq(SystemMerchant::getStatus, merchantDto.getAssigned()));
        List<Merchant1Vo> merchant1Vos = systemMerchants.stream().map(iter -> {
            Merchant1Vo merchant1Vo = new Merchant1Vo();
            BeanUtils.copyProperties(iter, merchant1Vo);
            return merchant1Vo;
        }).collect(Collectors.toList());
        return merchant1Vos;
    }

    //获取随机数base64
    public static String getSceCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // 生成 0 到 9 之间的随机数字
            sb.append(digit);
        }
        byte[] bytes = ByteBuffer.allocate(4).putLong(Long.parseLong(sb.toString())).array();
        String base64String = Base64.getEncoder().encodeToString(bytes);
        return base64String;
    }
}