package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.example.admin.service.SystemSubSettlementOrderService;
import org.example.common.controller.BaseController;
import org.example.common.exception.MsgException;
import org.example.common.vo.R;
import org.example.common.dto.SubSettlementOrderSearchDTO;
import org.example.common.vo.SubSettlementOrderVO;
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

    @SneakyThrows
    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/search")
    public R search(SubSettlementOrderSearchDTO dto) {
        super.startPageHelper();
        List<SubSettlementOrderVO> list = this.systemSubSettlementOrderService.search(dto);
        return super.pageToPageVO(list);
    }


}
