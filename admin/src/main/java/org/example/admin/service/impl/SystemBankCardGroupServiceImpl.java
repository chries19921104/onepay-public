package org.example.admin.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.AdminsMapper;
import org.example.admin.mapper.SystemBankCardGroupMapper;
import org.example.admin.mapper.SystemBankCardMapper;
import org.example.admin.mapper.SystemMerchantMapper;
import org.example.admin.service.SystemBankCardGroupService;
import org.example.common.base.CommResp;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankCardDto;
import org.example.admin.dto.BankCardGroupDto;
import org.example.admin.dto.SystemBankCardDto;
import org.example.common.entity.*;
import org.example.common.utils.BaseContext;
import org.example.common.utils.URLUtils;
import org.example.admin.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
* <p>
* system_bank_card_group Service 接口实现
* </p>
*
* @author zhangmi
* @since 2023-05-17 13:47:30
*/
@Service
@Transactional
@Slf4j
public class SystemBankCardGroupServiceImpl extends ServiceImpl<SystemBankCardGroupMapper, SystemBankCardGroup> implements SystemBankCardGroupService {

    @Autowired
    private SystemBankCardGroupMapper systemBankCardGroupMapper;

    @Autowired
    private SystemBankCardMapper systemBankCardMapper;

    @Autowired
    private SystemMerchantMapper systemMerchantMapper;

    @Autowired
    private AdminsMapper adminsMapper;

    @Override
    public CommResp currency() {
        List<SystemBankCardGroup> list = this.list();
        List<SystemBankCardGroupVO> collect = list.stream().map(systemBankCardGroup -> {
            SystemBankCardGroupVO systemBankCardGroupVO = new SystemBankCardGroupVO();
            BeanUtils.copyProperties(systemBankCardGroup, systemBankCardGroupVO);
            return systemBankCardGroupVO;
        }).collect(Collectors.toList());
        return CommResp.data(collect);
    }

    /**
     * 选择账户群组
     * @return
     */
    @Override
    public List<BankGroupVo> getGroup() {
        List<SystemBankCardGroup> systemBankCardGroups = systemBankCardGroupMapper.selectList(null);
        List<BankGroupVo> collect = systemBankCardGroups.stream().map(iter -> {
            BankGroupVo agentsByCardGroupVo = new BankGroupVo();
            BeanUtils.copyProperties(iter, agentsByCardGroupVo);
            return agentsByCardGroupVo;
        }).collect(Collectors.toList());
        return collect;
    }

    //银行账户管理-账户群组-搜索
    @Override
    public MerchantResp getGroupAll(BankCardGroupDto bankCardGroupDto, HttpServletRequest request) {
        Page<BankGroupVo> page = new Page<>(bankCardGroupDto.getPage(),bankCardGroupDto.getRp());
        if (bankCardGroupDto.getGroupId() != null && bankCardGroupDto.getGroupId() != 0){
            //1.传了groupid则直接查询返回结果
            SystemBankCardGroup cardGroup = systemBankCardGroupMapper.selectById(bankCardGroupDto.getGroupId());
            if (cardGroup != null){
                BankGroupVo bankGroupVo = new BankGroupVo();
                BeanUtils.copyProperties(cardGroup,bankGroupVo);
                List<BankGroupVo> list = new ArrayList<>();
                list.add(bankGroupVo);
                page.setRecords(list);
                page.setTotal(list.size());
                return getGroupAllRes(page,request,bankCardGroupDto);
            }
        }else {
            //2.没有传groupid的话需要通过其他条件信息查出对应的groupid。
            LambdaQueryWrapper<SystemBankCard> lqw = new LambdaQueryWrapper<>();
            if (bankCardGroupDto.getCardId() != null && bankCardGroupDto.getCardId().size() != 0){
                lqw.in(SystemBankCard::getCardId,bankCardGroupDto.getCardId());
            }if (bankCardGroupDto.getCurrency()!= null && !bankCardGroupDto.getCurrency().isEmpty()){
                lqw.eq(SystemBankCard::getCurrency,bankCardGroupDto.getCurrency());
            }
            List<SystemBankCard> systemBankCards = systemBankCardMapper.selectList(lqw);
            //通过查出来数据中的卡群id去查询商户表查出来对应的商户id来匹配传过来的商户id
            if (systemBankCards.size() == 0){
                return GetNoResp.getNoMerchantResp(request,bankCardGroupDto.getRp());
            }
            List<Long> groupIds = systemBankCards.stream()
                    .map(SystemBankCard::getCardGroupId)
                    .distinct()
                    .collect(Collectors.toList());
            if (bankCardGroupDto.getMerchantId() != null && bankCardGroupDto.getMerchantId().size() != 0){
                Iterator<Long> iterator = groupIds.iterator();
                while (iterator.hasNext()) {
                    Long element = iterator.next();
                    List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(new LambdaQueryWrapper<SystemMerchant>()
                            .eq(SystemMerchant::getCardGroupId,element));
                    if (systemMerchants.size() != 0) {
                        List<Long> collect = systemMerchants.stream().map(SystemMerchant::getMerchantId)
                                .collect(Collectors.toList());
                        if (collect.stream().noneMatch(bankCardGroupDto.getMerchantId()::contains)) {
                            iterator.remove(); // 删除满足条件的元素
                        }
                    }
                }
                //循环完后将原来修改过后的groupids获取出来查询银行卡群组表信息返回前端
                if (groupIds.size() != 0){
                    //分页
                    page.setRecords(getBankGroupVo(groupIds));
                    page.setTotal(getBankGroupVo(groupIds).size());
                    //返回结果
                    return getGroupAllRes(page,request,bankCardGroupDto);
                }
            }else {
                //直接拿groupids查询返回
                page.setRecords(getBankGroupVo(groupIds));
                page.setTotal(getBankGroupVo(groupIds).size());
                return getGroupAllRes(page,request,bankCardGroupDto);
            }
        }
        return GetNoResp.getNoMerchantResp(request,bankCardGroupDto.getRp());
    }

    //银行账户管理-账户群组-新增
    @Override
    public Map<String, BankGroupVo> createGroup(BankCardGroupDto bankCardGroupDto) {
        //获取信息存入数据库，并返回数据
        SystemBankCardGroup systemBankCardGroup = new SystemBankCardGroup();
        systemBankCardGroup.setCurrency(bankCardGroupDto.getCurrency());
        systemBankCardGroup.setGroupName(bankCardGroupDto.getGroupName());
        //更新与创建
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);
        if (admins != null){
            systemBankCardGroup.setCreatedAt(LocalDateTime.now());
            systemBankCardGroup.setCreator(admins.getUsername());
            systemBankCardGroup.setUpdatedAt(LocalDateTime.now());
            systemBankCardGroup.setUpdater(admins.getUsername());
            systemBankCardGroupMapper.insert(systemBankCardGroup);
        }else {
            return null;
        }
        systemBankCardGroup.setGroupId(systemBankCardGroup.getGroupId());
        BankGroupVo bankGroupVo = new BankGroupVo();
        BeanUtils.copyProperties(systemBankCardGroup,bankGroupVo);
        Map<String,BankGroupVo> map = new HashMap<>();
        map.put("data",bankGroupVo);
        return map;
    }

    //银行账户管理-账户群组-详情
    @Override
    public Map<String, BankGroupVo> getGroupOne(Long id) {
        SystemBankCardGroup systemBankCardGroup = systemBankCardGroupMapper.selectById(id);
        if (systemBankCardGroup == null){
            return null;
        }else {
            BankGroupVo bankGroupVo = new BankGroupVo();
            BeanUtils.copyProperties(systemBankCardGroup,bankGroupVo);
            Map<String,BankGroupVo> map = new HashMap<>();
            map.put("data",bankGroupVo);
            return map;
        }
    }

    //银行账户管理-账户群组-详情-商户
    @Override
    public List<Map<String, List<MerchantVo>>> getMerchantByGroup(Long id) {
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(new LambdaQueryWrapper<SystemMerchant>()
                .eq(SystemMerchant::getCardGroupId, id)
                .eq(SystemMerchant::getStatus,1));
        List<MerchantVo> merchantVos = systemMerchants.stream().map(iter -> {
            MerchantVo merchantVo = new MerchantVo();
            BeanUtils.copyProperties(iter, merchantVo);
            return merchantVo;
        }).collect(Collectors.toList());
        List<Map<String, List<MerchantVo>>> list = new ArrayList<>();
        Map<String,List<MerchantVo>> map = new HashMap<>();
        map.put("data",merchantVos);
        list.add(map);
        return list;
    }

    //银行账户管理-账户群组-详情-商户删除或新增
    @Override
    public Map<String, Boolean> deleteOrAddMerchant(BankCardGroupDto bankCardGroupDto) {
        //先通过groupid查询出商户id的集合
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(new LambdaQueryWrapper<SystemMerchant>()
                .eq(SystemMerchant::getCardGroupId, bankCardGroupDto.getGroupId())
                .eq(SystemMerchant::getStatus,1));
        //得到商户的ids
        List<Long> merchantIds = systemMerchants.stream().map(SystemMerchant::getMerchantId).collect(Collectors.toList());
        //判断ids的长度与传来的ids，如果比传来的ids长则为删除，反之为新增
        if (merchantIds.size() > bankCardGroupDto.getMerchantId().size()){
            //将查询的ids和前端传过来的ids进行比对，将删除的那个merchantid得到
            List<Long> collect = merchantIds.stream().filter(iter -> !bankCardGroupDto.getMerchantId().contains(iter))
                    .collect(Collectors.toList());
            //将状态改为等待
            SystemMerchant systemMerchant = new SystemMerchant();
            systemMerchant.setMerchantId(collect.get(0));
            systemMerchant.setCardGroupId(null);
            systemMerchant.setStatus(0);
            systemMerchantMapper.updateById(systemMerchant);
            Map<String ,Boolean> map = new HashMap<>();
            map.put("success",true);
            return map;
        }else if (merchantIds.size() < bankCardGroupDto.getMerchantId().size()){
            //需要添加的id放在list的最后一位，直接获取即可
            SystemMerchant systemMerchant = new SystemMerchant();
            systemMerchant.setMerchantId(bankCardGroupDto.getMerchantId().get(bankCardGroupDto.getMerchantId().size()-1));
            systemMerchant.setCardGroupId(bankCardGroupDto.getGroupId());
            systemMerchant.setStatus(1);
            systemMerchantMapper.updateById(systemMerchant);
            Map<String ,Boolean> map = new HashMap<>();
            map.put("success",true);
            return map;
        }else {
            return null;
        }
    }

    //银行账户管理-账户群组-详情-账户
    @Override
    public Map<String, List<BankCardAllVo>> getRecharge(SystemBankCardDto bankCardDto) {
        //通过状态和群组id来查询brankCard表，返回信息
        List<SystemBankCard> systemBankCards = systemBankCardMapper.selectList(new LambdaQueryWrapper<SystemBankCard>()
                .eq(SystemBankCard::getCardGroupId, bankCardDto.getCardGroupId())
                .eq(SystemBankCard::getType, bankCardDto.getType())
                .eq(SystemBankCard::getStatus,1));
        List<BankCardAllVo> bankCardAllVos = systemBankCards.stream().map(iter -> {
            BankCardAllVo bankCardAllVo = new BankCardAllVo();
            BeanUtils.copyProperties(iter, bankCardAllVo,"statement");
            return bankCardAllVo;
        }).collect(Collectors.toList());
        Map<String ,List<BankCardAllVo>> map = new HashMap<>();
        map.put("data",bankCardAllVos);
        return map;
    }

    //银行账户管理-账户群组-详情-账户删除或新增
    @Override
    public Map<String, Boolean> deleteOrAddAccount(BankCardDto bankCardDto) {
        //通过groupid和type来查询原有的cardid
        List<SystemBankCard> systemBankCards = systemBankCardMapper.selectList(new LambdaQueryWrapper<SystemBankCard>()
                .eq(SystemBankCard::getCardGroupId, bankCardDto.getCardGroupId())
                .eq(SystemBankCard::getType, bankCardDto.getType())
                .eq(SystemBankCard::getStatus,1));
        //获取cardids
        List<Long> cardIds = systemBankCards.stream().map(SystemBankCard::getCardId).collect(Collectors.toList());
        //判断ids比传来的ids如果多的话，则为删除，反之为新增
        if (cardIds.size() > bankCardDto.getCardId().size()){
            //将查询的ids和前端传过来的ids进行比对，将删除的那个cardid得到
            List<Long> collect = cardIds.stream().filter(iter -> !bankCardDto.getCardId().contains(iter))
                    .collect(Collectors.toList());
            SystemBankCard systemBankCard = new SystemBankCard();
            systemBankCard.setCardId(collect.get(0));
            systemBankCard.setStatus(0);
            systemBankCardMapper.updateById(systemBankCard);
            Map<String ,Boolean> map = new HashMap<>();
            map.put("success",true);
            return map;
        }else if (cardIds.size() < bankCardDto.getCardId().size()){
            SystemBankCard systemBankCard = new SystemBankCard();
            systemBankCard.setCardId(bankCardDto.getCardId().get(bankCardDto.getCardId().size()-1));
            systemBankCard.setCardGroupId(bankCardDto.getCardGroupId());
            systemBankCard.setStatus(1);
            systemBankCardMapper.updateById(systemBankCard);
            Map<String ,Boolean> map = new HashMap<>();
            map.put("success",true);
            return map;
        }else {
            return null;
        }
    }


    private MerchantResp getGroupAllRes(Page<BankGroupVo> page, HttpServletRequest request,BankCardGroupDto bankCardGroupDto) {

        MerchantResp merchantResp = new MerchantResp();
        //
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + bankCardGroupDto.getPage());
        if (page.getPages()>page.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (page.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) page.getCurrent());
        merchantResp.setData(page.getRecords());
        if (page.getTotal() != 0){
            merchantResp.setFrom((int) page.getCurrent());
        }
        merchantResp.setLast_page((int) page.getPages());
        merchantResp.setPer_page(bankCardGroupDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        if (page.getTotal() != 0){
            merchantResp.setTo((int) page.getTotal());
        }
        merchantResp.setTotal((int) page.getTotal());
        return merchantResp;
    }

    private List<BankGroupVo> getBankGroupVo(List<Long> groupIds){
        List<SystemBankCardGroup> bankCardGroups = systemBankCardGroupMapper.selectBatchIds(groupIds);
        return bankCardGroups.stream().map(iter -> {
            BankGroupVo bankGroupVo = new BankGroupVo();
            BeanUtils.copyProperties(iter, bankGroupVo);
            return bankGroupVo;
        }).collect(Collectors.toList());
    }

}