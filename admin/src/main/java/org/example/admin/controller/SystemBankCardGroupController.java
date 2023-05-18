package org.example.admin.controller;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemBankCardGroupService;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemBankCardGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;


/**
* <p>
* system_bank_card_group 控制器实现
* </p>
*
* @author zhangmi
* @since 2023-05-17 13:48:38
*/
@RestController
@RequestMapping("/api/abc")
public class SystemBankCardGroupController {

    @Autowired
    private SystemBankCardGroupService systemBankCardGroupService;


    @GetMapping("/simple")
    @NoAuthorization
    public CommResp getById() {
        return systemBankCardGroupService.currency();
    }
}
