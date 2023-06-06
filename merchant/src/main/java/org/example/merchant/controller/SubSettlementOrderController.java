package org.example.merchant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.exception.MsgException;
import org.example.admin.vo.R;
import org.example.merchant.dto.SubSettlementOrderSearchDTO;
import org.example.merchant.service.SystemSubSettlementOrderService;
import org.example.merchant.vo.SubSettlementOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "子下发api")
@RestController
@RequestMapping(value = "/test/oders")
public class SubSettlementOrderController extends BaseController {

    @Autowired
    private SystemSubSettlementOrderService systemSubSettlementOrderService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/search")
    public R search(SubSettlementOrderSearchDTO dto) throws MsgException {
        super.startPageHelper();
        List<SubSettlementOrderVO> list = this.systemSubSettlementOrderService.search(dto);
        return super.pageToPageVO(list);
    }


}
