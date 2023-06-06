package org.example.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.admin.mapper.SystemDepositOrderMapper;
import org.example.admin.service.*;
import org.example.admin.vo.*;
import org.example.common.base.CommResp;
import org.example.admin.dto.DashboardDto;
import org.example.admin.dto.SystemDepositOrderDto;
import org.example.common.entity.*;
import org.example.common.exception.MsgException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * system_deposit_order Service 接口实现
 * </p>
 *
 * @author zhangmi
 * @since 2023-05-17 19:16:15
 */
@Service
@Transactional
@Slf4j
public class SystemDepositOrderServiceImpl extends ServiceImpl<SystemDepositOrderMapper, SystemDepositOrder> implements SystemDepositOrderService {

    @Autowired
    private SystemDepositOrderMapper systemDepositOrderMapper;

    @Autowired
    private SystemBankCardService systemBankCardService;

    @Autowired
    private SystemBankCardGroupService systemBankCardGroupService;

    @Autowired
    private SystemMerchantService systemMerchantService;

    @Autowired
    private SystemNoticeRecordService systemNoticeRecordService;

    @Autowired
    private SystemVirtualBankStatementService systemVirtualBankStatementService;

    String today = DateUtil.today();
    String tomorrow = DateUtil.formatDate(DateUtil.tomorrow());
    String yesterday = DateUtil.formatDate(DateUtil.yesterday());
    private final static String successStatus = "1";
    private final static String failStatus = "4,5";
    private final static String incomeStatus = "3";
    private DataVo dataVo = new DataVo();


    @Override
    public CommResp selectTxnModeByRegion(DashboardDto dashboardDto) {
        StringBuilder cardId = new StringBuilder();
        StringBuilder merchantId = new StringBuilder();
        if (dashboardDto.getCardGroupId() != null) {
            List<SystemBankCard> cards = systemBankCardService
                    .query().eq("card_group_id", dashboardDto.getCardGroupId())
                    .select("card_id").list();
            if (cards != null) {
                for (SystemBankCard card : cards) {
                    cardId.append(card.getCardId() + ",");
                }
                cardId.deleteCharAt(cardId.length() - 1);
            }
        }
        if (dashboardDto.getMerchantId() != null) {
            for (String s : dashboardDto.getMerchantId()) {
                merchantId.append(s + ",");
            }
            merchantId.deleteCharAt(merchantId.length() - 1);
        }
        todaySuccess(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        todayFail(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        income(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        commission(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        loss(dashboardDto.getCurrency(), cardId.toString(), merchantId.toString());
        merchant(dashboardDto.getCardGroupId(), dashboardDto.getCurrency());
        freezeLoss(dashboardDto.getCardGroupId(), dashboardDto.getCurrency());
        //整理数据返回前端
        return CommResp.data(dataVo);
    }

    @Override
    public CommResp infoText() {
        HashMap<String, HashMap<String, DepositQRLossCommissionVo>> hs = info();
        return CommResp.data(hs);
    }

    @Override
    public Page<SystemDepositOrderVo> searchByCondition(SystemDepositOrderDto systemDepositOrderDto) {
        // 获取页码
        Integer pageNum = systemDepositOrderDto.getPage();
        Integer pageSize = systemDepositOrderDto.getRp();
        Page<SystemDepositOrder> depositOrderPage = new Page<>(pageNum, pageSize);

        // 银行id
        Integer bk100Id = systemDepositOrderDto.getBk100Id();
        Long[] bankCardIds = null;
        if (bk100Id != null){
            // 通过银行id查询，该银行下的所有的银行卡
            List<SystemBankCard> bankCardList = systemBankCardService.list(new LambdaQueryWrapper<SystemBankCard>().eq(SystemBankCard::getBankId, bk100Id));
            // 银行卡id数组
            bankCardIds = new Long[bankCardList.size()];
            if (bankCardList != null && bankCardList.size() != 0){
                for (int i = 0; i < bankCardList.size(); i++) {
                    bankCardIds[i] = bankCardList.get(i).getCardId();
                }
            }
        }


        // to 账户代码
        // 账户代码
        String accountCode = systemDepositOrderDto.getTo();
        Long[] bankCardIdsTo = null;
        if (accountCode != null){
            // 通过账户代码，查询所有的银行卡id
            List<SystemBankCard> cardList = systemBankCardService.list(new LambdaQueryWrapper<SystemBankCard>().eq(SystemBankCard::getAccountCode, accountCode));
            bankCardIdsTo = new Long[cardList.size()];
            if (cardList != null && cardList.size() != 0){
                for (int i = 0; i < cardList.size(); i++) {
                    bankCardIdsTo[i] = cardList.get(i).getCardId();
                }
            }
        }

        // on100_status 通知状态 查询订单通知表，对比是否是当前状态，on100表中的result字段
        Integer on100Status = systemDepositOrderDto.getOn100Status();
        // 所有的外键
        Long[] foreginKeys = null;
        if (on100Status != null && on100Status != 4 & on100Status != 0){
            // 通过result状态查询查询所有通知记录
            LambdaQueryWrapper<SystemNoticeRecord> queryWrapper = new LambdaQueryWrapper<SystemNoticeRecord>();
            queryWrapper.eq(SystemNoticeRecord::getResult, on100Status);
            queryWrapper.eq(SystemNoticeRecord::getModelClass, "FI");
            List<SystemNoticeRecord> noticeRecordList = systemNoticeRecordService.list(queryWrapper);
            foreginKeys = new Long[noticeRecordList.size()];
            if (noticeRecordList != null && noticeRecordList.size() != 0){
                for (int i = 0; i < noticeRecordList.size(); i++) {
                    foreginKeys[i] = noticeRecordList.get(i).getForeignKey();
                }
            }
        }

        // 查询条件
        LambdaQueryWrapper<SystemDepositOrder> queryWrapper = new LambdaQueryWrapper<>();
        if (systemDepositOrderDto.getCurrency() != null && !systemDepositOrderDto.getCurrency().isEmpty()){
            queryWrapper.eq(SystemDepositOrder::getCurrency, systemDepositOrderDto.getCurrency());
        }if(systemDepositOrderDto.getPg100Id() != null){
            queryWrapper.eq(SystemDepositOrder::getBankCardId, systemDepositOrderDto.getPg100Id());
        }if(systemDepositOrderDto.getSh100Ids() != null && systemDepositOrderDto.getSh100Ids().length > 0){
            queryWrapper.in(SystemDepositOrder::getMerchantId, systemDepositOrderDto.getSh100Ids());
        }if (systemDepositOrderDto.getFromBank() !=null && !systemDepositOrderDto.getFromBank().isEmpty()){
            queryWrapper.eq(SystemDepositOrder::getFromBank, systemDepositOrderDto.getFromBank());
        }if (bankCardIds != null && bankCardIds.length > 0){
            queryWrapper.in(SystemDepositOrder::getBankCardId, bankCardIds);
        }if (systemDepositOrderDto.getStatusList() != null && systemDepositOrderDto.getStatusList().length > 0){
            queryWrapper.in(SystemDepositOrder::getStatus, systemDepositOrderDto.getStatusList());
        }if(systemDepositOrderDto.getTxnMode() != null){
            queryWrapper.eq(SystemDepositOrder::getTxnMode, systemDepositOrderDto.getTxnMode());
        }if (on100Status != null){
            if (on100Status == 4 || on100Status == 0){
                queryWrapper.isNull(SystemDepositOrder::getCommandId);
            }else {
                queryWrapper.in(SystemDepositOrder::getFiId, foreginKeys);
            }
        }
        if (systemDepositOrderDto.getPairingByVbs() != null){
            if (systemDepositOrderDto.getPairingByVbs() == 0) {
                queryWrapper.isNull(SystemDepositOrder::getVbId);
            } else {
                queryWrapper.isNotNull(SystemDepositOrder::getVbId);
            }
        }if (systemDepositOrderDto.getPairingBySms() != null){
            if (systemDepositOrderDto.getPairingBySms() == 0) {
                queryWrapper.isNull(SystemDepositOrder::getSmId);
            } else {
                queryWrapper.isNotNull(SystemDepositOrder::getSmId);
            }
        }if (systemDepositOrderDto.getStartDate() != null && !systemDepositOrderDto.getEndDate().isEmpty()){
            queryWrapper.gt(SystemDepositOrder::getCreatedAt, systemDepositOrderDto.getStartDate());
            queryWrapper.lt(SystemDepositOrder::getCreatedAt, systemDepositOrderDto.getEndDate());
        }if (systemDepositOrderDto.getCompletedStartDate() != null && !systemDepositOrderDto.getCompletedEndDate().isEmpty()){
            queryWrapper.gt(SystemDepositOrder::getCompletedAt, systemDepositOrderDto.getCompletedStartDate());
            queryWrapper.lt(SystemDepositOrder::getCompletedAt, systemDepositOrderDto.getCompletedEndDate());
        }if (systemDepositOrderDto.getUpdatedStartDate() != null && !systemDepositOrderDto.getUpdatedEndDate().isEmpty()){
            queryWrapper.gt(SystemDepositOrder::getUpdatedAt, systemDepositOrderDto.getUpdatedStartDate());
            queryWrapper.lt(SystemDepositOrder::getUpdatedAt, systemDepositOrderDto.getUpdatedEndDate());
        }if (systemDepositOrderDto.getFrom() !=null && !systemDepositOrderDto.getFrom().isEmpty()){
            queryWrapper.eq(SystemDepositOrder::getFromBank, systemDepositOrderDto.getFrom());
        }if(systemDepositOrderDto.getAltId() != null){
            queryWrapper.eq(SystemDepositOrder::getFiId, systemDepositOrderDto.getAltId());
        }if (systemDepositOrderDto.getReference() != null){
            queryWrapper.eq(SystemDepositOrder::getReference, systemDepositOrderDto.getReference());
        }if (systemDepositOrderDto.getVb100Id() != null){
            queryWrapper.eq(SystemDepositOrder::getVbId, systemDepositOrderDto.getVb100Id());
        }if (systemDepositOrderDto.getSm100Id() != null){
            queryWrapper.eq(SystemDepositOrder::getSmId, systemDepositOrderDto.getSm100Id());
        }if (systemDepositOrderDto.getMessage() != null && !systemDepositOrderDto.getMessage().isEmpty()){
            queryWrapper.eq(SystemDepositOrder::getMessage, systemDepositOrderDto.getMessage());
        }if (systemDepositOrderDto.getPostscript() != null && !systemDepositOrderDto.getPostscript().isEmpty()){
            queryWrapper.eq(SystemDepositOrder::getPostscript, systemDepositOrderDto.getPostscript());
        }if (systemDepositOrderDto.getOrderAmount() != null && systemDepositOrderDto.getOrderAmount().length > 0){
            queryWrapper.gt(SystemDepositOrder::getOrderAmount, systemDepositOrderDto.getOrderAmount()[0]);
            queryWrapper.lt(SystemDepositOrder::getOrderAmount, systemDepositOrderDto.getOrderAmount()[1]);
        }if (systemDepositOrderDto.getRequestAmount() != null && systemDepositOrderDto.getRequestAmount().length > 0){
            queryWrapper.gt(SystemDepositOrder::getRequestAmount, systemDepositOrderDto.getRequestAmount()[0]);
            queryWrapper.lt(SystemDepositOrder::getRequestAmount, systemDepositOrderDto.getRequestAmount()[1]);
        }if (systemDepositOrderDto.getPaidAmount() != null && systemDepositOrderDto.getPaidAmount().length > 0){
            queryWrapper.gt(SystemDepositOrder::getPaidAmount, systemDepositOrderDto.getPaidAmount()[0]);
            queryWrapper.lt(SystemDepositOrder::getPaidAmount, systemDepositOrderDto.getPaidAmount()[1]);
        }

        // 查询分页数据
        depositOrderPage = this.page(depositOrderPage, queryWrapper);
        // 返回的page
        Page<SystemDepositOrderVo> orderVoPage = new Page<>();
        BeanUtils.copyProperties(depositOrderPage, orderVoPage, "records");
        // 获取records
        List<SystemDepositOrder> records = depositOrderPage.getRecords();
        List<SystemDepositOrderVo> collect = records.stream().map(item -> {

            SystemDepositOrderVo vo = new SystemDepositOrderVo();
            // 拷贝数据
            BeanUtils.copyProperties(item, vo);
            // 主键处理
            Long id = item.getFiId();
            String altId = getAltId(id);
            vo.setAltId(altId);
            // 判断是否有回调方法
            Integer status = item.getStatus();
            if (status == 4) {
                vo.setCallbackable(true);
            } else {
                vo.setCallbackable(false);
            }
            // 银行卡id
            Long bankCardId = item.getBankCardId();
            if(bankCardId != null){
                SystemBankCard bankCard = systemBankCardService.getById(bankCardId);
                if (bankCard != null){
                    // 添加银行卡需要返回的数据
                    vo.setPromptpayCode(bankCard.getPromptpayCode());
                    vo.setBankNote(bankCard.getNote());
                    vo.setAccountCode(bankCard.getAccountCode());
                    vo.setVndOtp(bankCard.getVndOtp());
                    vo.setAccountCode(bankCard.getAccountCode());
                    // TODO BC100 缺少 isRunMon字段
                }
            }

            // 系统商户表id
            Long merchantId = item.getMerchantId();
            if(merchantId != null){
                SystemMerchant merchant = systemMerchantService.getById(merchantId);
                if(merchant != null){
                    // 添加系统商户表需要返回的数据
                    vo.setCode(merchant.getCode());
                    vo.setTrQrRate(merchant.getTrQrRate());
                    vo.setTrRate(merchant.getTrRate());
                }
            }

            // 订单通知记录表查询，通过foreign_key fi_id 和 模型类：充值 FI 下发 FX 代付 FO，本表为FI
            Long fiId = item.getFiId();
            if (fiId != null){
                // 查询
                LambdaQueryWrapper<SystemNoticeRecord> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(SystemNoticeRecord::getForeignKey, fiId);
                wrapper.eq(SystemNoticeRecord::getModelClass, "FI");
                SystemNoticeRecord noticeRecord = systemNoticeRecordService.getOne(wrapper);
                if (noticeRecord != null){
                    // 添加订单通知记录表需要返回的数据
                    vo.setOn100Content(noticeRecord.getContent());
                    vo.setResult(noticeRecord.getResult());
                    vo.setNoticeRecordUpdatedAt(noticeRecord.getUpdatedAt());
                }
            }

            return vo;
        }).collect(Collectors.toList());

        orderVoPage.setRecords(collect);



        return orderVoPage;
    }

    /**
     * 拼接主键id的方法
     * @param id
     * @return
     */
    private String getAltId(Long id) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String format = localDate.format(formatter);
        String altId = "D-" + format + id;
        return altId;
    }

    @Override
    public String download(SystemDepositOrderDto systemDepositOrderDto) throws MsgException {
        // 获取分页数据
        Page<SystemDepositOrderVo> orderVoPage = searchByCondition(systemDepositOrderDto);
        // 查看数据总量是否超过8000
        if (orderVoPage.getTotal() > 8000){
            throw new MsgException("数据量大于8000无法下载");
        }
        // 创建表格
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建sheet表
        XSSFSheet sheet = workbook.createSheet();
        // 创建表头数据
        ArrayList<String> headList1 = new ArrayList<>();
        headList1.add("Id");
        headList1.add("From");
        headList1.add("To");
        headList1.add("Order Amount");
        headList1.add("Requested Amount");
        headList1.add("Received Amount");
        headList1.add("Loss Amount");
        headList1.add("Fee");
        headList1.add("Collect Type\n" +
                "OTP Method");
        headList1.add("Info");
        headList1.add("Status");
        headList1.add("Step");
        headList1.add("Created");
        headList1.add("VBS");
        headList1.add("SMS");
        headList1.add("Updated");
        headList1.add("Nt Status");
        headList1.add("Message");
        headList1.add("Account code");
        headList1.add("Mode");
        headList1.add("FI Create");
        headList1.add("FI Complete");
        headList1.add("Update Man");
        headList1.add("Auto/Manual");
        headList1.add("VBS Create");
        headList1.add("FI create and VBS Create comparison(s)");
        headList1.add("FI create and FI Complete comparison(s) ");

        //创建表头，第一行
        createSheetRow(sheet, headList1, 0);

        // 创建表头数据
        ArrayList<String> headList2 = new ArrayList<>();
        headList2.add("id");
        headList2.add("From");
        headList2.add("To");
        headList2.add("订单金额");
        headList2.add("请求金额");
        headList2.add("到账金额");
        headList2.add("QR充值损失");
        headList2.add("交易费");
        headList2.add("收款方式\n" +
                "OTP Method");
        headList2.add("资讯");
        headList2.add("状态");
        headList2.add("步骤");
        headList2.add("创建时间");
        headList2.add("VBS");
        headList2.add("SMS");
        headList2.add("更新时间");
        headList2.add("通知状态");
        headList2.add("信息");
        headList2.add("银行代号");
        headList2.add("交易模式");
        headList2.add("FI 创建时间");
        headList2.add("FI 上分时间");
        headList2.add("更新人员");
        headList2.add("自动/人工");
        headList2.add("VBS 创建时间");
        headList2.add("交易单和VBS时间差(秒)");
        headList2.add("交易单和上分时间差(秒)");

        //创建表头，第二行
        createSheetRow(sheet, headList2, 1);

        // 获取查询到的数据
        List<SystemDepositOrderVo> records = orderVoPage.getRecords();
        // 遍历添加数据到表格中
        int rowNum = 2;
        for (SystemDepositOrderVo item : records){
            ArrayList<String> list = new ArrayList<>();
            list.add(item.getAltId() == null ? "" : item.getAltId());
            list.add(item.getFromBank() == null ? "" : item.getFromBank());
            list.add(item.getAccountCode() == null ? "" : item.getAccountCode());
            list.add(item.getOrderAmount() == null ? "" : item.getOrderAmount().toString());
            list.add(item.getRequestAmount() == null ? "" : item.getRequestAmount().toString());
            list.add(item.getLossAmount() == null ? "" : item.getLossAmount().toString());
            list.add(item.getTrQrRate() == null ? "" : item.getTrQrRate().toString());
            list.add(item.getBankFee() == null ? "" : item.getBankFee().toString());
            switch (item.getTxnMode()){
                case 1:
                    list.add("银行");
                    break;
                case 2:
                    list.add("QR");
                    break;
                case 3:
                    list.add("Crypto");
                    break;
                default:
                    list.add("");
            }
            list.add(item.getCode() + "\n" + (item.getNote() == null ? "" : item.getNote()) + "\n" + item.getReference());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy\nhh:mm:ss ");
            if (item.getVbId() == null && item.getSmId() == null){
                list.add("等待中");
            }else if (item.getVbId() != null){
                list.add("成功" + "\n" + "VBS" + (item.getVbPairingTime().format(formatter)));
            }else if (item.getSmId() != null) {
                list.add("成功" + "\n" + "SMS" + (item.getSmPairingTime().format(formatter)));
            }else {
                list.add("");
            }
            list.add(item.getStep() == null ? "" : item.getStep());
            list.add(item.getCreatedAt() == null ? "" : item.getCreatedAt().format(formatter));
            list.add(item.getVbPairingTime() == null ? "" : item.getVbPairingTime().format(formatter) + "\n" + item.getVbId());
            list.add(item.getSmPairingTime() == null ? "" : item.getSmPairingTime().format(formatter) + "\n" + item.getSmId());
            list.add(item.getUpdatedAt() == null ? "" : item.getUpdatedAt().format(formatter));
            // 通知状态 存的数字 如何判断是否成功
            /**
             *  const STATUS_MN_PENDING = 31;           //等待中
             *  const STATUS_MN_SUCCESS = 33;           //成功
             *  const STATUS_MN_NOTRECEIVE_VERIFY = 34; //未到账审核
             *  const STATUS_MN_FAILED = 35;            //失败
             *  const STATUS_MN_NOT_RECEIVED = 36;      //未到账
             */
            int action = item.getAction();
            switch (action){
                case 31:
                    list.add("等待中");
                    break;
                case 33:
                    list.add("成功" + "\n" + item.getCompletedAt().format(formatter));
                    break;
                case 34:
                    list.add("未到账审核");
                    break;
                case 35:
                    list.add("失败");
                    break;
                case 36:
                    list.add("未到账");
                    break;
                default:
                    list.add("");
            }
            list.add(item.getMessage() == null ? "" : item.getMessage());
            list.add(item.getAccountCode() == null ? "" : item.getAccountCode());
            // 交易模式
            switch (item.getTxnMode()){
                case 1:
                    list.add("银行");
                    break;
                case 2:
                    list.add("QR");
                    break;
                case 3:
                    list.add("Crypto");
                    break;
                default:
                    list.add("");
            }
            // FI 创建时间
            list.add(item.getCreatedAt() == null ? "" : item.getCreatedAt().format(formatter));
            // FI 完成时间
            list.add(item.getCompletedAt() == null ? "" : item.getCompletedAt().format(formatter));
            // 更新人员
            list.add(item.getUpdater() == null ? "" : item.getUpdater());
            // 自动/人工 Status
            int status = item.getStatus();
            if (status == 4){
                list.add("手动");
            }else {
                list.add("自动");
            }
            // 获取vbsid
            Long vbId = item.getVbId();
            if (vbId != null){
                // 查询vbs创建时间
                SystemVirtualBankStatement bankStatement = systemVirtualBankStatementService.getById(vbId);
                if (bankStatement != null){
                    list.add(bankStatement.getCreatedAt() == null ? "" : bankStatement.getCreatedAt().format(formatter));
                    // 交易单和VBS时间差(秒) 使用VBS时间 - 交易单时间
                    Long createSeconds = Duration.between(item.getCreatedAt(), bankStatement.getCreatedAt()).getSeconds();
                    list.add(createSeconds.toString());
                }else {
                    list.add("");
                    list.add("");
                }
            }else {
                list.add("");
            }
            // 交易单和上分时间差(秒) 使用交易单完成时间 - 交易单创建时间
            if (item.getCreatedAt() != null && item.getCompletedAt() != null){
                Long completeSeconds = Duration.between(item.getCreatedAt(), item.getCompletedAt()).getSeconds();
                list.add(completeSeconds.toString());
            }else {
                list.add("");
            }
            createSheetRow(sheet, list, rowNum);
            rowNum++;
        }
        File file = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String format = LocalDate.now().format(formatter);
            file = File.createTempFile(format +"-FI-", ".xlsx");
        } catch (IOException e) {
            log.error("创建临时文件失败，错误信息{}", e.getMessage());
            throw new MsgException("创建临时文件失败");
        }
        try(FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (Exception e) {
            log.error("保存文件到本地失败，错误信息{}", e.getMessage());
            throw new MsgException("保存文件到本地失败");
        }
        // 文件名
        String fileName = file.getName();
        return fileName;
    }

    /**
     * 创建sheet表的一行数据
     * @param sheet sheet表
     * @param list 数据
     * @param rowNum 创建的行数
     */
    private void createSheetRow(XSSFSheet sheet, ArrayList<String> list, int rowNum) {
        XSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < list.size(); i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(list.get(i));
        }
    }

    //今日,昨日成功数据
    public void todaySuccess(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, successStatus, cardId, merchantId);
        //银行
        if (todayList != null && todayList.size() != 0) {
            for (SystemDepositOrderVo systemDepositOrderVo : todayList) {
                switch (systemDepositOrderVo.getTxnMode()) {
                    case 1: {
                        dataVo.setTodaySuccessBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessBankCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setTodayBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                    case 2:{
                        //QR
                        dataVo.setTodaySuccessQRMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessQRCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setTodayQRIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 3:{
                        //True Wallet
                        dataVo.setTodaySuccessTWMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessTWCount(systemDepositOrderVo.getSuccessCount());

                        dataVo.setTodayTWIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 4:{
                        //本地银行
                        dataVo.setTodaySuccessBDBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setTodaySuccessBDBankCount(systemDepositOrderVo.getSuccessCount());

                        dataVo.setTodayBDBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                        ;
                }
            }
        }
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, successStatus, cardId, merchantId);
        if (yesterdayList != null && yesterdayList.size() != 0) {
            for (SystemDepositOrderVo systemDepositOrderVo : yesterdayList) {

                switch (systemDepositOrderVo.getTxnMode()) {
                    case 1: {
                        dataVo.setYesterdaySuccessBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessBankCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setYesterdayBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                    case 2:{
                        //QR
                        dataVo.setYesterdaySuccessQRMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessQRCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setYesterdayQRIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 3:{
                        //True WalYesterday
                        dataVo.setYesterdaySuccessTWMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessTWCount(systemDepositOrderVo.getSuccessCount());

                        dataVo.setYesterdayTWIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }

                    case 4:{
                        //本地银行
                        dataVo.setYesterdaySuccessBDBankMoney(systemDepositOrderVo.getSuccessMoney());
                        dataVo.setYesterdaySuccessBDBankCount(systemDepositOrderVo.getSuccessCount());
                        dataVo.setYesterdayBDBankIncome(amountFee(systemDepositOrderVo.getFailMoney(), systemDepositOrderVo.getBankFee()));
                    }
                    ;
                }
            }
        }
        //代付
        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, successStatus, cardId);
        dataVo.setTodaySuccessDFMoney(todayDF.getSuccessMoney());
        dataVo.setTodaySuccessDFCount(todayDF.getSuccessCount());

        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, successStatus, cardId);
        dataVo.setYesterdaySuccessDFMoney(yesterdayDF.getSuccessMoney());
        dataVo.setYesterdaySuccessDFCount(yesterdayDF.getSuccessCount());
        //下发
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, successStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, successStatus, cardId);
        dataVo.setTodaySuccessXFMoney(todayXF.getSuccessMoney());
        dataVo.setTodaySuccessXFCount(todayXF.getSuccessCount());

        dataVo.setYesterdaySuccessXFMoney(yesterdayXF.getSuccessMoney());
        dataVo.setYesterdaySuccessXFCount(yesterdayXF.getSuccessCount());
        //虚拟币
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, successStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, successStatus, cardId);
        dataVo.setTodaySuccessXNMoney(todayXN.getSuccessMoney());
        dataVo.setTodaySuccessXNCount(todayXN.getSuccessCount());

        dataVo.setYesterdaySuccessXNMoney(yesterdayXN.getSuccessMoney());
        dataVo.setYesterdaySuccessXNCount(yesterdayXN.getSuccessCount());
    }
    //今日,昨日失败数据
    public void todayFail(String currency, String cardId, String merchantId) {
        //前四个
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, failStatus, cardId, merchantId);
        if (todayList != null && todayList.size() != 0) {
            for (SystemDepositOrderVo systemDepositOrderVo : todayList) {
                switch (systemDepositOrderVo.getTxnMode()){
                    case 1:{
                        //银行
                        dataVo.setTodayFailBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailBankCount(systemDepositOrderVo.getFailCount());
                    }
                    case 2:{
                        //QR
                        dataVo.setTodayFailQRMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailQRCount(systemDepositOrderVo.getFailCount());
                    }
                    case 3:{
                        //True Wallet
                        dataVo.setTodayFailTWMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailTWCount(systemDepositOrderVo.getFailCount());
                    }
                    case 4:{
                        //本地银行
                        dataVo.setTodayFailBDBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setTodayFailBDBankCount(systemDepositOrderVo.getFailCount());
                    }
                }
            }
        }
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, failStatus, cardId, merchantId);
        if (yesterdayList != null && yesterdayList.size() != 0) {

            for (SystemDepositOrderVo systemDepositOrderVo : yesterdayList) {
                switch (systemDepositOrderVo.getTxnMode()){
                    case 1:{
                        //银行
                        dataVo.setYesterdayFailBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailBankCount(systemDepositOrderVo.getFailCount());
                    }
                    case 2:{
                        //QR
                        dataVo.setYesterdayFailQRMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailQRCount(systemDepositOrderVo.getFailCount());
                    }
                    case 3:{
                        //True WalYesterday
                        dataVo.setYesterdayFailTWMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailTWCount(systemDepositOrderVo.getFailCount());
                    }
                    case 4:{
                        //本地银行
                        dataVo.setYesterdayFailBDBankMoney(systemDepositOrderVo.getFailMoney());
                        dataVo.setYesterdayFailBDBankCount(systemDepositOrderVo.getFailCount());
                    }
                }
        }
        //代付
        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, failStatus, cardId);
        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, failStatus, cardId);
        dataVo.setTodayFailDFMoney(todayDF.getFailMoney());
        dataVo.setTodayFailDFCount(todayDF.getFailCount());

        dataVo.setYesterdayFailDFMoney(yesterdayDF.getFailMoney());
        dataVo.setYesterdayFailDFCount(yesterdayDF.getFailCount());

        //下发
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, failStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, failStatus, cardId);
        dataVo.setTodayFailXFMoney(todayXF.getFailMoney());
        dataVo.setTodayFailXFCount(todayXF.getFailCount());

        dataVo.setYesterdayFailXFMoney(yesterdayXF.getFailMoney());
        dataVo.setYesterdayFailXFCount(yesterdayXF.getFailCount());
        //虚拟币
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, failStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, failStatus, cardId);
        dataVo.setTodayFailXNMoney(todayXN.getFailMoney());
        dataVo.setTodayFailXNCount(todayXN.getFailCount());

        dataVo.setYesterdayFailXNMoney(yesterdayXN.getFailMoney());
        dataVo.setYesterdayFailXNCount(yesterdayXN.getFailCount());
    }
    }



    //收入
    private void income(String currency, String cardId, String merchantId) {
        //今日收入
        List<SystemDepositOrderVo> todayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, today, tomorrow, incomeStatus, cardId, merchantId);

        SystemDepositOrderVo todayDF = systemDepositOrderMapper.selectWithdrawal(currency, today, tomorrow, incomeStatus, cardId);
        SystemDepositOrderVo todayXF = systemDepositOrderMapper.selectSettlement(currency, today, tomorrow, incomeStatus, cardId);
        SystemDepositOrderVo todayXN = systemDepositOrderMapper.selectCrypto(currency, today, tomorrow, incomeStatus, cardId);

        if (todayList != null && todayList.size() != 0) {
            dataVo.setTodayBankIncome(amountFee(todayList.get(0).getFailMoney(), todayList.get(0).getBankFee()));
            dataVo.setTodayQRIncome(amountFee(todayList.get(1).getFailMoney(), todayList.get(1).getBankFee()));
            dataVo.setTodayTWIncome(amountFee(todayList.get(2).getFailMoney(), todayList.get(2).getBankFee()));
            dataVo.setTodayBDBankIncome(amountFee(todayList.get(3).getFailMoney(), todayList.get(3).getBankFee()));
            dataVo.setTodayDFIncome(amountFee(todayDF.getFailMoney(), todayDF.getBankFee()));
            dataVo.setTodayXFIncome(amountFee(todayXF.getFailMoney(), todayXF.getBankFee()));
            dataVo.setTodayXNIncome(amountFee(todayXN.getFailMoney(), todayXN.getBankFee()));
        }

        //昨日收入 Yesterday
        List<SystemDepositOrderVo> yesterdayList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, yesterday, today, incomeStatus, cardId, merchantId);
        SystemDepositOrderVo yesterdayDF = systemDepositOrderMapper.selectWithdrawal(currency, yesterday, today, incomeStatus, cardId);
        SystemDepositOrderVo yesterdayXF = systemDepositOrderMapper.selectSettlement(currency, yesterday, today, incomeStatus, cardId);
        SystemDepositOrderVo yesterdayXN = systemDepositOrderMapper.selectCrypto(currency, yesterday, today, incomeStatus, cardId);
        if (yesterdayList != null && yesterdayList.size() != 0) {
            dataVo.setYesterdayBankIncome(amountFee(yesterdayList.get(0).getFailMoney(), yesterdayList.get(0).getBankFee()));
            dataVo.setYesterdayQRIncome(amountFee(yesterdayList.get(1).getFailMoney(), yesterdayList.get(1).getBankFee()));
            dataVo.setYesterdayTWIncome(amountFee(yesterdayList.get(2).getFailMoney(), yesterdayList.get(2).getBankFee()));
            dataVo.setYesterdayBDBankIncome(amountFee(yesterdayList.get(3).getFailMoney(), yesterdayList.get(3).getBankFee()));
        }
        dataVo.setYesterdayDFIncome(amountFee(yesterdayDF.getFailMoney(), yesterdayDF.getBankFee()));
        dataVo.setYesterdayXFIncome(amountFee(yesterdayXF.getFailMoney(), yesterdayXF.getBankFee()));
        dataVo.setYesterdayXNIncome(amountFee(yesterdayXN.getFailMoney(), yesterdayXN.getBankFee()));
        //本月
        List<SystemDepositOrderVo> thisMonthList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, getThisMonth(), getXMonth(), incomeStatus, cardId, merchantId);
        SystemDepositOrderVo thisMonthDF = systemDepositOrderMapper.selectWithdrawal(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);
        SystemDepositOrderVo thisMonthXF = systemDepositOrderMapper.selectSettlement(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);
        SystemDepositOrderVo thisMonthXN = systemDepositOrderMapper.selectCrypto(currency, getThisMonth(), getXMonth(), incomeStatus, cardId);

        if (thisMonthList != null && thisMonthList.size() != 0) {
            dataVo.setThisMonthBankIncome(amountFee(thisMonthList.get(0).getFailMoney(), thisMonthList.get(0).getBankFee()));
            dataVo.setThisMonthQRIncome(amountFee(thisMonthList.get(1).getFailMoney(), thisMonthList.get(1).getBankFee()));
            dataVo.setThisMonthTWIncome(amountFee(thisMonthList.get(2).getFailMoney(), thisMonthList.get(2).getBankFee()));
            dataVo.setThisMonthBDBankIncome(amountFee(thisMonthList.get(3).getFailMoney(), thisMonthList.get(3).getBankFee()));
        }
        dataVo.setThisMonthDFIncome(amountFee(thisMonthDF.getFailMoney(), thisMonthDF.getBankFee()));
        dataVo.setThisMonthXFIncome(amountFee(thisMonthXF.getFailMoney(), thisMonthXF.getBankFee()));
        dataVo.setThisMonthXNIncome(amountFee(thisMonthXN.getFailMoney(), thisMonthXN.getBankFee()));

        //上月
        List<SystemDepositOrderVo> lastMonthList = systemDepositOrderMapper
                .selectMoneyAndCount(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId, merchantId);
        SystemDepositOrderVo lastMonthDF = systemDepositOrderMapper.selectWithdrawal(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);
        SystemDepositOrderVo lastMonthXF = systemDepositOrderMapper.selectSettlement(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);
        SystemDepositOrderVo lastMonthXN = systemDepositOrderMapper.selectCrypto(currency, getLastMonth(), getThisMonth(), incomeStatus, cardId);

        if (lastMonthList != null && lastMonthList.size() != 0) {
            dataVo.setLastMonthBankIncome(amountFee(lastMonthList.get(0).getFailMoney(), lastMonthList.get(0).getBankFee()));
            dataVo.setLastMonthQRIncome(amountFee(lastMonthList.get(1).getFailMoney(), lastMonthList.get(1).getBankFee()));
            dataVo.setLastMonthTWIncome(amountFee(lastMonthList.get(2).getFailMoney(), lastMonthList.get(2).getBankFee()));
            dataVo.setLastMonthBDBankIncome(amountFee(lastMonthList.get(3).getFailMoney(), lastMonthList.get(3).getBankFee()));
        }
        dataVo.setLastMonthDFIncome(amountFee(lastMonthDF.getFailMoney(), lastMonthDF.getBankFee()));
        dataVo.setLastMonthXFIncome(amountFee(lastMonthXF.getFailMoney(), lastMonthXF.getBankFee()));
        dataVo.setLastMonthXNIncome(amountFee(lastMonthXN.getFailMoney(), lastMonthXN.getBankFee()));


    }

    //获取本月
    public static final String getThisMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        return format.format(date);
    }
    //获取上月

    /**
     * 获取上个月月份
     *
     * @return
     */
    public static String getLastMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        // 设置为上一个月
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        return format.format(date);
    }

    /**
     * 获取下个月月份
     *
     * @return
     */
    public static String getXMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        // 设置为上一个月
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        date = calendar.getTime();
        return format.format(date);
    }

    //银行手续费
    private void commission(String currency, String cardId, String merchantId) {
        //今日
        List<SystemDepositOrderCommissionVo> todayCommission =
                systemDepositOrderMapper.selectWithdrawalrCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayCommission != null && todayCommission.size() != 0) {
            dataVo.setTodayMyCommission(todayCommission.get(0).getMoney().multiply(todayCommission.get(0).getBankFee()));
            dataVo.setTodayMyCommission(todayCommission.get(1).getMoney().multiply(todayCommission.get(1).getMerchantFee()));
        }
        //结算
        SystemDepositOrderCommissionVo todayJS = systemDepositOrderMapper.selectSettlementCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayJS!=null){
            dataVo.setTodayJSCommission(todayJS.getMoney().multiply(todayJS.getBankFee()));
        }

        //内转
        SystemDepositOrderCommissionVo todayNZ = systemDepositOrderMapper.selectIntroversionCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayNZ!=null){
            dataVo.setTodayNZCommission(todayNZ.getMoney().multiply(todayNZ.getBankFee()));
        }

        //外传
        SystemDepositOrderCommissionVo todayWZ = systemDepositOrderMapper.selectExternalTradeCommission(currency, today, tomorrow, cardId, merchantId);
        if (todayWZ!=null){
            dataVo.setTodayWZCommission(todayWZ.getMoney().multiply(todayWZ.getBankFee()));
        }

        //昨日
        List<SystemDepositOrderCommissionVo> yesterdayCommission =
                systemDepositOrderMapper.selectWithdrawalrCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayCommission != null && yesterdayCommission.size() != 0
        ) {
            dataVo.setYesterdayMyCommission(yesterdayCommission.get(0).getMoney().multiply(yesterdayCommission.get(0).getBankFee()));
            dataVo.setYesterdayMyCommission(yesterdayCommission.get(1).getMoney().multiply(yesterdayCommission.get(1).getMerchantFee()));
        }
        //结算
        SystemDepositOrderCommissionVo yesterdayJS = systemDepositOrderMapper.selectSettlementCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayJS!=null){
            dataVo.setYesterdayJSCommission(yesterdayJS.getMoney().multiply(yesterdayJS.getBankFee()));
        }

        //内转
        SystemDepositOrderCommissionVo yesterdayNZ = systemDepositOrderMapper.selectIntroversionCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayNZ!=null){
            dataVo.setYesterdayNZCommission(yesterdayNZ.getMoney().multiply(yesterdayNZ.getBankFee()));
        }

        //外传
        SystemDepositOrderCommissionVo yesterdayWZ = systemDepositOrderMapper.selectExternalTradeCommission(currency, yesterday, today, cardId, merchantId);
        if (yesterdayWZ!=null){
            dataVo.setTodayWZCommission(yesterdayWZ.getMoney().multiply(yesterdayWZ.getBankFee()));
        }

    }

    //损失
    private void loss(String currency, String cardId, String merchantId) {
        //QR
        DepositQRLossCommissionVo todayQRLoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayQRLoss(todayQRLoss.getMoney());
        dataVo.setTodayQRCount(todayQRLoss.getCount());
        DepositQRLossCommissionVo thisQRLoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, getThisMonth(), getXMonth(), cardId, merchantId);
        dataVo.setThisQRLoss(thisQRLoss.getMoney());
        dataVo.setThisQRCount(thisQRLoss.getCount());
        DepositQRLossCommissionVo lastQRLoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, getLastMonth(), getThisMonth(), cardId, merchantId);
        dataVo.setLastQRLoss(lastQRLoss.getMoney());
        dataVo.setLastQRCount(lastQRLoss.getCount());
        DepositQRLossCommissionVo allLQRoss = systemDepositOrderMapper.selectDepositQRLossCommission(currency, null, null, cardId, merchantId);
        dataVo.setAllQRLoss(allLQRoss.getMoney());
        dataVo.setAllQRCount(allLQRoss.getCount());
        //True
        DepositQRLossCommissionVo todayTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, today, tomorrow, cardId, merchantId);
        dataVo.setTodayTrueLoss(todayTrueLoss.getMoney());
        dataVo.setTodayTrueCount(todayTrueLoss.getCount());
        DepositQRLossCommissionVo thisTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, getThisMonth(), getXMonth(), cardId, merchantId);
        dataVo.setThisTrueLoss(thisTrueLoss.getMoney());
        dataVo.setThisTrueCount(thisTrueLoss.getCount());
        DepositQRLossCommissionVo lastTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, getLastMonth(), getThisMonth(), cardId, merchantId);
        dataVo.setLastTrueLoss(lastTrueLoss.getMoney());
        dataVo.setLastTrueCount(lastTrueLoss.getCount());
        DepositQRLossCommissionVo allTrueLoss = systemDepositOrderMapper.selectDepositTrueLossCommission(currency, null, null, cardId, merchantId);
        dataVo.setAllTrueLoss(allTrueLoss.getMoney());
        dataVo.setAllTrueCount(allTrueLoss.getCount());
    }

    //商户
    private void merchant(Integer cardGroupId, String currency) {
        dataVo.setTodayMerchantRegister(systemDepositOrderMapper.selectMerchantRegister(currency, today, tomorrow, cardGroupId));
        dataVo.setYesterdayMerchantRegister(systemDepositOrderMapper.selectMerchantRegister(currency, yesterday, today, cardGroupId));
        dataVo.setSuccessMerchantExamine(systemDepositOrderMapper.selectMerchantExamine(currency, "1", cardGroupId));
        dataVo.setFailMerchantExamine(systemDepositOrderMapper.selectMerchantExamine(currency, "0", cardGroupId));
    }

    //损失/数量
    private void freezeLoss(Integer cardGroupId, String currency) {
        SystemDepositOrderInfoVo today = systemDepositOrderMapper.selectBankCardFreeze(currency, this.today, tomorrow, cardGroupId);

        dataVo.setTodayFreezeLoss(today.getAmount());
        dataVo.setTodayFreezeCount(today.getCount());
        SystemDepositOrderInfoVo all = systemDepositOrderMapper.selectBankCardFreeze(currency, null, null, cardGroupId);
        dataVo.setAllFreezeLoss(all.getAmount());
        dataVo.setAllFreezeCount(all.getCount());
    }
    private BigDecimal amountFee(@NotNull  BigDecimal money, @NotNull BigDecimal fee) {
        if (money==null){
            money=new BigDecimal(0);
        }
        if (fee==null){
            fee =new BigDecimal(0);
        }
        return money.subtract(money.multiply(fee));
    }


    private HashMap<String, HashMap<String, DepositQRLossCommissionVo>> info() {
        HashMap<String, HashMap<String, DepositQRLossCommissionVo>> hm = new HashMap<>();
        String[] currency = new String[]{"THB", "kVND", "kIDR", "CNY"};
        String[] table = new String[]{"system_withdrawal_order", "system_sub_settlement_order"};
        String[] ss = new String[]{"processing_fo", "processing_fx"};
        for (int i = 0; i < table.length; i++) {
            HashMap<String, DepositQRLossCommissionVo> hs = new HashMap<>();
            for (int j = 0; j < currency.length; j++) {
                DepositQRLossCommissionVo vo = systemDepositOrderMapper.processingFoAmount(currency[j], table[i]);
                hs.put(currency[j], vo);
            }
            hm.put(ss[i], hs);
        }
        String[] confirmable = new String[]{"1", "2"};
        String[] sss = new String[]{"confirmable_fo", "mer_confirm_fo"};
        for (int i = 0; i < confirmable.length; i++) {
            HashMap<String, DepositQRLossCommissionVo> hs = new HashMap<>();
            for (int j = 0; j < currency.length; j++) {
                DepositQRLossCommissionVo vo = systemDepositOrderMapper.confirmableFoAmount(currency[j], confirmable[i]);
                hs.put(currency[j], vo);
            }
            hm.put(sss[i], hs);

        }
        HashMap<String, DepositQRLossCommissionVo> hs = new HashMap<>();
        for (int i = 0; i < currency.length; i++) {

            DepositQRLossCommissionVo vo = systemDepositOrderMapper.approvalEtAmount(currency[i]);
            hs.put(currency[i], vo);
        }
        hm.put("approval_external", hs);

        return hm;
    }

}

