package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.SystemBankService;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BrankDto;
import org.example.admin.dto.ParamDto;
import org.example.admin.vo.BrankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* <p>
* system_bank 控制器实现
* </p>
*
* @author Administrator
* @since 2023-05-25 10:58:42
*/
@Api(tags = "银行控制器")
@RestController
@RequestMapping("/api")
public class SystemBankController {

    @Autowired
    private SystemBankService systemBankService;

    //http://localhost:8088/api/bk100/simple?status=1
    //http://localhost:8088/api/bk100/simple?with=currency,status
    @ApiOperation(value = "有关银行的一些选项列表查询接口")
    @GetMapping("/bk100/simple")
    public List<BrankVo> getBrank(ParamDto paramDto) {
        if (paramDto.getStatus() != null && paramDto.getWith().size() == 0){
            return systemBankService.getBranksByStatus(paramDto.getStatus());
        }else if (paramDto.getStatus() == null && paramDto.getWith().size() == 2){
            return systemBankService.getBranksAll();
        }else {
            return null;
        }
    }

    //http://localhost:8088/api/bk100?currency=THB&rp=100&page=1
    @ApiOperation(value = "银行账户管理-银行-查询")
    @GetMapping("/bk100")
    public MerchantResp getBrankByCurrency(BrankDto brankDto, HttpServletRequest request) {
        return systemBankService.getBrankByCurrency(brankDto,request);
    }

    //http://localhost:8088/api/bk100
    @ApiOperation(value = "银行账户管理-银行-新增")
    @PostMapping("/bk100")
    public Map<String , BrankVo> createBank(@RequestBody BrankVo brankVo) {
        return systemBankService.createBank(brankVo);
    }

    //http://localhost:8088/api/bk100/41
    @ApiOperation(value = "银行账户管理-银行-编辑")
    @PutMapping("/bk100/{id}")
    public Map<String , Boolean> updateBank(@RequestBody BrankVo brankVo) {
        return systemBankService.updateBank(brankVo);
    }




}
