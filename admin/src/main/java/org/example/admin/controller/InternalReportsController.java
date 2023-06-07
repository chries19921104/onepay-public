package org.example.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.InternalReportsService;
import org.example.admin.service.SystemBankCardService;
import org.example.common.base.CommResp;
import org.example.common.dto.BankAccountListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 15:52:28
 */

@Api(tags = "内部报表模块")
@RestController
@RequestMapping("")
public class InternalReportsController {

    @Resource
    private InternalReportsService internalReportsService;


    @ApiOperation(value = "银行账户监控列表查询接口")
    @GetMapping("/bankAccountList")
    public CommResp getBankAccountList(HttpServletRequest request) {
        //获取 currency
        String currency = request.getParameter("currency");

        //获取 PG100_ID
        String[] listPG100_ID = request.getParameterValues("PG100_ID");
        List<String> PG100_ID = null;
        if (listPG100_ID != null){
            PG100_ID = Arrays.asList(listPG100_ID);
        }

        //获取 BK100_ID
        String BK100_ID = request.getParameter("BK100_ID");

        //获取 type
        String[] listType = request.getParameterValues("type");
        List<String> type = null;
        if (listType != null){
            type = Arrays.asList(listType);
        }

        //获取 status
        String[] listStatus = request.getParameterValues("status");
        List<String> status = null;
        if (listStatus != null){
            status = Arrays.asList(listStatus);
        }

        //获取 mode
        String[] listMode = request.getParameterValues("mode");
        List<String> mode = null;
        if (listMode != null){
            mode = Arrays.asList(listMode);
        }

        //获取 account_code
        String account_code = request.getParameter("account_code");

        //获取 BC100_name
        String BC100_name = request.getParameter("BC100_name");

        return internalReportsService.getBankAccountList(currency,PG100_ID,BK100_ID,type,status,mode,account_code,BC100_name);
    }

}
