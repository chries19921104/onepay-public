package org.example.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.InternalReportsService;
import org.example.admin.service.SystemBankCardService;
import org.example.common.base.CommResp;
import org.example.common.vo.BankCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 15:52:28
 */

@Api(tags = "内部报表模块")
@RestController
@RequestMapping("/api")
public class InternalReportsController {

    @Resource
    private InternalReportsService internalReportsService;


    @ApiOperation(value = "银行账户监控列表查询接口")
    @GetMapping("/bankAccountList")
    public CommResp getBankAccountList() {

        return internalReportsService.getBankAccountList();

    }

}
