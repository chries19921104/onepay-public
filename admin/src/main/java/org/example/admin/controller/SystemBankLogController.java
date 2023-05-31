package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemBankLogService;
import org.example.common.base.MerchantResp;
import org.example.common.dto.BankLogDto;
import org.example.common.vo.BrankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
* <p>
* system_bank_log 控制器实现
* </p>
*
* @author Administrator
* @since 2023-05-31 11:03:20
*/
@RestController
@RequestMapping("/system/bank/log")
@Api(tags = "银行日志控制器")
public class SystemBankLogController {

    @Autowired
    private SystemBankLogService systemBankLogService;

    //http://localhost:8088/api/bk100log?
    // BK100_ID=1&
    // type=FI_min&
    // admin_name=nora&
    // rp=100&page=1&start_date=2019-05-01&end_date=2023-05-31
    @ApiOperation(value = "银行账户管理-银行-详情log")
    @GetMapping("/bk100log")
    @NoAuthorization
    public MerchantResp getBankLog(BankLogDto bankLogDto, HttpServletRequest request) {
        return systemBankLogService.getBankLog(bankLogDto,request);
    }

}
