package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.dto.BankAccountListDto;
import org.example.admin.service.InternalReportsService;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.util.Arrays;
import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 15:52:28
 */

@Api(tags = "内部报表模块")
@RestController
@RequestMapping("")
@Validated
public class InternalReportsController {

    @Resource
    private InternalReportsService internalReportsService;


    @ApiOperation(value = "银行账户监控列表查询接口")
    @GetMapping("/bankAccountList")
    public MerchantResp getBankAccountList(HttpServletRequest request, BankAccountListDto bankAccountListDto) {
        return internalReportsService.getBankAccountList(request, bankAccountListDto);
    }

    @ApiOperation(value = "交易画面记录查询接口")
    @GetMapping("/transactionScreenRecords")
    public CommResp getTransactionScreenRecords(@Max(255) Integer type, String alt_id, Integer rp, Integer page) {
        return internalReportsService.getTransactionScreenRecords(alt_id,rp,page);
    }
}
