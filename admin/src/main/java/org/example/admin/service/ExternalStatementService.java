package org.example.admin.service;

import org.apache.ibatis.annotations.Param;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;

import java.time.LocalDateTime;
import java.util.List;

public interface ExternalStatementService {

    MerchantResp getAll(@Param("BK100_ID") Long bankCardId ,
                        @Param("VB100_ID") String statement ,
                        @Param("account_code") String accountCode,
                        @Param("command_id") String commandId,
//                   @Param("account_no") String accountNo,
                        @Param("transaction_start_date")LocalDateTime transactionStartData,
                        @Param("transaction_end_date")LocalDateTime transactionEndData,
                        @Param("currency")String currency,
                        @Param("type") List<Integer> type,
                        @Param("debit")Long debit,
                        @Param("credit")Long credit,
                        @Param("description")String description,
                        @Param("updated_start_date")LocalDateTime updatedStartAt,
                        @Param("updated_end_date")LocalDateTime updatedEndAt);
}
