package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.graphbuilder.math.func.LgFunction;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.BankCardLogDto;
import org.example.admin.mapper.SystemBankCardLogMapper;
import org.example.admin.service.SystemBankCardLogService;
import org.example.admin.vo.BankCardLogVo;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.common.entity.SystemBankCardLog;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* system_bank_card_log Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-06-14 18:58:57
*/
@Service
@Transactional
@Slf4j
public class SystemBankCardLogServiceImpl extends ServiceImpl<SystemBankCardLogMapper, SystemBankCardLog> implements SystemBankCardLogService {

    @Autowired
    private SystemBankCardLogMapper systemBankCardLogMapper;

    @Override
    public MerchantResp getBankCardLog(BankCardLogDto bankCardLogDto, HttpServletRequest request) {
        Page<SystemBankCardLog> page = new Page<>(bankCardLogDto.getPage(),bankCardLogDto.getRp());
        LambdaQueryWrapper<SystemBankCardLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemBankCardLog::getBankCardId,bankCardLogDto.getBankCardId());
        if (bankCardLogDto.getAdminName() != null && !bankCardLogDto.getAdminName().isEmpty()){
            lqw.eq(SystemBankCardLog::getCreator,bankCardLogDto.getAdminName());
        }if (bankCardLogDto.getType() != null){
            lqw.like(SystemBankCardLog::getContent,bankCardLogDto.getType());
        }if (bankCardLogDto.getStartData() != null && !bankCardLogDto.getStartData().isEmpty()){
            lqw.between(SystemBankCardLog::getCreatedAt,bankCardLogDto.getStartData(),bankCardLogDto.getEndData());
        }
        Page<SystemBankCardLog> systemBankCardLogPage = systemBankCardLogMapper.selectPage(page, lqw);
        if (systemBankCardLogPage.getRecords().size() != 0){
            List<BankCardLogVo> collect = systemBankCardLogPage.getRecords().stream().map(iter -> {
                BankCardLogVo bankCardLogVo = new BankCardLogVo();
                BeanUtils.copyProperties(iter, bankCardLogVo);
                return bankCardLogVo;
            }).collect(Collectors.toList());
            Page<BankCardLogVo> logVoPage = new Page<>(bankCardLogDto.getPage(),bankCardLogDto.getRp());
            logVoPage.setRecords(collect);
            logVoPage.setTotal(systemBankCardLogPage.getTotal());
            logVoPage.setCurrent(systemBankCardLogPage.getCurrent());
            logVoPage.setSize(systemBankCardLogPage.getSize());
            return MerchantResp.getMerchantResp(request,logVoPage);
        }else {
            return GetNoResp.getNoResp(request,bankCardLogDto.getRp());
        }
    }
}