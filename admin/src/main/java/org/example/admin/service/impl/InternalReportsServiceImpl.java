package org.example.admin.service.impl;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.BankAccountListDto;
import org.example.admin.dto.InternalTransferDto;
import org.example.admin.dto.TransactionScreenRecordsDto;
import org.example.admin.mapper.*;
import org.example.admin.req.Transaction;
import org.example.admin.service.InternalReportsService;
import org.example.admin.vo.BankAccountListVo;
import org.example.admin.vo.InternalTransferVo;
import org.example.common.base.CommResp;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.common.entity.SystemApprovedAccountReport;
import org.example.common.entity.SystemLogLastPage;
import org.example.common.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Txd
 * @since 2023-06-06 16:15:56
 */
@Service
@Transactional
@Slf4j
public class InternalReportsServiceImpl implements InternalReportsService {

    @Autowired
    private InternalReportsMapper internalReportsMapper;
    @Resource
    private SystemSelectOptionConfigMapper systemSelectOptionConfigMapper;
    @Resource
    private SystemLogLastPageMapper systemLogLastPageMapper;
    @Resource
    private SystemApprovedAccountReportMapper systemApprovedAccountReportMapper;
    @Resource
    private SystemMerchantMapper systemMerchantMapper;
    @Resource
    private SystemIntroversionOrderMapper systemIntroversionOrderMapper;



    @Override
    public MerchantResp getBankAccountList(HttpServletRequest request, BankAccountListDto bankAccountListDto) {
        //当前页
        int page = bankAccountListDto.getPage();

        bankAccountListDto.setPage((bankAccountListDto.getPage() - 1) * bankAccountListDto.getRp());

        List<BankAccountListVo> bankAccountList = internalReportsMapper.getBankAccountList(bankAccountListDto);

        Page<BankAccountListVo> bankAccountListVoPage = new Page<>(page,bankAccountListDto.getRp(),
                (((List<Integer>)bankAccountList.get(1)).get(0)));
        bankAccountListVoPage.setRecords((List<BankAccountListVo>)bankAccountList.get(0));

//        for (BankAccountListVo bankAccountListVo: bankAccountList) {
//            SystemSelectOptionConfig bc100Type = systemSelectOptionConfigMapper.selectOne(new LambdaQueryWrapper<SystemSelectOptionConfig>()
//                    .eq(SystemSelectOptionConfig::getType, "BC100_type")
//                    .eq(SystemSelectOptionConfig::getNum, bankAccountListVo.getBC100_type()));
//            if (bc100Type.getContent() != null && bc100Type.getContent().length() != 0) {
//                bankAccountListVo.setBC100_type_PG100_name(bankAccountListVo.getBC100_type());
//            } else {
//                bankAccountListVo.setBC100_type_PG100_name(bc100Type.getContent());
//            }
//
//        }

        if (bankAccountListVoPage.getRecords() != null && bankAccountListVoPage.getRecords().size() != 0){
            //其他信息
            return getMerchantResp(bankAccountListVoPage,null,request,bankAccountListDto.getRp());
        }
        return GetNoResp.getNoBankAccountListResp(request,bankAccountListDto.getRp());
    }


    @Override
    public MerchantResp getInternalTransfer(HttpServletRequest request, InternalTransferDto internalTransferDto) {
        if (internalTransferDto.getCompleted_start_time() != null){
            List<Integer> status = new ArrayList<>(Transaction.STATUS_COMPLETED);
            internalTransferDto.setStatus(status);
        }
        List<InternalTransferVo> internalTransferVos = systemIntroversionOrderMapper.getInternalTransfer(internalTransferDto);

        return null;
    }

    @Override
    public MerchantResp getTransactionScreenRecords(HttpServletRequest request, TransactionScreenRecordsDto transactionScreenRecordsDto) {
        SystemLogLastPage systemLogLastPage = systemLogLastPageMapper.selectOne(new LambdaQueryWrapper<SystemLogLastPage>()
                .eq(SystemLogLastPage::getCommandId, transactionScreenRecordsDto.getArt_id()));

        Page<SystemLogLastPage> systemLogLastPagePage = new Page<>(transactionScreenRecordsDto.getPage(),transactionScreenRecordsDto.getRp(),
                systemLogLastPage == null ? 0 : 1);
        systemLogLastPagePage.setRecords((List<SystemLogLastPage>) systemLogLastPage);

        if (systemLogLastPage != null){
            //其他信息
            return getMerchantResp(systemLogLastPagePage,null,request,transactionScreenRecordsDto.getRp());
        }
        return GetNoResp.getTransactionScreenRecordsResp(request,transactionScreenRecordsDto.getRp());
    }


    @Override
    public CommResp getApprovedCardReport(String number) {

        String partition = number.substring(number.length()-2);

        List<SystemApprovedAccountReport> systemApprovedAccountReports = systemApprovedAccountReportMapper.selectList(new LambdaQueryWrapper<SystemApprovedAccountReport>()
                .eq(SystemApprovedAccountReport::getPartition, partition)
                .eq(SystemApprovedAccountReport::getNumber, number));

        HashSet<Integer> set = new HashSet<>();
        for (SystemApprovedAccountReport systemApprovedAccountReport:systemApprovedAccountReports) {
            set.add(systemApprovedAccountReport.getMerchantId());
            if (!StringUtil.isNullOrEmpty(systemApprovedAccountReport.getMatchedNames())){
                JSONArray objects = new JSONArray(systemApprovedAccountReport.getMatchedNames());
                systemApprovedAccountReport.setMatchedNamesJson(objects);
            }
        }


        Map<Integer,String> systemMerchants = systemMerchantMapper.selectListMap(set);

        for (SystemApprovedAccountReport systemApprovedAccountReport:systemApprovedAccountReports) {
            systemApprovedAccountReport.setMerchantName(systemMerchants.get(systemApprovedAccountReport.getMerchantId()));
        }


        return CommResp.data(systemApprovedAccountReports);
    }


    private MerchantResp getMerchantResp(Page bankAccountListVoPage, Totals totals, HttpServletRequest request, Integer rp) {
        MerchantResp merchantResp = new MerchantResp();
        //
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + bankAccountListVoPage.getPages());
        if (bankAccountListVoPage.getPages() > bankAccountListVoPage.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (bankAccountListVoPage.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) bankAccountListVoPage.getCurrent());
        merchantResp.setData(bankAccountListVoPage.getRecords());
        if (bankAccountListVoPage.getTotal() != 0){
            merchantResp.setFrom((int) bankAccountListVoPage.getCurrent());
        }
        merchantResp.setLast_page((int) bankAccountListVoPage.getPages());
        merchantResp.setPer_page(rp+ "");
        merchantResp.setPrev_page_url(null);
        merchantResp.setSubtotals(totals);
        if (bankAccountListVoPage.getTotal() != 0){
            merchantResp.setTo((int) bankAccountListVoPage.getTotal());
        }
        merchantResp.setTotal((int) bankAccountListVoPage.getTotal());
        merchantResp.setTotals(totals);
        return merchantResp;
    }
}
