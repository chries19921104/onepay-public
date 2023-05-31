package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemBankCardService;
import org.example.common.dto.BankCardDto;
import org.example.common.vo.BankCardAllVo;
import org.example.common.vo.BankCardVo;
import org.example.common.vo.BrankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
* <p>
* system_bank_card 控制器实现
* </p>
*
* @author Administrator
* @since 2023-05-25 13:43:08
*/
@Api(tags = "银行卡控制器")
@RestController
@RequestMapping("/api")
public class SystemBankCardController {

    @Autowired
    private SystemBankCardService systemBankCardService;

    //http://localhost:8088/api/bc100/simple?with=bank_code,account_code,currency
    @ApiOperation(value = "有关银行卡账户号码选项列表查询接口")
    @GetMapping("/bc100/simple")
    public List<BankCardVo> getBrank(@RequestParam("with") List<String> with) {
        return systemBankCardService.getBranks();
    }

    //http://localhost:8088/api/bc100/all?type=1&assigned=0&currency=THB
    @ApiOperation(value = "查找不同type，status，curreny的账户")
    @GetMapping("/bc100/all")
    public Map<String, List<BankCardAllVo>> getBrankByType(BankCardDto bankCardDto) {
        return systemBankCardService.getBrankByType(bankCardDto);
    }
}