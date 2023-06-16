package org.example.admin.service.impl;

import cn.hutool.core.util.StrUtil;
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
import org.example.admin.vo.AgentsVo;
import org.example.admin.vo.BankAccountListVo;
import org.example.admin.vo.InternalTransferVo;
import org.example.common.base.CommResp;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.common.entity.SystemAgents;
import org.example.common.entity.SystemApprovedAccountReport;
import org.example.common.entity.SystemIntroversionOrder;
import org.example.common.entity.SystemLogLastPage;
import org.example.common.utils.URLUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        // 当前页
        int page = bankAccountListDto.getPage();
        // 每页条数
        bankAccountListDto.setPage((bankAccountListDto.getPage() - 1) * bankAccountListDto.getRp());
        // 查询得到大部分数据
        List<BankAccountListVo> bankAccountList = internalReportsMapper.getBankAccountList(bankAccountListDto);
        // 分页参数
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
        // 查询数据不为空
        if (bankAccountListVoPage.getRecords() != null && bankAccountListVoPage.getRecords().size() != 0){
            //其他信息
            return getMerchantResp(bankAccountListVoPage,null,request,bankAccountListDto.getRp());
        }
        // 查询数据为空
        return GetNoResp.getNoBankAccountListResp(request,bankAccountListDto.getRp());
    }


    @Override
    public MerchantResp getInternalTransfer(HttpServletRequest request, InternalTransferDto internalTransferDto) {
        // 判断 getCompleted_start_time() 不为空，给status赋值
        if (internalTransferDto.getCompleted_start_time() != null){
            List<Integer> status = new ArrayList<>(Transaction.STATUS_COMPLETED);
            internalTransferDto.setStatus(status);
        }

//        List<InternalTransferVo> internalTransferVos = systemIntroversionOrderMapper.getInternalTransfer(internalTransferDto);
//        // 分页参数
//        Page<InternalTransferVo> internalTransferVoPage = new Page<>(page,internalTransferDto.getRp(),
//                (((List<Integer>)internalTransferVos.get(1)).get(0)));
//        internalTransferVoPage.setRecords((List<InternalTransferVo>)internalTransferVos.get(0));


        LambdaQueryWrapper<SystemIntroversionOrder> lam = new LambdaQueryWrapper<>();

        // 判断查询所需要的条件
        if (internalTransferDto.getCurrency() != null){
            lam.eq(SystemIntroversionOrder::getCurrency,internalTransferDto.getCurrency());
        }
        if (internalTransferDto.getStart_date() != null){
            lam.ge(SystemIntroversionOrder::getCreatedAt,internalTransferDto.getStart_date());
        }
        if (internalTransferDto.getEnd_date() != null){
            lam.le(SystemIntroversionOrder::getCreatedAt,internalTransferDto.getEnd_date());
        }
        if (internalTransferDto.getBK100_ID() != null){
            lam.and(wrapper -> wrapper.exists("select * from `system_bank_card` where `system_introversion_order`.`from_bank_card_id` = `system_bank_card`.`card_id` and\n" +
                            "   exists ( select * from `system_bank` where `system_bank_card`.`bank_id` = `system_bank`.`bank_id` and `bank_id` = 3 )")
               .or()
               .exists("select * from `system_bank_card` where `system_introversion_order`.`to_bank_card_id` = `system_bank_card`.`card_id` and\n" +
                            "   exists ( select * from `system_bank` where `system_bank_card`.`bank_id` = `system_bank`.`bank_id` and `bank_id` = 3 )"));
        }
        if (internalTransferDto.getFrom() != null){
            lam.exists(MessageFormat.format("select * from `system_bank_card` where `system_introversion_order`.`from_bank_card_id` = `system_bank_card`.`card_id`" +
                    "and `account_code` like %{0}%",internalTransferDto.getFrom()));
        }
        if (internalTransferDto.getTo() != null){
            lam.exists(MessageFormat.format("select * from `system_bank_card` where `system_introversion_order`.`to_bank_card_id` = `system_bank_card`.`card_id`" +
                    "and `account_code` like %{0}%",internalTransferDto.getTo()));
        }
        if (internalTransferDto.getUpdated_start_date() != null){
            lam.ge(SystemIntroversionOrder::getUpdatedAt,internalTransferDto.getUpdated_start_date());
        }
        if (internalTransferDto.getUpdated_end_date() != null){
            lam.le(SystemIntroversionOrder::getUpdatedAt,internalTransferDto.getUpdated_end_date());
        }
        if (internalTransferDto.getCompleted_start_time() != null){
            lam.ge(SystemIntroversionOrder::getCompletedAt,internalTransferDto.getCompleted_start_time());
        }
        if (internalTransferDto.getCompleted_end_time() != null){
            lam.le(SystemIntroversionOrder::getCompletedAt,internalTransferDto.getCompleted_end_time());
        }
        if (internalTransferDto.getVnd_otp() != null){
            lam.in(SystemIntroversionOrder::getVndOtp,internalTransferDto.getVnd_otp());
        }
        if (internalTransferDto.getVnd_payment_method() != null){
            lam.in(SystemIntroversionOrder::getVndPaymentMethod,internalTransferDto.getVnd_payment_method());
        }
        if (internalTransferDto.getPostscript() != null){
            lam.like(SystemIntroversionOrder::getPostscript,internalTransferDto.getPostscript());
        }
        if (internalTransferDto.getAlt_id() != null){
            List<String> split = StrUtil.split(internalTransferDto.getAlt_id(), "|");
            lam.in(SystemIntroversionOrder::getTrId,split);
        }
        if (internalTransferDto.getTr_auto() != null){
            lam.eq(SystemIntroversionOrder::getTrAuto,1);
        }
        if (internalTransferDto.getVbs_tag() != null){
            if (internalTransferDto.getVbs_tag() == 1){
                lam.isNotNull(SystemIntroversionOrder::getFromVsId).isNotNull(SystemIntroversionOrder::getToVsId);
            } else if (internalTransferDto.getVbs_tag() == 2) {
                lam.isNull(SystemIntroversionOrder::getFromVsId).or().isNull(SystemIntroversionOrder::getToVsId);
            }
        }
        if (internalTransferDto.getStatus() != null){
            lam.in(SystemIntroversionOrder::getStatus,internalTransferDto.getStatus());
        }
        if (internalTransferDto.getType() != null){
            lam.in(SystemIntroversionOrder::getType,internalTransferDto.getType());
        }


        //使用MP中自带的分页插件，放入获取的当前页和每页显示条数
        Page<SystemIntroversionOrder> page = new Page<>(internalTransferDto.getPage(),internalTransferDto.getRp());
        //查询放入page和查询条件lqw
        Page<SystemIntroversionOrder> systemIntroversionOrderPage = systemIntroversionOrderMapper.selectPage(page, lam);
        //获取查询结果
        List<SystemIntroversionOrder> systemIntroversionOrderList = systemIntroversionOrderPage.getRecords();

        // 查询数据不为空
        if (systemIntroversionOrderList != null && systemIntroversionOrderList.size() != 0){
            // 把查询的结果复制进返回的实体类中，再加入集合里
            List<InternalTransferVo> internalTransferVoList = systemIntroversionOrderList.stream().map(iter -> {
                InternalTransferVo internalTransferVo = new InternalTransferVo();
                BeanUtils.copyProperties(iter,internalTransferVo);
                return internalTransferVo;
            }).collect(Collectors.toList());
            // 分页对象，参数
            Page<InternalTransferVo> internalTransferVoPage = new Page<>(internalTransferDto.getPage(),internalTransferDto.getRp(),
                    systemIntroversionOrderPage.getTotal());
            internalTransferVoPage.setRecords(internalTransferVoList);
            // 其他信息
            return getMerchantResp(internalTransferVoPage,null,request,internalTransferDto.getRp());
        }
        // 查询数据为空
        return GetNoResp.getNoBankAccountListResp(request,internalTransferDto.getRp());
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
