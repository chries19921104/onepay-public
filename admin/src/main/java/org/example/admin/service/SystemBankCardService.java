package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.BankCardCreateDto;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankCardDto;
import org.example.common.entity.SystemBankCard;
import org.example.admin.vo.BankCardAllVo;
import org.example.admin.vo.BankCardVo;

import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

/**
* <p>
* system_bank_card ExternalStatementService 接口
* </p>
*
* @author Administrator
* @since 2023-05-25 13:43:38
*/
public interface SystemBankCardService extends IService<SystemBankCard> {

    //有关银行卡账户号码选项列表查询接口
    List<BankCardVo> getBranks();

    //查找不同type，status，curreny的账户
    Map<String, List<BankCardAllVo>> getBrankByType(BankCardDto bankCardDto);

    //银行账户管理-银行账户-搜索
    MerchantResp getBrankAccountList(BankCardDto bankCardDto, HttpServletRequest request);

    //银行账户管理-银行账户-新增
    Map<String,BankCardAllVo> createBrankAccount(BankCardCreateDto bankCardDto);

    //银行账户管理-银行账户-详情
    Map<String, BankCardAllVo> getBrankAccount(Long id);

    //银行账户管理-银行账户-修改
    Map<String, Boolean> updateBrankAccount(BankCardAllVo bankCardAllVo);
}