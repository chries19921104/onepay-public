package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.BankCardLogDto;
import org.example.common.base.MerchantResp;
import org.example.common.entity.SystemBankCardLog;

import javax.servlet.http.HttpServletRequest;

/**
* <p>
* system_bank_card_log Service 接口
* </p>
*
* @author Administrator
* @since 2023-06-14 18:58:38
*/
public interface SystemBankCardLogService extends IService<SystemBankCardLog> {

    //银行账户-详情-log查询
    MerchantResp getBankCardLog(BankCardLogDto bankCardLogDto, HttpServletRequest request);
}