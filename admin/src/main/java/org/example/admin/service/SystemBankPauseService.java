package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.admin.dto.BankPauseDto;
import org.example.common.entity.SystemBankPause;
import org.example.admin.vo.BankPauseVo;

import java.util.List;
import java.util.Map;

/**
* <p>
* system_bank_pause Service 接口
* </p>
*
* @author Administrator
* @since 2023-06-01 12:49:58
*/
public interface SystemBankPauseService extends IService<SystemBankPause> {

    //银行账户管理-暂停时间表设定-搜索
    Map<String, List<BankPauseVo>> getBrankPause(BankPauseDto brankDto);

    //银行账户管理-暂停时间表设定-新增
    Map<String, BankPauseVo> createBrankPause(BankPauseDto brankDto);

    //银行账户管理-暂停时间表设定-删除
    Map<String, Boolean> deleteBankPause(Integer id);

    //银行账户管理-暂停时间表设定-状态
    Map<String, Boolean> updateBankPause(BankPauseDto brankDto);
}