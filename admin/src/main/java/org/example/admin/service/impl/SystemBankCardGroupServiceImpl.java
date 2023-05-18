package org.example.admin.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemBankCardGroupMapper;
import org.example.admin.service.SystemBankCardGroupService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemBankCardGroup;
import org.example.common.vo.SystemBankCardGroupVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
}