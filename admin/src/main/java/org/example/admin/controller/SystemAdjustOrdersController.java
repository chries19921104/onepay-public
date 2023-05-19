package org.example.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.example.common.controller.BaseController;
import org.example.common.dto.SystemAdjustOrdersDTO;
import org.example.common.dto.SystemAdjustOrdersSearchDTO;
import org.example.admin.service.SystemAdjustOrdersService;
import org.example.common.entity.SysUserEntity;
import org.example.common.entity.SystemAdjustOrders;
import org.example.common.enums.AckCode;
import org.example.common.vo.R;
import org.example.common.vo.SystemAdjustOrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Api(tags = "调整订单api")
@RestController
@RequestMapping(value = "/test/Adjust")
public class SystemAdjustOrdersController extends BaseController {


    @Autowired
    private SystemAdjustOrdersService systemAdjustOrdersService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/search")
    public R search(@Validated SystemAdjustOrdersSearchDTO dto){

        Page<SystemAdjustOrdersVO> list = this.systemAdjustOrdersService.search(new Page<>(dto.getPage(),dto.getSize()),dto);

        return R.okHasData(list);
    }


    @ApiOperation(value = "新增或者修改")
    @PostMapping(value = "/addOrUpdate")
    public R add(@Validated @RequestBody SystemAdjustOrdersDTO dto) {

        // 根据shiro 获取登陆用户信息
        SysUserEntity db = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();

        //java单机版  后续使用分布式锁
        Lock lock=new ReentrantLock();

        try {
            boolean result = lock.tryLock(3, TimeUnit.SECONDS);
            if (result) {
                // 拷贝对象
                SystemAdjustOrders orders = new SystemAdjustOrders();
                BeanUtil.copyProperties(dto,orders);

                // 后续需要添加更新者，修改人
                if (dto.getId()!=null){ // 修改，添加更新时间
                    orders.setUpdatedAt(new Date());
                    // 暂时固定
                    orders.setUpdater("admin");
                }else{
                    orders.setCreatedAt(new Date());
                    orders.setCreator("admin");
                }
                boolean b = this.systemAdjustOrdersService.saveOrUpdate(orders);
                return super.toBoolean(b);

            } else {
                return R.build(AckCode.COMMON_FRE_OPERATION);
            }

        } catch (InterruptedException e) {
            return R.build(AckCode.FAIL);
        } finally {
            lock.unlock();
        }
    }


}
