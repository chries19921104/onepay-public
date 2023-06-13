package org.example.admin.controller;

import org.example.admin.service.ExternalStatementService;
import org.example.admin.service.SystemVirtualBankStatementService;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ExternalStatementController {

    @Autowired
    private ExternalStatementService statementService;

    @GetMapping("/oto")
    public MerchantResp getAll(Long bankCardId, String statement, String accountCode, String commandId,
                               LocalDateTime transactionStartData, LocalDateTime transactionEndData , String currency,
                               List<Integer> type, Long debit, Long credit, String description, LocalDateTime updaterStartData , LocalDateTime updaterEndData){
        MerchantResp all = statementService.getAll(bankCardId, statement, accountCode, commandId, transactionStartData,
                transactionEndData, currency, type, debit, credit, description, updaterStartData, updaterEndData);
        return all;
    }
}