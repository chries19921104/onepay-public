package org.example.admin.service;

import org.example.admin.dto.BankAccountListDto;
import org.example.admin.dto.InternalTransferDto;
import org.example.admin.dto.TransactionScreenRecordsDto;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 16:15:56
 */
public interface InternalReportsService {

    MerchantResp getBankAccountList(HttpServletRequest request, BankAccountListDto bankAccountListDto);

    MerchantResp getTransactionScreenRecords(HttpServletRequest request, TransactionScreenRecordsDto transactionScreenRecordsDto);

    CommResp getApprovedCardReport(String number);

    MerchantResp getInternalTransfer(HttpServletRequest request, InternalTransferDto internalTransferDto);
}
