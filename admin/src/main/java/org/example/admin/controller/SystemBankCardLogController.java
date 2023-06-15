package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.dto.BankCardLogDto;
import org.example.admin.service.SystemBankCardLogService;
import org.example.admin.vo.AdminsVO;
import org.example.common.base.MerchantResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;


/**
* <p>
* system_bank_card_log 控制器实现
* </p>
*
* @author Administrator
* @since 2023-06-14 18:57:31
*/
@RestController
@RequestMapping("/system/bank/card/log")
@Api(tags = "银行卡log控制器")
public class SystemBankCardLogController {

    @Autowired
    private SystemBankCardLogService systemBankCardLogService;

    //http://localhost:8088/api/bc100log?BC100_ID=57&type=type&admin_name=nora&rp=100&page=1
    @ApiOperation(value ="银行账户-详情-log查询")
    @GetMapping("/api/bc100log")
    @NoAuthorization
    public MerchantResp getBankCardLog(BankCardLogDto bankCardLogDto , HttpServletRequest request){
        return systemBankCardLogService.getBankCardLog(bankCardLogDto,request);
    }

}
