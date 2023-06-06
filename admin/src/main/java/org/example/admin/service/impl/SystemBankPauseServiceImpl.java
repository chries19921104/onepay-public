package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.AdminsMapper;
import org.example.admin.mapper.SystemBankMapper;
import org.example.admin.mapper.SystemBankPauseMapper;
import org.example.admin.service.SystemBankPauseService;
import org.example.common.base.CommResp;
import org.example.admin.dto.BankPauseDto;
import org.example.common.entity.Admins;
import org.example.common.entity.SystemBank;
import org.example.common.entity.SystemBankPause;
import org.example.common.utils.BaseContext;
import org.example.admin.vo.BankPauseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
* <p>
* system_bank_pause Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-06-01 12:50:09
*/
@Service
@Transactional
@Slf4j
public class SystemBankPauseServiceImpl extends ServiceImpl<SystemBankPauseMapper, SystemBankPause> implements SystemBankPauseService {

    @Autowired
    private SystemBankPauseMapper systemBankPauseMapper;

    @Autowired
    private SystemBankMapper systemBankMapper;

    @Autowired
    private AdminsMapper adminsMapper;

    //银行账户管理-暂停时间表设定-搜索
    @Override
    public Map<String, List<BankPauseVo>> getBrankPause(BankPauseDto brankDto) {

        Map<String,List<BankPauseVo>> map = new HashMap<>();
        //通过传来的条件查询system_bank_pause表
        LambdaQueryWrapper<SystemBankPause> lqw = new LambdaQueryWrapper<>();
        if (brankDto.getType() != null){
            lqw.eq(SystemBankPause::getType,brankDto.getType());
        }if (brankDto.getStatus() != null){
            lqw.eq(SystemBankPause::getStatus,brankDto.getStatus());
        }if (brankDto.getBankId() != null){
            lqw.eq(SystemBankPause::getBankId,brankDto.getBankId());
        }if (brankDto.getStartDate() != null && !brankDto.getStartDate().isEmpty()){
            lqw.between(SystemBankPause::getCreatedAt,brankDto.getStartDate(),brankDto.getEndDate());
        }
        List<SystemBankPause> systemBankPauses = systemBankPauseMapper.selectList(lqw);
        if (systemBankPauses.size() == 0){
            map.put("data",null);
            return map;
        }
        if (brankDto.getBankId() != null){
            SystemBank systemBank = systemBankMapper.selectById(brankDto.getBankId());
            List<BankPauseVo> bankPauseVos = systemBankPauses.stream().map(iter -> {
                BankPauseVo bankPauseVo = new BankPauseVo();
                BeanUtils.copyProperties(iter, bankPauseVo,"daysOfWeek");
                if (iter.getFrequency() == 3){
                    List<String> daysOfWeek = getDaysOfWeek2(iter.getDaysOfWeek());
                    bankPauseVo.setDaysOfWeek(daysOfWeek);
                }
                bankPauseVo.setBankCode(systemBank.getCode());
                return bankPauseVo;
            }).collect(Collectors.toList());
            //返回结果
            map.put("data",bankPauseVos);
            return map;
        }
        //查询出currency对应的银行id
        List<SystemBank> systemBanks = systemBankMapper.selectList(new LambdaQueryWrapper<SystemBank>()
                .eq(SystemBank::getCurrency, brankDto.getCurrency()));
        if (systemBanks.size() == 0){
            map.put("data",null);
            return map;
        }
        //将两边的bankid进行一个交集处理，剩下的就是符合要求的。
        List<Long> collect = systemBanks.stream().map(SystemBank::getBankId).collect(Collectors.toList());
        List<Long> collect1 = systemBankPauses.stream().map(SystemBankPause::getBankId).collect(Collectors.toList());
        collect.retainAll(collect1);
        //将原来的systemBankPauses中对象的bankid进行和交集判断
        Iterator<SystemBankPause> iterator = systemBankPauses.iterator();
        while (iterator.hasNext()) {
            SystemBankPause element = iterator.next();
            if (!collect.contains(element.getBankId())){
                iterator.remove();
            }
        }
        if (systemBankPauses.size() == 0){
            map.put("data",null);
            return map;
        }
        List<BankPauseVo> bankPauseVos = systemBankPauses.stream().map(iter -> {
            BankPauseVo bankPauseVo = new BankPauseVo();
            BeanUtils.copyProperties(iter, bankPauseVo,"daysOfWeek");
            List<String> daysOfWeek = getDaysOfWeek2(iter.getDaysOfWeek());
            bankPauseVo.setDaysOfWeek(daysOfWeek);
            SystemBank systemBank = systemBankMapper.selectById(iter.getBankId());
            bankPauseVo.setBankCode(systemBank.getCode());
            return bankPauseVo;
        }).collect(Collectors.toList());
        //返回结果
        map.put("data",bankPauseVos);
        return map;
    }

    //银行账户管理-暂停时间表设定-新增
    @Override
    public Map<String,BankPauseVo> createBrankPause(BankPauseDto brankDto) {
        //先拷贝信息，除了day——of——week
        SystemBankPause  systemBankPause = new SystemBankPause();
        BeanUtils.copyProperties(brankDto,systemBankPause,"daysOfWeek");
        if (brankDto.getFrequency() == 3){
            Integer daysOfWeek = getDaysOfWeek1(brankDto);
            systemBankPause.setDaysOfWeek(daysOfWeek);
        }else {
            systemBankPause.setDaysOfWeek(0);
        }
        //更新与创建
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);
        systemBankPause.setCreatedAt(LocalDateTime.now());
        systemBankPause.setCreator(admins.getUsername());
        systemBankPause.setUpdatedAt(LocalDateTime.now());
        systemBankPause.setUpdater(admins.getUsername());
        systemBankPauseMapper.insert(systemBankPause);

        //返回结果
        BankPauseVo bankPauseVo = new BankPauseVo();
        BeanUtils.copyProperties(systemBankPause,bankPauseVo,"daysOfWeek");
        bankPauseVo.setId(systemBankPause.getId());
        bankPauseVo.setDaysOfWeek(brankDto.getDayOfWeek());
        Map<String, BankPauseVo> map = new HashMap<>();
        map.put("data",bankPauseVo);
        return map;
    }

    //银行账户管理-暂停时间表设定-删除
    @Override
    public Map<String, Boolean> deleteBankPause(Integer id) {
        systemBankPauseMapper.deleteById(id);
        return CommResp.success();
    }

    //银行账户管理-暂停时间表设定-状态
    @Override
    public Map<String, Boolean> updateBankPause(BankPauseDto brankDto) {
        SystemBankPause systemBankPause = new SystemBankPause();
        systemBankPause.setId(brankDto.getId());
        systemBankPause.setStatus(brankDto.getStatus());
        systemBankPauseMapper.updateById(systemBankPause);
        return CommResp.success();
    }

    //DaysOfWeek字段转换方法
    private Integer getDaysOfWeek1(BankPauseDto brankDto) {
        int dayOfWeek = 0;
        for (String s : brankDto.getDayOfWeek()) {
            if ("Sunday".equals(s)) {
                dayOfWeek += 1;
            }else if ("Monday".equals(s)){
                dayOfWeek += 2;
            }else if ("Tuesday".equals(s)){
                dayOfWeek += 4;
            }else if ("Wednesday".equals(s)){
                dayOfWeek += 8;
            }else if ("Thursday".equals(s)){
                dayOfWeek += 16;
            }else if ("Friday".equals(s)){
                dayOfWeek += 32;
            }else if ("Saturday".equals(s)){
                dayOfWeek += 64;
            }else {
                dayOfWeek += 0;
            }
        }
        return dayOfWeek;
    }

    //DaysOfWeek字段转换方法
    private List<String> getDaysOfWeek2(Integer daysOfWeek) {
        List<String> s = new ArrayList<>();
        while (daysOfWeek != 0){
            if (daysOfWeek >= 64){
                daysOfWeek -= 64;
                s.add("Saturday");
            }else if (daysOfWeek >= 32){
                daysOfWeek -= 32;
                s.add("Friday");
            }else if (daysOfWeek >= 16){
                daysOfWeek -= 16;
                s.add("Thursday");
            }else if (daysOfWeek >= 8){
                daysOfWeek -= 8;
                s.add("Wednesday");
            }else if (daysOfWeek >= 4){
                daysOfWeek -= 4;
                s.add("Tuesday");
            }else if (daysOfWeek >= 2){
                daysOfWeek -= 2;
                s.add("Monday");
            }else {
                daysOfWeek -= 1;
                s.add("Sunday");
            }
        }
        return s;
    }
}