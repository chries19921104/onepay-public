package org.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.SystemSubWithdrawalOrderService;
import org.example.admin.service.SystemWithdrawalOrderService;
import org.example.admin.vo.SubWithdrawalOrderVo;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.admin.dto.WithdrawalOrderDto;
import org.example.admin.vo.WithdrawalOrderVo;
import org.example.common.exception.MsgException;
import org.example.common.utils.PageUtils;
import org.example.common.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/fo100")
@Api(value = "银行账户交易记录模块-代付子模块", tags = "银行账户交易记录模块-代付子模块")
public class SystemWithdrawalOrderController {

    @Autowired
    private SystemWithdrawalOrderService withdrawalOrderService;

    @Autowired
    private SystemSubWithdrawalOrderService subWithdrawalOrderService;

    @GetMapping
    @ApiOperation(value = "代付搜索接口")
    @NoAuthorization
    public MerchantResp search(WithdrawalOrderDto withdrawalOrderDto, HttpServletRequest request){
        // 获取全部数据
        List<WithdrawalOrderVo> orderVoList = withdrawalOrderService.searchByCondition(withdrawalOrderDto);

        // 封装成Page
        // 查询总条数
        Integer total = withdrawalOrderService.getTotal(withdrawalOrderDto);
        // 封装返回
        Page<WithdrawalOrderVo> orderVoPage = new Page<WithdrawalOrderVo>(withdrawalOrderDto.getPage(), withdrawalOrderDto.getRp());
        // 当前页
        int page = withdrawalOrderDto.getPage();
        // 每页显示数据
        int pageSize = withdrawalOrderDto.getRp();
        List<WithdrawalOrderVo> records = PageUtils.getPageRecords(page, pageSize, orderVoList);
        orderVoPage.setRecords(records);
        if (total == null){
            orderVoPage.setTotal(0);
        }else {
            orderVoPage.setTotal(total);
        }

        // 封装返回数据
        MerchantResp merchantResp = MerchantResp.getMerchantResp(request, orderVoPage);
        // 总计
        // 获取Totals
        Totals totals = getTotals(orderVoList);

        // 获取subTotals 小计
        Totals subTotals = getTotals(records);
        merchantResp.setSubtotals(subTotals);
        merchantResp.setTotals(totals);
        return merchantResp;
    }

    @GetMapping("/download")
    @ApiOperation(value = "代付汇出报表接口")
    @NoAuthorization
    public String download(WithdrawalOrderDto withdrawalOrderDto, HttpServletRequest request) throws MsgException {
        // 获取文件名
        String fileName = withdrawalOrderService.download(withdrawalOrderDto);
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        url = url.substring(0, url.indexOf("download"));
        return url + "reportDownload/" + fileName;
    }

    @GetMapping("/reportDownload/{fileName}")
    @ApiOperation(value = "代付汇出报表文件下载")
    @NoAuthorization
    public void reportDownload(@PathVariable String fileName, HttpServletResponse response) {
        File fileTemp = null;
        try {
            fileTemp = File.createTempFile("file", ".temp");
        } catch (IOException e) {
            log.error("创建临时文件失败，错误信息{}", e.getMessage());
            throw new MsgException("创建临时文件失败");
        }
        // 获取文件路径
        String absolutePath = fileTemp.getAbsolutePath();
        String[] split = absolutePath.split("\\\\");
        StringBuilder filePath = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            filePath.append(split[i] + "\\");
        }
        File file = new File(filePath.toString() + fileName);
        FileInputStream fis = null;
        OutputStream outputStream = null;
        try {
            fis = new FileInputStream(file);

            // 读取Excel文件内容
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            // 设置响应头，指定文件下载的名称和Content-Type
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            IOUtils.copy(fis, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                // 关闭输入流和输出流
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @GetMapping("/{foId}/fo110")
    @ApiOperation(value = "代付详情接口")
    public Map<String, Map<String, Object>> getDetail(@PathVariable Long foId, HttpServletRequest request) {
        // 获取fo100数据
        WithdrawalOrderVo orderVo = withdrawalOrderService.getWithdrawalOrderVoByFoId(foId);
        // 获取fo110的list
        List<SubWithdrawalOrderVo> subOrderVoList = subWithdrawalOrderService.getSubWithdrawalOrderVo(foId);
        MerchantResp merchantResp = new MerchantResp();
        if (subOrderVoList != null){
            Page<SubWithdrawalOrderVo> subOrderPage = new Page<>();
            subOrderPage.setTotal(50);
            subOrderPage.setRecords(subOrderVoList);
            subOrderPage.setCurrent(1);
            subOrderPage.setSize(50);
            merchantResp = MerchantResp.getMerchantResp(request, subOrderPage);
        }
        // 返回的map
        Map<String, Map<String, Object>> map = new HashMap<>();
        // 最内层map
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("FO100", orderVo);
        hashMap.put("FO110", merchantResp);
        map.put("data", hashMap);
        return map;
    }

    /**
     * 获取Totals
     * @param withdrawalOrderVos
     * @return
     */
    private Totals getTotals(List<WithdrawalOrderVo> withdrawalOrderVos) {
        Totals totals = new Totals();
        BigDecimal bankFee = BigDecimal.ZERO;
        BigDecimal paidAmount = BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal requestAmount = BigDecimal.ZERO;
        //遍历depositOrderVos
        for (WithdrawalOrderVo orderVo : withdrawalOrderVos) {
            // 相加
            bankFee = bankFee.add(orderVo.getBankFee());
            paidAmount = paidAmount.add(orderVo.getPaidAmount());
            rate = rate.add(orderVo.getRate());
            requestAmount = requestAmount.add(orderVo.getRequestAmount());
        }
        //将totals数据存储
        totals.setBankFee(bankFee);
        totals.setPaidAmount(paidAmount);
        totals.setRate(rate);
        totals.setRequestAmount(requestAmount);
        return totals;
    }

}
