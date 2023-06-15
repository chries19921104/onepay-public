package org.example.merchant.controller;

import io.swagger.annotations.Api;
import org.example.merchant.service.SystemSubSettlementOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "子下发api")
@RestController
@RequestMapping(value = "/test/oders")
public class SubSettlementOrderController {

    @Autowired
    private SystemSubSettlementOrderService systemSubSettlementOrderService;

//    @ApiOperation(value = "分页查询")
//    @GetMapping(value = "/search")
//    public R search(SubSettlementOrderSearchDTO dto) throws MsgException {
//        super.startPageHelper();
//        List<SubSettlementOrderVO> list = this.systemSubSettlementOrderService.search(dto);
//        return super.pageToPageVO(list);
//    }


}
