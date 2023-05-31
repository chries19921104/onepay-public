package org.example.admin.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemBankCardGroupService;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.common.dto.BankCardDto;
import org.example.common.dto.BankCardGroupDto;
import org.example.common.dto.MerchantDto;
import org.example.common.dto.SystemBankCardDto;
import org.example.common.vo.BankCardAllVo;
import org.example.common.vo.BankGroupVo;
import org.example.common.vo.Merchant1Vo;
import org.example.common.vo.MerchantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
* <p>
* system_bank_card_group 控制器实现
* </p>
*
* @author zhangmi
* @since 2023-05-17 13:48:38
*/
@Api(tags = "银行群组控制器")
@RestController
@RequestMapping("/api")
public class SystemBankCardGroupController {

    @Autowired
    private SystemBankCardGroupService systemBankCardGroupService;


    @GetMapping("/abc/simple")
    @NoAuthorization
    public CommResp getById() {
        return systemBankCardGroupService.currency();
    }

    //http://localhost:8088/api/pg100/simple?with=currency,model(选择账户群组)
    //http://localhost:8088/api/pg100/simple?with=model(取消使用，使用上面的接口返回数据)
    @GetMapping("/pg100/simple")
    @ApiOperation(value = "有关群组的一些选项列表查询接口")
    public List<BankGroupVo> getGroup(String with) {
        //返回的数据类型：{PG100_ID: 1, PG100_name: "DEV-THB", currency: "THB"}
        //里面牵涉表，system_bank_card_group
        if ("currency,model".equals(with)){
            return systemBankCardGroupService.getGroup();
        }
        return null;
    }

    //http://localhost:8088/api/pg100?
    // PG100_ID=1&
    // SH100_ID[]=152&SH100_ID[]=155&
    // BC100_ID[]=1&BC100_ID[]=2&BC100_ID[]=2&BC100_ID[]=3&
    // currency=THB&
    // account_code=&
    // model=0&(新数据已取消该字段)
    // rp=100&page=1
    @GetMapping("/pg100")
    @ApiOperation(value = "银行账户管理-账户群组-搜索")
    public MerchantResp getGroupAll(BankCardGroupDto bankCardGroupDto, HttpServletRequest request) {
        return systemBankCardGroupService.getGroupAll(bankCardGroupDto,request);
    }

    //http://localhost:8088/api/pg100
    @PostMapping("/pg100")
    @ApiOperation(value = "银行账户管理-账户群组-新增")
    public Map<String,BankGroupVo> createGroup(BankCardGroupDto bankCardGroupDto) {
        return systemBankCardGroupService.createGroup(bankCardGroupDto);
    }

    @GetMapping("/pg100/{id}")
    @ApiOperation(value = "银行账户管理-账户群组-详情")
    public Map<String,BankGroupVo> getGroupAll(@PathVariable("id") Long id) {
        return systemBankCardGroupService.getGroupOne(id);
    }

    //http://localhost:8088/api/pg100/5/sh100/all
    @GetMapping("/pg100/{id}/sh100/all")
    @ApiOperation(value = "银行账户管理-账户群组-详情-商户")
    public List<Map<String, List<MerchantVo>>> getMerchantByGroup(@PathVariable("id") Long id) {
        return systemBankCardGroupService.getMerchantByGroup(id);
    }

    //http://localhost:8088/api/pg100/5/sh100
    @PutMapping("/pg100/{id}/sh100")
    @ApiOperation(value = "银行账户管理-账户群组-详情-商户删除或新增")
    public Map<String,Boolean> deleteMerchant(BankCardGroupDto bankCardGroupDto) {
        return systemBankCardGroupService.deleteOrAddMerchant(bankCardGroupDto);
    }

    //http://localhost:8088/api/pg100/5/bc100/all?PG100_ID=5&type=1
    @GetMapping("/pg100/{id}/bc100/all")
    @ApiOperation(value = "银行账户管理-账户群组-详情-账户")
    public Map<String, List<BankCardAllVo>> getRecharge(SystemBankCardDto bankCardDto) {
        return systemBankCardGroupService.getRecharge(bankCardDto);
    }

    //http://localhost:8088/api/pg100/1/bc100
    @PutMapping("/pg100/{id}/bc100")
    @ApiOperation(value = "银行账户管理-账户群组-详情-账户删除或新增")
    public Map<String,Boolean> getMerchantByGroup(BankCardDto bankCardDto) {
        return systemBankCardGroupService.deleteOrAddAccount(bankCardDto);
    }
}
