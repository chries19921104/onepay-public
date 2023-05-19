package org.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemSettlementOrderService;
import org.example.common.controller.BaseController;
import org.example.common.vo.R;
import org.example.common.dto.SettlementOrderSearchDTO;
import org.example.common.vo.SettlementOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(tags = "下发管理")
@RestController
@RequestMapping(value = "/api/order/")
public class SettlementOrderController extends BaseController {

    @Autowired
    private SystemSettlementOrderService systemSettlementOrderService;

    @SneakyThrows
    @ApiOperation(value = "分页查询")
    @GetMapping(value = "search")
    @NoAuthorization
    public R search(SettlementOrderSearchDTO dto) {

        Page<SettlementOrderVO> list=this.systemSettlementOrderService.search(new Page<SettlementOrderVO>(dto.getPage(),dto.getSize()),dto);

        return R.okHasData(list);
    }
}
