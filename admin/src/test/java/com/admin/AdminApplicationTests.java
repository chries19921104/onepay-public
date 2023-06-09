package com.admin;

import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.SystemAgentsService;
import org.example.admin.service.impl.SystemAgentsServiceImpl;
import org.example.admin.vo.DepositQRLossCommissionVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    private SystemDepositOrderMapper systemDepositOrderMapper;

    @Test
    void contextLoads() {

            HashMap<String, DepositQRLossCommissionVo> hs= new HashMap<>();
            String[] currency=new String[]{"THB", "kVND", "kIDR", "CNY"};
            String[] table=new String[]{"system_withdrawal_order","system_sub_settlement_order"};
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < currency.length; j++) {
                    DepositQRLossCommissionVo vo = systemDepositOrderMapper.processingFoAmount(currency[j], table[i]);
                    hs.put(currency[i],vo);

            }
        }
    }


}
