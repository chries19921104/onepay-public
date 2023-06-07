package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.InternalReportsMapper;
import org.example.admin.mapper.SystemBankCardMapper;
import org.example.admin.mapper.SystemSelectOptionConfigMapper;
import org.example.admin.service.InternalReportsService;
import org.example.admin.service.SystemSelectOptionConfigService;
import org.example.common.base.CommResp;
import org.example.common.dto.BankAccountListDto;
import org.example.common.entity.SystemBankCard;
import org.example.common.entity.SystemSelectOptionConfig;
import org.example.common.vo.BankAccountListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 16:15:56
 */
@Service
@Transactional
@Slf4j
public class InternalReportsServiceImpl implements InternalReportsService {

    @Autowired
    private InternalReportsMapper internalReportsMapper;
    @Resource
    private SystemSelectOptionConfigMapper systemSelectOptionConfigMapper;

    @Override
    public CommResp getBankAccountList(String currency,List<String> PG100_ID,String BK100_ID,List<String> type,List<String> status,List<String> mode,String account_code,String BC100_name) {
        List<BankAccountListVo> bankAccountList = internalReportsMapper.getBankAccountList(currency,PG100_ID,BK100_ID,type,status,mode,account_code,BC100_name);

//        for (BankAccountListVo bankAccountListVo: bankAccountList) {
//            SystemSelectOptionConfig bc100Type = systemSelectOptionConfigMapper.selectOne(new LambdaQueryWrapper<SystemSelectOptionConfig>()
//                    .eq(SystemSelectOptionConfig::getType, "BC100_type")
//                    .eq(SystemSelectOptionConfig::getNum, bankAccountListVo.getBC100_type()));
//            if (bc100Type.getContent() != null && bc100Type.getContent().length() != 0) {
//                bankAccountListVo.setBC100_type_PG100_name(bankAccountListVo.getBC100_type());
//            } else {
//                bankAccountListVo.setBC100_type_PG100_name(bc100Type.getContent());
//            }
//
//        }

        return CommResp.data(bankAccountList);
    }


}
