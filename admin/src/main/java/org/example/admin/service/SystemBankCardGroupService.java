package org.example.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemBankCardGroup;
import org.springframework.stereotype.Service;


/**
* <p>
* system_bank_card_group Service 接口
* </p>
*
* @author zhangmi
* @since 2023-05-17 13:44:10
*/
@Service
public interface SystemBankCardGroupService extends IService<SystemBankCardGroup> {

    CommResp currency();

}