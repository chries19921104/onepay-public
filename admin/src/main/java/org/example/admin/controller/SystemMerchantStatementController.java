package org.example.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.controller.BaseController;
import org.example.common.dto.SystemMerchantStatementSearchDTO;
import org.example.admin.service.SystemMerchantStatementService;
import org.example.common.vo.R;
import org.example.common.vo.SystemMerchantStatementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商户对账单")
@RestController
@RequestMapping(value = "/test/statement")
public class SystemMerchantStatementController extends BaseController {

    @Autowired
    private SystemMerchantStatementService systemMerchantStatementService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/search")
    public R search(@Validated SystemMerchantStatementSearchDTO dto){

        Page<SystemMerchantStatementVO> list=this.systemMerchantStatementService.search(new Page<SystemMerchantStatementVO>(dto.getPage(),dto.getSize()),dto);
        return R.okHasData(list);

    }
}
