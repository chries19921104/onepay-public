package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.SystemMenuMapper;
import org.example.admin.service.SystemMenuService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
* system_menu ExternalStatementService 接口实现
* </p>
*
* @author Administrator
* @since 2023-05-11 16:34:56
*/
@Service
@Transactional
@Slf4j
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {

    /**
     * 菜单分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public CommResp listPage(Integer pageNum, Integer pageSize) {
        Page<SystemMenu> page = new Page<>();
        //将is_del状态为1的数据进行一个过滤，不查找；
        LambdaQueryWrapper<SystemMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemMenu::getIsDel,0).eq(SystemMenu::getPid,0);
        this.page(page,lqw);
        return CommResp.data(page);
    }

    /**
     * 菜单删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public CommResp deleteById(Integer id) {
        //先判断传过来的id是否为主菜单
        SystemMenu byId = this.getById(id);
        if (byId.getPid()!= null && byId.getPid() == 0){
            //为主菜单，如果删除需要删除包括他下面的所有子菜单
            LambdaQueryWrapper<SystemMenu> lqw = new LambdaQueryWrapper<>();
            lqw.eq(SystemMenu::getPid,id).eq(SystemMenu::getIsDel,0);
            List<SystemMenu> list = this.list(lqw);
            List<SystemMenu> collect = list.stream().map(item -> {
                item.setIsDel(1);
                return item;
            }).collect(Collectors.toList());
            //删除子级菜单
            for (SystemMenu systemMenu : collect) {
                this.updateById(systemMenu);
            }
            //删除本身
            byId.setIsDel(1);
            this.updateById(byId);
            return CommResp.SUCCEE();
        }
        //为子菜单，删除对应的菜单即可
        byId.setIsDel(1);
        this.updateById(byId);
        return CommResp.SUCCEE();
    }

    /**
     * 菜单新增
     * @param systemMenu
     * @return
     */
    @Override
    public CommResp create(SystemMenu systemMenu) {
        this.save(systemMenu);
        return CommResp.SUCCEE();
    }

    /**
     * 菜单修改
     * @param systemMenu
     * @return
     */
    @Override
    public CommResp update(SystemMenu systemMenu) {
        this.updateById(systemMenu);
        return CommResp.SUCCEE();
    }

    /**
     * 查询单个菜单（编辑菜单回显）
     * @param id
     * @return
     */
    @Override
    public CommResp getId(Integer id) {
        SystemMenu byId = this.getById(id);
        if (byId.getIsDel() != null && byId.getIsDel() == 0){
            return CommResp.data(byId);
        }
        return CommResp.FAIL();
    }

    /**
     * 获取所有的父级名
     * @return
     */
    @Override
    public CommResp getPidName() {
        LambdaQueryWrapper<SystemMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemMenu::getPid, 0).eq(SystemMenu::getIsDel,0);
        List<SystemMenu> list = this.list(lqw);
        List<String> collect = list.stream().map(item -> item.getMenuName()).collect(Collectors.toList());
        return CommResp.data(collect);
    }

    /**
     * 获取父级下所有的子菜单
     * @param id
     * @return
     */
    @Override
    public CommResp getIdName(Integer id) {
        LambdaQueryWrapper<SystemMenu> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SystemMenu::getPid,id).eq(SystemMenu::getIsDel,0);
        List<SystemMenu> list = this.list(lqw);
        return CommResp.data(list);
    }
}