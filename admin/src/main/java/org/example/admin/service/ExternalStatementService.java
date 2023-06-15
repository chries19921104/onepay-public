package org.example.admin.service;

import org.apache.ibatis.annotations.Param;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;

import java.time.LocalDateTime;
import java.util.List;

public interface ExternalStatementService {

    MerchantResp getAll(Long bankCardId , String statement , String accountCode, String commandId, LocalDateTime transactionStartData,
                        LocalDateTime transactionEndData, String currency, List<Integer> type, Long debit, Long credit,
                        String description, LocalDateTime updatedStartAt, LocalDateTime updatedEndAt);
}
