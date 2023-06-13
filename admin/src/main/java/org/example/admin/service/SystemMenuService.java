package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemMenu;
import org.springframework.stereotype.Service;


/**
* <p>
* system_menu ExternalStatementService 接口
* </p>
*
* @author Administrator
* @since 2023-05-11 16:34:34
*/
@Service
public interface SystemMenuService extends IService<SystemMenu> {

    /**
     * 菜单分页列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public CommResp listPage(Integer pageNum, Integer pageSize);

    /**
     * 菜单删除（逻辑删除）
     * @param id
     * @return
     */
    public CommResp deleteById(Integer id);

    /**
     * 菜单新增
     * @param systemMenu
     * @return
     */
    public CommResp create(SystemMenu systemMenu);

    /**
     * 菜单修改
     * @param systemMenu
     * @return
     */
    public CommResp update(SystemMenu systemMenu);

    /**
     *查询单个菜单（编辑菜单回显）
     * @param id
     * @return
     */
    public CommResp getId(Integer id);

    /**
     *获取所有的父级名
     * @return
     */
    public CommResp getPidName();

    /**
     * 获取父级下所有的子菜单
     * @param id
     * @return
     */
    public CommResp getIdName(Integer id);
}