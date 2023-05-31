package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemBankLogMapper;
import org.example.admin.service.SystemBankLogService;
import org.example.common.base.MerchantResp;
import org.example.common.dto.BankLogDto;
import org.example.common.entity.SystemBankLog;
import org.example.common.entity.SystemMerchant;
import org.example.common.utils.URLUtils;
import org.example.common.vo.BankLogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* system_bank_log Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-05-31 11:03:08
*/
@Service
@Transactional
@Slf4j
public class SystemBankLogServiceImpl extends ServiceImpl<SystemBankLogMapper, SystemBankLog> implements SystemBankLogService {

    @Autowired
    private SystemBankLogMapper systemBankLogMapper;

    @Override
    public MerchantResp getBankLog(BankLogDto bankLogDto, HttpServletRequest request) {
        MerchantResp merchantResp = new MerchantResp();
        Page<SystemBankLog> page = new Page<>(bankLogDto.getPage(),bankLogDto.getRp());
        LambdaQueryWrapper<SystemBankLog> lqw = new LambdaQueryWrapper<>();
        if (bankLogDto.getBankId()!= null){
            lqw.eq(SystemBankLog::getBankId,bankLogDto.getBankId());
        }if (bankLogDto.getType()!= null && !bankLogDto.getType().isEmpty()){
            lqw.like(SystemBankLog::getContent,bankLogDto.getType());
        }if (bankLogDto.getOperator()!= null){
            lqw.eq(SystemBankLog::getOperator,bankLogDto.getOperator());
        }if (bankLogDto.getStartDate() != null && !bankLogDto.getStartDate().isEmpty()) {
            lqw.between(SystemBankLog::getCreatedAt, Timestamp.valueOf(bankLogDto.getStartDate()), Timestamp.valueOf(bankLogDto.getEndDate()));
        }
        Page<SystemBankLog> bankLogPage = systemBankLogMapper.selectPage(page, lqw);
        List<BankLogVo> bankLogVos = bankLogPage.getRecords().stream().map(iter -> {
            BankLogVo bankLogVo = new BankLogVo();
            BeanUtils.copyProperties(iter, bankLogVo);
            return bankLogVo;
        }).collect(Collectors.toList());
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + bankLogPage.getPages());
        if (bankLogPage.getPages()>bankLogPage.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (bankLogPage.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) bankLogPage.getCurrent());
        merchantResp.setData(bankLogVos);
        if (bankLogPage.getTotal() != 0){
            merchantResp.setFrom((int) bankLogPage.getCurrent());
        }
        merchantResp.setLast_page((int) bankLogPage.getPages());
        merchantResp.setPer_page(bankLogDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        if (bankLogPage.getTotal() != 0){
            merchantResp.setTo((int) bankLogPage.getTotal());
        }
        merchantResp.setTotal((int) bankLogPage.getTotal());
        return merchantResp;
    }
}