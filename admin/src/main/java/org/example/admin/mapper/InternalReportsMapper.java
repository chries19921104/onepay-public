package org.example.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.common.dto.BankAccountListDto;
import org.example.common.vo.BankAccountListVo;

import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 16:56:38
 */

@Mapper
public interface InternalReportsMapper {


    List<BankAccountListVo> getBankAccountList(String currency,List<String> PG100_ID,String BK100_ID,List<String> type,List<String> status,List<String> mode,String account_code,String BC100_name);
}
