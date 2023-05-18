package org.example.common.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* <p>
* system_menu 实体类
* </p>
*
* @author Administrator
* @since 2023-05-11 16:30:47
*/
@Getter
@Setter
@TableName("system_menu")
public class SystemMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 菜单ID
    */
    @TableId
    private Integer id;

    /**
    * 父级id
    */
    private Integer pid;

    /**
    * 图标
    */
    private String icon;

    /**
    * 按钮名
    */
    private String menuName;

    /**
    * 模块名
    */
    private String module;

    /**
    * 控制器
    */
    private String controller;

    /**
    * 方法名
    */
    private String action;

    /**
    * api接口地址
    */
    private String apiUrl;

    /**
    * 提交方式POST GET PUT DELETE
    */
    private String methods;

    /**
    * 参数
    */
    private String params;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 是否为隐藏菜单0=隐藏菜单,1=显示菜单
    */
    private Integer isShow;

    /**
    * 是否为隐藏菜单供前台使用
    */
    private Integer isShowPath;

    /**
    * 子管理员是否可用
    */
    private Integer access;

    /**
    * 路由名称 前端使用
    */
    private String menuPath;

    /**
    * 路径
    */
    private String path;

    /**
    * 是否为菜单 1菜单 2功能
    */
    private Integer authType;

    /**
    * 顶部菜单标示
    */
    private String header;

    /**
    * 是否顶部菜单1是0否
    */
    private Integer isHeader;

    /**
    * 前台唯一标识
    */
    private String uniqueAuth;

    /**
    * 是否删除
    */
    private Integer isDel;


}
