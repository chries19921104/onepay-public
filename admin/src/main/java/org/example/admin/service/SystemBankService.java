package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.MerchantResp;
import org.example.admin.dto.BrankDto;
import org.example.common.entity.SystemBank;
import org.example.admin.vo.BrankVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    List<BrankVo> getBranksByStatus(Integer status);

    //银行账户管理-银行-查询
    MerchantResp getBrankByCurrency(BrankDto brankDto, HttpServletRequest request);

    //银行账户管理-银行-新增
    Map<String, BrankVo> createBank(BrankVo brankVo);

    //银行账户管理-银行-编辑
    Map<String, Boolean> updateBank(BrankVo brankVo);

    List<BrankVo> getBranksAll();
}