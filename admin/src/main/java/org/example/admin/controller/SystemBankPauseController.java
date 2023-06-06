package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemBankPauseService;
import org.example.admin.dto.BankPauseDto;
import org.example.admin.vo.BankPauseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
* <p>
* system_bank_pause 控制器实现
* </p>
*
* @author Administrator
* @since 2023-06-01 12:50:20
*/
@RestController
@RequestMapping("/api")
@Api(tags = "银行暂停时间表控制器")
public class SystemBankPauseController {

    @Autowired
    private SystemBankPauseService systemBankPauseService;

    //http://localhost:8088/api/bk120?
    // currency=THB&
    // type=1&status=1&
    // rp=100&page=1&
    // BK100_ID=1&
    // start_date=2019-06-01+00:00:00&end_date=2023-07-01+23:59:59
    @ApiOperation(value = "银行账户管理-暂停时间表设定-搜索")
    @GetMapping("/bk120")
    public Map<String, List<BankPauseVo>> getBrankPause(BankPauseDto brankDto) {
        return systemBankPauseService.getBrankPause(brankDto);
    }

    //http://localhost:8088/api/bk120
    @ApiOperation(value = "银行账户管理-暂停时间表设定-新增")
    @PostMapping("/bk120")
    public Map<String, BankPauseVo> createBrankPause(@RequestBody BankPauseDto brankDto) {
        return systemBankPauseService.createBrankPause(brankDto);
    }

    //http://localhost:8088/api/bk120/84
    @ApiOperation(value = "银行账户管理-暂停时间表设定-删除")
    @DeleteMapping("/bk120/{id}")
    @NoAuthorization
    public Map<String,Boolean> deleteBankPause(@PathVariable("id") Integer id) {
        return systemBankPauseService.deleteBankPause(id);
    }

    //http://localhost:8088/api/bk120/85
    @ApiOperation(value = "银行账户管理-暂停时间表设定-状态")
    @PutMapping("/bk120/{id}")
    @NoAuthorization
    public Map<String,Boolean> updateBankPause(@RequestBody BankPauseDto brankDto) {
        return systemBankPauseService.updateBankPause(brankDto);
    }
}
