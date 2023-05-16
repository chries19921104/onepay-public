package org.example.merchant.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.example.common.base.CommResp;

import org.example.common.validator.group.ListGroup;

import org.example.merchant.req.Aas100List;
import org.example.merchant.service.Aas100ServiceA;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.groups.Default;


@Api(tags = "安卓机管理")
@RestController
//@RequestMapping("/aas")
public class Aas100Controller {
    @Resource
    private Aas100ServiceA aas100ServiceA;

    @ApiOperation(value = "列表")
    @PostMapping("/page")
    public CommResp list(@Validated({ListGroup.class, Default.class}) @RequestBody Aas100List req){
        CommResp commResp = aas100ServiceA.pageData(req);
        CommResp eqweq = new CommResp().FAIL("eqweq");


        return eqweq;
    }


//    @ApiOperation(value = "保存")
//    @PostMapping("/save")
//    public CommResp save(@Validated(AddGroup.class) @RequestBody Aas100 entity){
//		boolean save = aas100Service.save(entity);
//        if(save){
//            return CommResp.SUCCEE();
//        }else{
//            return CommResp.FAIL("保存失败");
//        }
//
//    }
//
//
//    @ApiOperation(value = "修改")
//    @PostMapping("/update")
//    public CommResp update(@Validated(UpdateGroup.class) @RequestBody Aas100 entity){
//		boolean b = aas100Service.updateById(entity);
//        if(b){
//            return CommResp.SUCCEE();
//        }else{
//            return CommResp.FAIL("修改失败");
//        }
//    }
//
//    @ApiOperation(value = "删除")
//    @PostMapping("/delete")
//    public CommResp delete(@RequestBody Integer[] ids){
//		boolean b = aas100Service.removeByIds(Arrays.asList(ids));
//        if(b){
//            return CommResp.SUCCEE();
//        }else{
//            return CommResp.FAIL("删除失败");
//        }
//    }

}
