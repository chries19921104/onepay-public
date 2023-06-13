package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankLogDto;
import org.example.common.entity.SystemBankLog;

import javax.servlet.http.HttpServletRequest;

/**
* <p>
* system_bank_log ExternalStatementService 接口
* </p>
*
* @author Administrator
* @since 2023-05-31 11:02:45
*/
public interface SystemBankLogService extends IService<SystemBankLog> {

    //银行账户管理-银行-详情log
    MerchantResp getBankLog(BankLogDto bankLogDto, HttpServletRequest request);
}