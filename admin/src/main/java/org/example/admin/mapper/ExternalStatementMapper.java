package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.admin.vo.ExternalStatementVo;
import org.example.common.base.MerchantResp;
import org.example.common.entity.SystemVirtualBankStatement;

import java.time.LocalDateTime;
import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ExternalStatementMapper extends BaseMapper<SystemVirtualBankStatement> {

     List<ExternalStatementVo> getAll(@Param("BK100_ID") Long bankCardId ,
                                               @Param("VB100_ID") String statement ,
                                               @Param("account_code") String accountCode,
                                               @Param("command_id") String commandId,
//                                             @Param("account_no") String accountNo,
                                               @Param("transaction_start_date")LocalDateTime transactionStartDate,
                                               @Param("transaction_end_date")LocalDateTime transactionEndDate,
                                               @Param("currency")String currency,
                                               @Param("type") List<Integer> type,
                                               @Param("debit")Long debit,
                                               @Param("credit")Long credit,
                                               @Param("description")String description,
                                               @Param("updated_start_date")LocalDateTime updatedStartAt,
                                               @Param("updated_end_date")LocalDateTime updatedEndAt);
}
