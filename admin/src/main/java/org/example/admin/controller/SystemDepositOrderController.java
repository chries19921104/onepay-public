package org.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.dto.DashboardDto;
import org.example.common.base.CommResp;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.admin.dto.DepositOrderDto;
import org.example.common.exception.MsgException;
import org.example.common.utils.URLUtils;
import org.example.admin.vo.DepositOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

import org.example.admin.service.SystemDepositOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* <p>
* system_deposit_order 控制器实现
* </p>
*
* @author zhangmi
* @since 2023-05-17 19:16:15
*/
@Slf4j
@RestController
@RequestMapping("/api/fi100")
@Api(value = "银行账户交易记录模块-充值子模块", tags = "银行账户交易记录模块-充值子模块")
public class SystemDepositOrderController {

    @Autowired
    private SystemDepositOrderService systemDepositOrderService;

    @GetMapping("/dashboard")
    public CommResp dashboard(DashboardDto dashboardDto) {
        return systemDepositOrderService.selectTxnModeByRegion(dashboardDto);
    }

    @GetMapping("/status/info")
    @NoAuthorization
    public CommResp info(){
      return systemDepositOrderService.infoText();
    }

    @GetMapping
    @ApiOperation(value = "搜索接口")
    @NoAuthorization
    public MerchantResp search(DepositOrderDto depositOrderDto, HttpServletRequest request){
        // 获取分页数据
        Page<DepositOrderVo> orderVoPage = systemDepositOrderService.searchByCondition(depositOrderDto);

        // 封装数据
        MerchantResp merchantResp = MerchantResp.getMerchantResp(request, orderVoPage);
        // 获取Totals
        Totals totals = getTotals(orderVoPage.getRecords());
        merchantResp.setSubtotals(totals);
        merchantResp.setTotals(totals);
        return merchantResp;
    }

    @GetMapping("/download")
    @ApiOperation(value = "汇出报表接口")
    @NoAuthorization
    public String download(DepositOrderDto depositOrderDto, HttpServletRequest request) throws MsgException {
        // 获取文件名
        String fileName = systemDepositOrderService.download(depositOrderDto);
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        url = url.substring(0, url.indexOf("download"));
        return url + "reportDownload/" + fileName;
    }

    @GetMapping("/reportDownload/{fileName}")
    @ApiOperation(value = "汇出报表文件下载")
    @NoAuthorization
    public void reportDownload(@PathVariable String fileName, HttpServletResponse response) throws Exception {
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
        FileInputStream fis = new FileInputStream(file);

        // 读取Excel文件内容
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = fis.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        // 设置响应头，指定文件下载的名称和Content-Type
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        IOUtils.copy(fis, outputStream);

        // 关闭输入流和输出流
        outputStream.close();
        fis.close();;
    }

    /**
     * 获取Totals
     * @param depositOrderVos
     * @return
     */
    private Totals getTotals(List<DepositOrderVo> depositOrderVos) {
        Totals totals = new Totals();
        BigDecimal lossAmount = BigDecimal.ZERO;
        BigDecimal orderAmount = BigDecimal.ZERO;
        BigDecimal paidAmount = BigDecimal.ZERO;
        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal requestAmount = BigDecimal.ZERO;
        //遍历depositOrderVos
        for (DepositOrderVo orderVo : depositOrderVos) {
            // 相加
            lossAmount = lossAmount.add(orderVo.getLossAmount());
            orderAmount = orderAmount.add(orderVo.getOrderAmount());
            paidAmount = paidAmount.add(orderVo.getPaidAmount());
            rate = rate.add(orderVo.getRate());
            requestAmount = requestAmount.add(orderVo.getRequestAmount());
        }
        //将totals数据存储
        totals.setLossAmount(lossAmount);
        totals.setOrderAmount(orderAmount);
        totals.setPaidAmount(paidAmount);
        totals.setRate(rate);
        totals.setRequestAmount(requestAmount);
        return totals;
    }

}
