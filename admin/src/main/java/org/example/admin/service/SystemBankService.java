package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.entity.SystemBank;
import org.example.common.vo.BrankVo;

import java.util.List;

/**
* <p>
* system_bank Service 接口
* </p>
*
* @author Administrator
* @since 2023-05-25 11:00:08
*/
public interface SystemBankService extends IService<SystemBank> {

    /**
     * 银行信息
     * @param status
     * @return
     */
    List<BrankVo> getBranks(Integer status);

}