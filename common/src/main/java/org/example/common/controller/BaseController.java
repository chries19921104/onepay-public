package org.example.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.exception.MsgException;
import org.example.common.utils.ServletUtils;
import org.example.common.vo.PageVO;
import org.example.common.vo.R;

import java.util.List;


/**
 * 通用控制器
 */
public class BaseController {

    /**
     * 第页第三步，把Mybatis返回的结果IPage转换自定义的对象
     * @param page
     * @return
     */
    private static final String PAGE_NUM_NAME="page";

    private static final String PAGE_SIZE_NAME="size";

    /**
     * 启动pageHelper分页 PageHelper.startPage(1, 10);
     */
    protected void startPageHelper() throws MsgException {
        //当前第几页
        int page= ServletUtils.getParamsToInt(PAGE_NUM_NAME);
        //每页多少笔
        int size=ServletUtils.getParamsToInt(PAGE_SIZE_NAME);

        //使用pagehelper，开启mybatis分页拦截器
        PageHelper.startPage(page,size);
    }

    /**
     * 把pageHelper返回的Page对象转换成自已定义的对象
     * @param list
     * @return
     */
    protected R pageToPageVO(List list){
        PageVO pageVO=new PageVO();

        //mybatis分页插件
        if(list instanceof Page){
            Page  pg= (Page) list;
            pageVO.setTotal(pg.getTotal());
            pageVO.setRows(pg.getResult());
        }else{
            //普通的list
            pageVO.setRows(list);
            pageVO.setTotal(0L);
        }
        return  R.okHasData(pageVO);
    }

}
