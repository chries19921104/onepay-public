package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.SystemBankService;
import org.example.common.vo.BrankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/system/bank")
public class SystemBankController {

    @Autowired
    private SystemBankService systemBankService;

    //http://localhost:8088/api/bk100/simple?status=1
    @ApiOperation(value = "有关银行的一些选项列表查询接口")
    @GetMapping("/bk100/simple")

    public List<BrankVo> getBrank(@RequestParam("status") Integer status) {
        return systemBankService.getBranks(status);
    }
}
