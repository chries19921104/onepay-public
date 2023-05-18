package org.example.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.example.common.controller.BaseController;
import org.example.common.dto.SystemAdjustOrdersSearchDTO;
import org.example.admin.service.SystemAdjustOrdersService;
import org.example.common.vo.R;
import org.example.common.vo.SystemAdjustOrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "调整订单api")
@RestController
@RequestMapping(value = "/test/Adjust")
public class SystemAdjustOrdersController extends BaseController {

    @Autowired
    private SystemAdjustOrdersService systemAdjustOrdersService;

    @SneakyThrows
    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/search")
    public R search(SystemAdjustOrdersSearchDTO dto){
        super.startPageHelper();
        List<SystemAdjustOrdersVO> list= this.systemAdjustOrdersService.search(dto);
        return super.pageToPageVO(list);
    }


}
