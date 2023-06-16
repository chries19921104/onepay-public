package com.example.admin.mapper;

import org.example.admin.mapper.SystemWithdrawalOrderMapper;
import org.example.admin.dto.WithdrawalOrderDto;
import org.example.admin.vo.WithdrawalOrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SystemWithdrawalOrderExternalStatementMapperTests {

    @Autowired
    private SystemWithdrawalOrderMapper systemWithdrawalOrderMapper;

    @Test
    void testSelectDepositOrderVo() {
        WithdrawalOrderDto order = new WithdrawalOrderDto();
        order.setCurrency("THB");
        order.setRp(10);
        order.setPg100Id(1L);
        ArrayList<Integer> sh100Ids = new ArrayList<>();
        sh100Ids.add(1);
        sh100Ids.add(2);
        sh100Ids.add(3);
        order.setSh100Ids(sh100Ids);
        order.setBk100Id(36L);
        ArrayList<Integer> statuses = new ArrayList<>();
        statuses.add(3);
        statuses.add(5);
        statuses.add(6);
        order.setStatuses(statuses);
        order.setConfirmAccname(1);
        order.setOn100Status(0);
//        order.setCompletedStartTime(LocalDateTime.now());
//        order.setCompletedEndTime(LocalDateTime.now());
        order.setFrom("1WOElsINu8");
        order.setTo("CARD001");
        order.setAltId("W-25082020001");
        Long altId = Long.parseLong("W-25082020001".substring(8));
        Long subFoAltId = Long.parseLong("W-25082010002".substring(8));
        order.setReference("REF001");

        List<WithdrawalOrderVo> withdrawalOrderVo = systemWithdrawalOrderMapper.selectWithdrawalOrderVo(order,
                altId,
                subFoAltId,
                new BigDecimal(1),
                new BigDecimal(1000),
                new BigDecimal(1),
                new BigDecimal(1000));
        Integer total = systemWithdrawalOrderMapper.selectTotal(order,
                altId,
                subFoAltId,
                new BigDecimal(1),
                new BigDecimal(1000),
                new BigDecimal(1),
                new BigDecimal(1000));
    }
}
