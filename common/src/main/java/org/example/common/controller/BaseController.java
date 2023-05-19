package org.example.common.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.common.enums.AckCode;
import org.example.common.exception.MsgException;
import org.example.common.utils.ServletUtils;
import org.example.common.vo.PageVO;
import org.example.common.vo.R;

import java.util.List;


/**
 * 通用控制器
 */
public class BaseController {

    // 通用判断成功或者失败的返回模板
    protected R toBoolean(Boolean b){
        return b?R.ok():R.build(AckCode.FAIL);
    }

    protected R toInt(int i){
        return i>0?R.ok():R.build(AckCode.FAIL);
    }

}
