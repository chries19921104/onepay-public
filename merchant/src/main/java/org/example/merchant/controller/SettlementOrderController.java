package org.example.merchant.controller;

import io.swagger.annotations.Api;
import org.example.merchant.service.SystemSettlementOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "下发管理")
@RestController
@RequestMapping(value = "/test/order/")
public class SettlementOrderController {

    @Autowired
    private SystemSettlementOrderService systemSettlementOrderService;

//    @ApiOperation(value = "分页查询")
//    @GetMapping(value = "search")
//    public R search(SettlementOrderSearchDTO dto) throws MsgException {
//        super.startPageHelper();
//        List<SettlementOrderVO> list = this.systemSettlementOrderService.search(dto);
//        return super.pageToPageVO(list);
//    }
}
