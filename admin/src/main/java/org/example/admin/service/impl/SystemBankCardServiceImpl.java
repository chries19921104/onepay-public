package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemBankCardMapper;
import org.example.admin.mapper.SystemBankMapper;
import org.example.admin.service.SystemBankCardService;
import org.example.common.dto.BankCardDto;
import org.example.common.entity.SystemBank;
import org.example.common.entity.SystemBankCard;
import org.example.common.vo.BankCardAllVo;
import org.example.common.vo.BankCardVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}