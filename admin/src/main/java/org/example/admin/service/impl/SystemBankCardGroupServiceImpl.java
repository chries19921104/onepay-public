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
import org.example.common.dto.BankCardGroupDto;
import org.example.common.entity.*;
import org.example.common.utils.BaseContext;
import org.example.common.utils.URLUtils;
import org.example.common.vo.BankCardVo;
import org.example.common.vo.BankGroupVo;
import org.example.common.vo.SystemBankCardGroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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
    public Map<String, BankGroupVo> getMerchantByGroup(Long id) {
        List<SystemMerchant> systemMerchants = systemMerchantMapper.selectList(new LambdaQueryWrapper<SystemMerchant>()
                .eq(SystemMerchant::getCardGroupId, id));

        return null;
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