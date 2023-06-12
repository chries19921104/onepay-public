package org.example.admin.service.impl;

import org.example.admin.mapper.ExternalStatementMapper;
import org.example.admin.mapper.SystemVirtualBankStatementMapper;
import org.example.admin.service.ExternalStatementService;
import org.example.admin.service.SystemVirtualBankStatementService;
import org.example.admin.vo.ExternalStatementVo;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExternalStatementServiceImpl implements ExternalStatementService {

    @Autowired
    private ExternalStatementMapper externalStatementMapper;

    @Override
    public MerchantResp getAll(Long bankCardId, String statement, String accountCode, String commandId,
                               LocalDateTime transactionStartData, LocalDateTime transactionEndData,String currency,
                               List<Integer> type, Long debit, Long credit, String description, LocalDateTime updatedStartAt ,
                               LocalDateTime updatedEndAt) {
        List<ExternalStatementVo> all = externalStatementMapper.getAll(bankCardId, statement,
                accountCode, commandId, transactionStartData ,transactionEndData, currency, type, debit, credit,
                description, updatedStartAt,updatedEndAt);
        MerchantResp merchantResp = new MerchantResp();
        merchantResp.setData(all);
        return merchantResp;
    }
}
