package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.admin.mapper.SystemWithdrawalOrderMapper;
import org.example.admin.service.SystemSubWithdrawalOrderService;
import org.example.admin.service.SystemWithdrawalOrderService;
import org.example.admin.dto.WithdrawalOrderDto;
import org.example.common.entity.SystemSubWithdrawalOrder;
import org.example.common.entity.SystemWithdrawalOrder;
import org.example.admin.vo.WithdrawalOrderVo;
import org.example.common.exception.MsgException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.common.constant.SystemWithdrawalOrderConstant.*;

@Service
public class SystemWithdrawalOrderServiceImpl extends ServiceImpl<SystemWithdrawalOrderMapper, SystemWithdrawalOrder> implements SystemWithdrawalOrderService {

    @Autowired
    private SystemWithdrawalOrderMapper systemWithdrawalOrderMapper;

    @Autowired
    private SystemSubWithdrawalOrderService systemSubWithdrawalOrderService;

    @Override
    public List<WithdrawalOrderVo> searchByCondition(WithdrawalOrderDto withdrawalOrderDto) {
        // 获取当前页数
        int page = withdrawalOrderDto.getPage() - 1;
        // 获取altId并返回成id
        Long altId = null;
        if (withdrawalOrderDto.getAltId() != null){
            altId = Long.parseLong(withdrawalOrderDto.getAltId().substring(8));
        }
        // 获取子代付altId
        Long subFoAltId = null;
        if (withdrawalOrderDto.getFo110AltId() != null){
            subFoAltId = Long.parseLong(withdrawalOrderDto.getFo110AltId().substring(8));
        }
        // 请求金额最小值
        BigDecimal requestAmountMin = null;
        // 请求金额最大值
        BigDecimal requestAmountMax = null;
        if (withdrawalOrderDto.getRequestAmounts() != null && withdrawalOrderDto.getRequestAmounts().size() > 0){
            requestAmountMin = withdrawalOrderDto.getRequestAmounts().get(0);
            requestAmountMax = withdrawalOrderDto.getRequestAmounts().get(1);
        }

        // 已付金额最小值
        BigDecimal paidAmountMin = null;
        // 已付金额最大值
        BigDecimal paidAmountMax = null;
        if (withdrawalOrderDto.getPaidAmounts() != null && withdrawalOrderDto.getPaidAmounts().size() > 0){
            paidAmountMin = withdrawalOrderDto.getPaidAmounts().get(0);
            paidAmountMax = withdrawalOrderDto.getPaidAmounts().get(1);
        }
        // 查询数据
        List<WithdrawalOrderVo> withdrawalOrderVoList = systemWithdrawalOrderMapper.selectWithdrawalOrderVo(withdrawalOrderDto,
                altId, subFoAltId, requestAmountMin, requestAmountMax, paidAmountMin, paidAmountMax);

        withdrawalOrderVoList = withdrawalOrderVoList.stream().map(item -> {
            WithdrawalOrderVo vo = new WithdrawalOrderVo();
            // 拷贝
            BeanUtils.copyProperties(item, vo);
            // 主键处理
            Long id = item.getFoId();
            String alt_Id = getAltId(id);
            vo.setAltId(alt_Id);
            // 获取confirm_accname字段
            int confirmAccname = item.getConfirmAccname();
            // 处理accNameForConfirm字段
            if (confirmAccname != 1){
                vo.setAccNameForConfirm(new ArrayList<String>());
            }else {
                // 查询子代付中status为7的全部数据
                List<SystemSubWithdrawalOrder> subOrders = systemSubWithdrawalOrderService.list(
                        new LambdaQueryWrapper<SystemSubWithdrawalOrder>()
                                .eq(SystemSubWithdrawalOrder::getFoId, item.getFoId())
                                .eq(SystemSubWithdrawalOrder::getStatus, STATUS_UNMATCHED_ACC_NAME));
                List<String> descriptions = new ArrayList<>();
                if (subOrders != null && subOrders.size() > 0){
                    // 获取全部的description
                    subOrders.forEach(subOrder -> {
                        String description = subOrder.getDescription();
                        descriptions.add(description);
                    });
                }
                // 去重
                List<String> collect = descriptions.stream().distinct().collect(Collectors.toList());
                vo.setAccNameForConfirm(collect);
            }
            // accConfirmDeniable字段 需要子代付表的全部status都为7 4 5 6才为true
            List<SystemSubWithdrawalOrder> subOrderList = systemSubWithdrawalOrderService.list(new LambdaQueryWrapper<SystemSubWithdrawalOrder>()
                    .eq(SystemSubWithdrawalOrder::getFoId, item.getFoId())
                    .notIn(SystemSubWithdrawalOrder::getStatus,
                            STATUS_UNMATCHED_ACC_NAME,
                            STATUS_MANUAL_PROCESS,
                            STATUS_FAILED,
                            STATUS_RETRIED));
            vo.setAccConfirmDeniable(subOrderList == null || subOrderList.size() == 0);
            // 拒绝按钮 1 4 就是true 不为6 8 查询子代付先去重如果剩余结果为1条，则查询代付为1，子代付为1则为true或者代付为2子代付为4或5则也为true
            int status = item.getStatus();
            if (status == STATUS_PENDING || status == STATUS_MANUAL_PROCESS){
                vo.setRejectBtn(true);
                return vo;
            }
            List<SystemSubWithdrawalOrder> subOrders = systemSubWithdrawalOrderService.list(new LambdaQueryWrapper<SystemSubWithdrawalOrder>()
                    .eq(SystemSubWithdrawalOrder::getFoId, item.getFoId())
                    .notIn(SystemSubWithdrawalOrder::getStatus, STATUS_ACC_NAME_CONFIRMED));
            // 获取status数组
            ArrayList<Integer> statuses = new ArrayList<>();
            if (subOrders == null || subOrders.size() == 0){
                subOrders.forEach(subOrder -> statuses.add(subOrder.getStatus()));
            }
            // 去重
            List<Integer> collect = statuses.stream().distinct().collect(Collectors.toList());
            if (collect.size() == 1){
                vo.setRejectBtn(status == STATUS_PROCESSING && collect.get(0) == STATUS_MANUAL_PROCESS || collect.get(0) == STATUS_FAILED);
            }else {
                vo.setRejectBtn(false);
            }
            return vo;
        }).collect(Collectors.toList());

        return withdrawalOrderVoList;
    }

    @Override
    public String download(WithdrawalOrderDto withdrawalOrderDto) {
        // 获取分页数据
        List<WithdrawalOrderVo> orderVoList = searchByCondition(withdrawalOrderDto);
        // 查看数据总量是否超过8000
        if (orderVoList.size() > 8000){
            throw new MsgException("数据量大于8000无法汇出");
        }
        // 创建表格
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建sheet表
        XSSFSheet sheet = workbook.createSheet();
        // 创建表头数据
        ArrayList<String> headList1 = new ArrayList<>();
        headList1.add("Id");
        headList1.add("Merchant Code");
        headList1.add("Bank");
        headList1.add("Beneficiary Name");
        headList1.add("Account Number");
        headList1.add("Requested Amount");
        headList1.add("Paid Amount");
        headList1.add("Fee");
        headList1.add("Bank Fee");
        headList1.add("Bank Fee Bearer");
        headList1.add("Info");
        headList1.add("Status");
        headList1.add("Nt Status");
        headList1.add("Created");
        headList1.add("Success");
        headList1.add("Updated");

        //创建表头，第一行
        createSheetRow(sheet, headList1, 0);

        // 创建表头数据
        ArrayList<String> headList2 = new ArrayList<>();
        headList2.add("id");
        headList2.add("商户号");
        headList2.add("银行");
        headList2.add("受款人");
        headList2.add("账号");
        headList2.add("请求金额");
        headList2.add("已付金额");
        headList2.add("交易费");
        headList2.add("银行手续费");
        headList2.add("银行费用承担");
        headList2.add("资讯");
        headList2.add("状态");
        headList2.add("通知状态");
        headList2.add("创建时间");
        headList2.add("成功");
        headList2.add("更新时间");

        //创建表头，第二行
        createSheetRow(sheet, headList2, 1);

        // 遍历添加数据到表格中
        int rowNum = 2;
        for (WithdrawalOrderVo item : orderVoList){
            ArrayList<String> list = new ArrayList<>();
            list.add(item.getAltId() == null ? "" : item.getAltId());
            list.add(item.getMechantCode() == null ? "" : item.getMechantCode());
            list.add(item.getBankCode() == null ? "" : item.getBankCode());
            list.add(item.getToMan() == null ? "" : item.getToMan());
            list.add(item.getBankNumber () == null ? "" : item.getBankNumber());
            list.add(item.getRequestAmount() == null ? "" : item.getRequestAmount().toString());
            list.add(item.getPaidAmount() == null ? "" : item.getPaidAmount().toString());
            list.add(item.getRate() == null ? "" : item.getRate().toString());
            list.add(item.getBankFee() == null ? "" : item.getBankFee().toString());
            // Bank Fee Bearer 银行费用承担
            Integer payBankFee = item.getPayBankFee();
            if (payBankFee == 0) {
                list.add("one wallet/壹支付");
            } else {
                list.add("merchant/商户");
            }
            // Info 资讯
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss");
            String reference = item.getReference() == null ? "" : item.getReference();
            String note = item.getNote();
            String checkedAt = item.getCheckedAt().format(format);
            String checkedMan = item.getCheckedMan();
            String confirmedAt = item.getConfirmedAt().format(format);
            String confirmedMan = item.getConfirmedMan();
            String info = "MOID/商户订单号：" + reference + "\n\n" + "Note/备注：" + note;
            info = checkedAt != null ? (info + "\n\n" + checkedAt + "\n" + checkedMan + "Pass by merchant/由商户确认) ") : info;
            info = confirmedAt != null ? (info + "\n\n" + confirmedAt + "\n" + (checkedAt != null ? "Merchant-" : "") + confirmedMan + "：Pass/通过") : info;
            list.add(info);
            // 状态
            int status = item.getStatus();
            switch (status){
                case STATUS_PENDING:
                    list.add("Pending/等待中");
                    break;
                case STATUS_PROCESSING:
                    list.add("Processing/处理中");
                    break;
                case STATUS_COMPLETED:
                    list.add("Success/成功");
                    break;
                case STATUS_MANUAL_PROCESS:
                    list.add("STATUS_MANUAL_PROCESS");
                    break;
                case STATUS_FAILED:
                    list.add("Failed/失败");
                    break;
            }
            // 通知状态
            String noticeResult = item.getNoticeResult();
            String noticeContent = item.getNoticeContent();
            LocalDateTime noticeRecordUpdatedAt = item.getNoticeRecordUpdatedAt();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm-ss");
            String updateAt = noticeRecordUpdatedAt.format(formatter);
            StringBuilder ntStatus = new StringBuilder();
            if (noticeResult != null){
                ntStatus.append("on100.result_");
                ntStatus.append(noticeResult);
                ntStatus.append("\n");
                ntStatus.append(noticeContent);
                ntStatus.append("\n");
                ntStatus.append(updateAt);
            }
            list.add(ntStatus.toString());
            // 创建时间
            list.add(item.getCreatedAt().format(formatter));
            // 成功
            list.add(item.getCompletedAt().format(formatter));
            // 更新时间
            list.add(item.getUpdatedAt().format(formatter));
            createSheetRow(sheet, list, rowNum);
            rowNum++;
        }
        File file = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String format = LocalDate.now().format(formatter);
            file = File.createTempFile(format +"-FO-", ".xlsx");
        } catch (IOException e) {
            log.error("创建临时文件失败，错误信息：{}", e);
            throw new MsgException("创建临时文件失败");
        }
        try(FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (Exception e) {
            log.error("保存文件到本地失败，错误信息{}", e);
            throw new MsgException("保存文件到本地失败");
        }
        // 文件名
        String fileName = file.getName();
        return fileName;
    }

    @Override
    public Integer getTotal(WithdrawalOrderDto withdrawalOrderDto) {
        // 获取altId并返回成id
        Long altId = null;
        if (withdrawalOrderDto.getAltId() != null){
            altId = Long.parseLong(withdrawalOrderDto.getAltId().substring(8));
        }
        // 获取子代付altId
        Long subFoAltId = null;
        if (withdrawalOrderDto.getFo110AltId() != null){
            subFoAltId = Long.parseLong(withdrawalOrderDto.getFo110AltId().substring(8));
        }
        // 请求金额最小值
        BigDecimal requestAmountMin = null;
        // 请求金额最大值
        BigDecimal requestAmountMax = null;
        if (withdrawalOrderDto.getRequestAmounts() != null && withdrawalOrderDto.getRequestAmounts().size() > 0){
            requestAmountMin = withdrawalOrderDto.getRequestAmounts().get(0);
            requestAmountMax = withdrawalOrderDto.getRequestAmounts().get(1);
        }

        // 已付金额最小值
        BigDecimal paidAmountMin = null;
        // 已付金额最大值
        BigDecimal paidAmountMax = null;
        if (withdrawalOrderDto.getPaidAmounts() != null && withdrawalOrderDto.getPaidAmounts().size() > 0){
            paidAmountMin = withdrawalOrderDto.getPaidAmounts().get(0);
            paidAmountMax = withdrawalOrderDto.getPaidAmounts().get(1);
        }
        Integer total = systemWithdrawalOrderMapper.selectTotal(withdrawalOrderDto,
                altId, subFoAltId, requestAmountMin, requestAmountMax, paidAmountMin, paidAmountMax);
        return total;
    }

    @Override
    public List<WithdrawalOrderVo> getWithdrawalOrderVoByFoId(Long foId) {
        // 获取数据
        List<WithdrawalOrderVo> orderVoList = systemWithdrawalOrderMapper.selectWithdrawalOrderVo(new WithdrawalOrderDto(),
                foId, null, null, null, null, null);
        orderVoList = orderVoList.stream().map(item -> {
            WithdrawalOrderVo vo = new WithdrawalOrderVo();
            BeanUtils.copyProperties(item, vo);
            vo.setSubBalance(null);
            vo.setBankFeeMerchant(0);
            // 获取forceSubManual
            Integer forceSubManual = item.getForceSubManual();
            Boolean force = forceSubManual == 0 ? false : true;
            vo.setForce(force);
            // 状态
            // 查询子代付表
            List<SystemSubWithdrawalOrder> list = systemSubWithdrawalOrderService.list(
                    new LambdaQueryWrapper<SystemSubWithdrawalOrder>()
                            .eq(SystemSubWithdrawalOrder::getFoId, foId)
                            .eq(SystemSubWithdrawalOrder::getStatus, STATUS_FAILED));
            int status = item.getStatus();
            if (status == STATUS_COMPLETED || status == STATUS_FAILED){
                vo.setAddDisplay(0);
            }else if (force || list != null || list.size() > 0){
                vo.setAddDisplay(1);
            }
            return vo;
        }).collect(Collectors.toList());
        return orderVoList;
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
        String altId = "W-" + format + id;
        return altId;
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
}
