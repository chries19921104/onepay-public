package org.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.SystemSubSettlementOrderService;
import org.example.common.controller.BaseController;
import org.example.common.vo.R;
import org.example.common.dto.SubSettlementOrderSearchDTO;
import org.example.common.vo.SubSettlementOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "子下发api")
@RestController
@RequestMapping(value = "/api/oders")
public class SubSettlementOrderController extends BaseController {

    @Autowired
    private SystemSubSettlementOrderService systemSubSettlementOrderService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/search")
    public R search(SubSettlementOrderSearchDTO dto) {
        Page<SubSettlementOrderVO> list = this.systemSubSettlementOrderService.search(new Page<SubSettlementOrderVO>(dto.getPage(),dto.getSize()),dto);
        return R.okHasData(list);
    }


}
