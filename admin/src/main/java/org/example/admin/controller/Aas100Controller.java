package org.example.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.req.Aas100List;
import org.example.admin.service.Aas100Service;
import org.example.common.base.CommResp;
import org.example.common.entity.Aas100;
import org.example.common.validator.group.AddGroup;
import org.example.common.validator.group.ListGroup;
import org.example.common.validator.group.UpdateGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.Arrays;



@Api(tags = "安卓机管理")
@RestController
@RequestMapping("/aas")
public class Aas100Controller {
    @Resource
    private Aas100Service aas100Service;

    @ApiOperation(value = "列表")
//    @RequiresRoles("admin")
    @PostMapping("/page")
    public CommResp list(@Validated({ListGroup.class, Default.class}) @RequestBody Aas100List req){
        CommResp commResp = aas100Service.pageData(req);
        return commResp;
    }


    @ApiOperation(value = "保存")
    @PostMapping("/save")
    public CommResp save(@Validated(AddGroup.class) @RequestBody Aas100 entity){
		boolean save = aas100Service.save(entity);
        if(save){
            return CommResp.SUCCEE();
        }else{
            return CommResp.FAIL_SAVE();
        }

    }


    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public CommResp update(@Validated(UpdateGroup.class) @RequestBody Aas100 entity){
		boolean b = aas100Service.updateById(entity);
        if(b){
            return CommResp.SUCCEE();
        }else{
            return CommResp.FAIL_UPDATE();
        }
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public CommResp delete(@RequestBody Integer[] ids){
		boolean b = aas100Service.removeByIds(Arrays.asList(ids));
        if(b){
            return CommResp.SUCCEE();
        }else{
            return CommResp.FAIL_DELETE();
        }
    }

}
