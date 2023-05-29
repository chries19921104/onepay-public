package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemBankMapper;
import org.example.admin.service.SystemBankService;
import org.example.common.entity.SystemBank;
import org.example.common.vo.BrankVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
            BeanUtils.copyProperties(iter, brankVo);
            return brankVo;
        }).collect(Collectors.toList());
        return collect;
    }

}