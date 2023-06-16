package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.dto.BankCardCreateDto;
import org.example.admin.service.SystemBankCardService;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BankCardDto;
import org.example.admin.vo.BankCardAllVo;
import org.example.admin.vo.BankCardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation(value = "银行账户管理-银行账户-搜索")
    @GetMapping("/bc100")
    public MerchantResp getBrankAccountList(BankCardDto bankCardDto ,HttpServletRequest request) {
        return systemBankCardService.getBrankAccountList(bankCardDto,request);
    }

    //http://localhost:8088/api/bc100
    @ApiOperation(value = "银行账户管理-银行账户-新增")
    @PostMapping("/bc100")
    public Map<String,BankCardAllVo> createBrankAccount(BankCardCreateDto bankCardDto) {
        return systemBankCardService.createBrankAccount(bankCardDto);
    }

    //http://localhost:8088/api/bc100/228
    @ApiOperation(value = "银行账户管理-银行账户-详情")
    @GetMapping("/bc100/{id}")
    public Map<String,BankCardAllVo> getBrankAccount(Long id) {
        return systemBankCardService.getBrankAccount(id);
    }

    //http://localhost:8088/api/bc100/56
    @ApiOperation(value = "银行账户管理-银行账户-修改")
    @PutMapping("/bc100/{id}")
    public Map<String,Boolean> updateBrankAccount(BankCardAllVo bankCardAllVo) {
        return systemBankCardService.updateBrankAccount(bankCardAllVo);
    }
}
