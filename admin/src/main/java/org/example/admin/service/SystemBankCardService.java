package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.entity.SystemBankCard;
import org.example.common.vo.BankCardVo;

import java.util.List;

/**
* <p>
* system_bank_card Service 接口
* </p>
*
* @author Administrator
* @since 2023-05-25 13:43:38
*/
public interface SystemBankCardService extends IService<SystemBankCard> {

    //有关银行卡账户号码选项列表查询接口
    List<BankCardVo> getBranks();

}