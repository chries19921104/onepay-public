package org.example.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankCardDto;
import org.example.admin.dto.BankCardGroupDto;
import org.example.admin.dto.SystemBankCardDto;
import org.example.common.entity.SystemBankCardGroup;
import org.example.admin.vo.BankCardAllVo;
import org.example.admin.vo.BankGroupVo;
import org.example.admin.vo.MerchantVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
* <p>
* system_bank_card_group ExternalStatementService 接口
* </p>
*
* @author zhangmi
* @since 2023-05-17 13:44:10
*/
@Service
public interface SystemBankCardGroupService extends IService<SystemBankCardGroup> {

    CommResp currency();

    /**
     * 选择账户群组
     * @return
     */
    List<BankGroupVo> getGroup();

    //银行账户管理-账户群组-搜索
    MerchantResp getGroupAll(BankCardGroupDto bankCardGroupDto, HttpServletRequest request);

    //银行账户管理-账户群组-新增
    Map<String, BankGroupVo> createGroup(BankCardGroupDto bankCardGroupDto);

    //银行账户管理-账户群组-详情
    Map<String, BankGroupVo> getGroupOne(Long id);

    //银行账户管理-账户群组-详情-商户
    List<Map<String, List<MerchantVo>>> getMerchantByGroup(Long id);

    //银行账户管理-账户群组-详情-商户删除或新增
    Map<String, Boolean> deleteOrAddMerchant(BankCardGroupDto bankCardGroupDto);

    //银行账户管理-账户群组-详情-充值账户
    Map<String, List<BankCardAllVo>> getRecharge(SystemBankCardDto bankCardDto);

    //银行账户管理-账户群组-详情-账户删除或新增
    Map<String, Boolean> deleteOrAddAccount(BankCardDto bankCardDto);
}