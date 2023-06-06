package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.AdminsMapper;
import org.example.admin.mapper.SystemBankMapper;
import org.example.admin.service.SystemBankService;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BrankDto;
import org.example.common.entity.Admins;
import org.example.common.entity.SystemBank;
import org.example.common.utils.BaseContext;
import org.example.common.utils.URLUtils;
import org.example.admin.vo.BrankVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>
* system_bank Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-05-25 11:00:33
*/
@Service
@Transactional
@Slf4j
public class SystemBankServiceImpl extends ServiceImpl<SystemBankMapper, SystemBank> implements SystemBankService {

    @Autowired
    private SystemBankMapper systemBankMapper;

    @Autowired
    private AdminsMapper adminsMapper;

    /**
     * 银行信息
     * @param status
     * @return
     */
    @Override
    public List<BrankVo> getBranksByStatus(Integer status) {
        List<SystemBank> systemBanks = systemBankMapper.selectList(new LambdaQueryWrapper<SystemBank>()
                .eq(SystemBank::getStatus, status));
        //拷贝属性
        List<BrankVo> collect = systemBanks.stream().map(iter -> {
            BrankVo brankVo = new BrankVo();
            BeanUtils.copyProperties(iter, brankVo);
            return brankVo;
        }).collect(Collectors.toList());
        return collect;
    }

    //银行账户管理-银行-查询
    @Override
    public MerchantResp getBrankByCurrency(BrankDto brankDto, HttpServletRequest request) {
        MerchantResp merchantResp = new MerchantResp();
        Page<SystemBank> page = new Page<>(brankDto.getPage(),brankDto.getRp());
        Page<SystemBank> bankPage = systemBankMapper.selectPage(page, new LambdaQueryWrapper<SystemBank>()
                .eq(SystemBank::getCurrency, brankDto.getCurrency()));
        //拷贝属性data
        List<BrankVo> brankVos = bankPage.getRecords().stream().map(iter -> {
            BrankVo brankVo = new BrankVo();
            BeanUtils.copyProperties(iter, brankVo);
            return brankVo;
        }).collect(Collectors.toList());
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + bankPage.getPages());
        if (bankPage.getPages()>bankPage.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (bankPage.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) bankPage.getCurrent());
        merchantResp.setData(brankVos);
        if (bankPage.getTotal() != 0){
            merchantResp.setFrom((int) bankPage.getCurrent());
        }
        merchantResp.setLast_page((int) bankPage.getPages());
        merchantResp.setPer_page(brankDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        if (bankPage.getTotal() != 0){
            merchantResp.setTo((int) bankPage.getTotal());
        }
        merchantResp.setTotal((int) bankPage.getTotal());
        return merchantResp;
    }

    //银行账户管理-银行-新增
    @Override
    public Map<String, BrankVo> createBank(BrankVo brankVo) {
        SystemBank systemBank = new SystemBank();
        BeanUtils.copyProperties(brankVo,systemBank);
        //更新与创建
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);
        systemBank.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        systemBank.setCreator(admins.getUsername());
        systemBank.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        systemBank.setUpdater(admins.getUsername());
        systemBankMapper.insert(systemBank);
        systemBank.setBankId(systemBank.getBankId());

        BeanUtils.copyProperties(systemBank,brankVo);
        Map<String,BrankVo> map = new HashMap<>();
        map.put("data",brankVo);
        return map;
    }

    //银行账户管理-银行-编辑
    @Override
    public Map<String, Boolean> updateBank(BrankVo brankVo) {
        SystemBank systemBank = new SystemBank();
        BeanUtils.copyProperties(brankVo,systemBank);
        systemBankMapper.updateById(systemBank);
        Map<String,Boolean> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @Override
    public List<BrankVo> getBranksAll() {
        List<SystemBank> systemBanks = systemBankMapper.selectList(null);
        //拷贝属性
        List<BrankVo> collect = systemBanks.stream().map(iter -> {
            BrankVo brankVo = new BrankVo();
            BeanUtils.copyProperties(iter, brankVo);
            return brankVo;
        }).collect(Collectors.toList());
        return collect;
    }

}