package org.example.admin.service;

import org.example.common.base.CommResp;
import org.example.common.dto.BankAccountListDto;

import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 16:15:56
 */
public interface InternalReportsService {


    CommResp getBankAccountList(String currency,List<String> PG100_ID,String BK100_ID,List<String> type,List<String> status,List<String> mode,String account_code,String BC100_name);
}
