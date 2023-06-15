package org.example.admin.controller;

import org.example.admin.dto.ExternalStatementDto;
import org.example.admin.service.ExternalStatementService;
import org.example.admin.service.SystemVirtualBankStatementService;
import org.example.admin.vo.ExternalStatementVo;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/oto")
@RestController
public class ExternalStatementController {

    @Autowired
    private ExternalStatementService statementService;

    @GetMapping("/{bankCardId}/{statement}/{accountCode}/{commandId}/{transactionStartData}/{transactionEndData}/{currency}/{type}/{debit}/{credit}/{description}/{updaterStartData}/{updaterEndData}")
    public MerchantResp getAll(@PathVariable Long bankCardId,@PathVariable String statement,
                               @PathVariable String accountCode,@PathVariable String commandId,
                               @PathVariable LocalDateTime transactionStartData,@PathVariable LocalDateTime transactionEndData ,
                               @PathVariable String currency,@PathVariable List<Integer> type,@PathVariable Long debit,
                               @PathVariable Long credit,@PathVariable String description,@PathVariable LocalDateTime updaterStartData ,
                               @PathVariable LocalDateTime updaterEndData){
        MerchantResp all = statementService.getAll(bankCardId, statement, accountCode, commandId, transactionStartData,
                transactionEndData, currency, type, debit, credit, description, updaterStartData, updaterEndData);
        System.out.println("111");
        return all;
    }

}