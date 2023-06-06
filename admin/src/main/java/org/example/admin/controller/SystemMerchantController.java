package org.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.dto.*;
import org.example.admin.service.SystemMerchantService;
import org.example.admin.vo.*;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.common.entity.*;
import org.example.common.utils.URLUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* <p>
* system_merchant 控制器实现
* </p>
*
* @author asus
* @since 2023-05-16 14:27:59
*/
@Api(tags = "商户资讯模块")
@RestController
@RequestMapping("/api")
public class SystemMerchantController {

    @Autowired
    private SystemMerchantService systemMerchantService;

    //http://localhost:8088/api/sh100/simple?with=currency,agent_id&with=currency,PG100_ID（选择商户）
    //http://localhost:8088/api/sh100/simple?with=currency,agent_id
    @ApiOperation(value = "有关商户的一些选项列表查询接口，注意不同的请求需要判断with的内容。")
    @GetMapping("/sh100/simple")
    @NoAuthorization
    public List<MerchantByAgentByGroupVo> getMerchant(@RequestParam("with") List<String> with) {
        //查询出所有商户资料，相关表为system_merchant
        return systemMerchantService.getMerchant();
    }

    //http://localhost:8088/api/sh100?
    // SH100_ID[]=151&SH100_ID[]=150&
    // agent_id[]=none&agent_id[]=5&
    // currency=THB&
    // start_date=2020-05-05&
    // end_date=2023-05-17&
    // status[]=1&status[]=0&
    // not_allowed_types[]=1&not_allowed_types[]=2&
    // not_allowed_BK100_ID=&
    // rp=100&page=1&
    // PG100_ID=1
    @GetMapping("/sh100")
    @ApiOperation(value = "商户资讯-商户列表-查询接口")
    @NoAuthorization
    public MerchantResp selectMerchant(MerchantDto merchantDto, HttpServletRequest request) {
        //data
        Page<MerchantDataDto> merchantData = systemMerchantService.selectData(merchantDto);
        //totals
        if (merchantData != null){
            Totals totals = getTotals(merchantData.getRecords());
            //其他信息MerchantResp
            return getMerchantResp(merchantData,totals,request,merchantDto);
        }
        return GetNoResp.getNoMerchantResp(request,merchantDto.getRp());

    }

    //http://localhost:8088/api/sh100
    @ApiOperation(value = "商户资讯-商户列表-新增接口")
    @PostMapping("/sh100")
    public Map<String, MerchantByCreateVo> merchantCreate(@RequestBody MerchantBodyDto merchantBodyDto){
        //将接收的部分信息存贮在merchant表中
        SystemMerchant merchantId = systemMerchantService.saveMerchant(merchantBodyDto);
        //返回数据
        return getMerchantByCreateVo(merchantId,merchantBodyDto);
    }

    //http://localhost:8088/api/sh120?currency=THB&
    // SH100_ID[]=153&SH100_ID[]=152&SH100_ID[]=151&
    // status[]=1&status[]=0&
    // card_number=&
    // start_date=&end_date=&
    // rp=100&page=1
    @ApiOperation(value = "商户资讯-银行账户-查询接口")
    @GetMapping("/sh120")
    public MerchantResp selectMerchant(MerchantByBrandDto merchantDto, HttpServletRequest request){
        //data
        Page<SystemMerchantBankCard> bankCardPage = systemMerchantService.selectBrandData(merchantDto);
        //MerchantByBrandResp
        return getMerchantByBrandResp(bankCardPage,request,merchantDto);
    }

    //http://localhost:8088/api/sh120/16
    @PutMapping("sh120/{id}")
    @ApiOperation(value = "商户资讯-银行账户-更新接口")
    public Map<String,Boolean> updateBankCount(@RequestBody MerchantByBrandVo merchant){
        Map<String,Boolean> map = new HashMap<>();
        //更新状态
        systemMerchantService.updateStatus(merchant);
        map.put("success",true);
        return map;
    }

    //http://localhost:8088/api/sh130?
    // SH100_ID[]=1&SH100_ID[]=2&
    // currency=THB&
    // type[]=4&type[]=2&
    // ip=111&
    // start_date=&end_date=&
    // rp=100&page=1
    @ApiOperation(value = "商户资讯-白名单-查询接口")
    @GetMapping("/sh130")
    public MerchantResp selectMerchantWhite(MerchantByWhiteDto merchantByWhiteDto, HttpServletRequest request){
        //data
        Page<SystemMerchantWhiteList> whiteListPage = systemMerchantService.getMerchantByWhite(merchantByWhiteDto);
        //MerchantResp
        return getMerchantByWhiteResp(whiteListPage,request,merchantByWhiteDto);
    }

    //http://localhost:8088/api/sh130
    @PostMapping("/sh130")
    @ApiOperation(value = "商户资讯-白名单-新增接口")
    public Map<String, WhiteCreateVo> createWhite(@RequestBody WhiteBodyDto whiteBodyDto){
        //新增数据
        SystemMerchantWhiteList merchantWhiteList = systemMerchantService.saveWhite(whiteBodyDto);
        //返回数据
        WhiteCreateVo whiteCreateVo = new WhiteCreateVo();
        BeanUtils.copyProperties(merchantWhiteList,whiteCreateVo);
        Map<String,WhiteCreateVo> map = new HashMap<>();
        map.put("data",whiteCreateVo);
        return map;
    }

    //http://localhost:8088/api/sh130/388
    @PutMapping("sh130/{id}")
    @ApiOperation(value = "商户资讯-白名单-更新接口")
    public Map<String,Boolean> updateWhite(@RequestBody MerchantByWhiteVo merchantByWhiteVo){
        Map<String,Boolean> map = new HashMap<>();
        //更新状态
        systemMerchantService.updateWhite(merchantByWhiteVo);
        map.put("success",true);
        return map;
    }


    //http://localhost:8088/api/sh100/153
    @ApiOperation(value = "商户资讯-商户列表-单个详情")
    @GetMapping("/sh100/{id}")
    public Map<String, MerchantVo> selectMerchantById(@PathVariable("id") Long id){
        //通过id查询数据,返回的数据集包含
        // system_merchant，system_agents，system_merchant_wallet，system_merchant_admin,system_merchant_support_bank五张表的数据
        MerchantVo merchantVo = systemMerchantService.selectMerchantById(id);

        Map<String,MerchantVo> map = new HashMap<>();
        map.put("data",merchantVo);
        return map;
    }

    //http://localhost:8088/api/sh100/153
    @ApiOperation(value = "商户资讯-商户列表-单个详情编辑")
    @PutMapping("/sh100/{id}")
    public Map<String,Object> selectMerchantById(@RequestBody MerchantDto1 merchantDto1){
        //将对应的不同信息存储在不同的五张表中。
        systemMerchantService.updateMerchant(merchantDto1);

        Map<String,Object> map = new HashMap<>();
        map.put("data",merchantDto1);
        map.put("tpi_settled",true);
        return map;
    }


    //http://localhost:8088/api/user/374/password/reset
    @PutMapping("/user/{id}/password/reset")
    @ApiOperation(value = "商户列表-详情-商户资讯-重置密码接口")
    public Map<String,Map<String,String>> rechargePassword(@PathVariable("id") Long id) {
        // 修改system_merchant_admin表
        String password = systemMerchantService.rechargePassword(id);
        Map<String,Map<String,String>> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();
        map1.put("password",password);
        map.put("data",map1);
        return map;
    }

    //http://localhost:8088/api/user/374/totpSecret/reset
    @PutMapping("/user/{id}/totpSecret/reset")
    @ApiOperation(value = "商户列表-详情-商户资讯-重置2FA接口")
    public Map<String,Boolean> recharge2FA(@PathVariable("id") Long id) {
        systemMerchantService.recharge2FA(id);
        Map<String,Boolean> map = new HashMap<>();
        map.put("data",true);
        return map;
    }

    //http://localhost:8088/api/sh100log?
    //SH100_ID=153&
    //type=name&
    //admin_name=admin&
    //rp=100&page=1&start_date=2023-05-01&end_date=2023-05-22
    @ApiOperation(value = "商户列表-详情-Log-搜索接口")
    @GetMapping("/sh100log")
    public MerchantResp selectMerchantLog(MerchantByLogDto merchantByLogDto, HttpServletRequest request){
        Page<SystemMerchantOperateLog> page = systemMerchantService.getMerchantByLog(merchantByLogDto);
        //MerchantResp
        return getMerchantByLogResp(page,request,merchantByLogDto);
    }

    //http://localhost:8088/api/sh100/all?assigned=0&currency=THB
    @ApiOperation(value = "按商户status查询")
    @GetMapping("/sh100/all")
    public List<Merchant1Vo> getMerchantByStatus(MerchantDto merchantDto){
        return systemMerchantService.getMerchantByStatus(merchantDto);
    }

    private MerchantResp getMerchantByLogResp(Page<SystemMerchantOperateLog> page, HttpServletRequest request, MerchantByLogDto merchantByLogDto) {
        MerchantResp merchantResp = new MerchantResp();
        //拷贝属性data
        List<MerchantByLogVo> collect = page.getRecords().stream().map(iter -> {
            MerchantByLogVo merchant = new MerchantByLogVo();
            BeanUtils.copyProperties(iter,merchant);
            return merchant;
        }).collect(Collectors.toList());
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + page.getPages());
        if (page.getPages()>page.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (page.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) page.getCurrent());
        merchantResp.setData(collect);
        if (page.getTotal() != 0){
            merchantResp.setFrom((int) page.getCurrent());
        }
        merchantResp.setLast_page((int) page.getPages());
        merchantResp.setPer_page(merchantByLogDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        if (page.getTotal() != 0){
            merchantResp.setTo((int) page.getTotal());
        }
        merchantResp.setTotal((int) page.getTotal());
        return merchantResp;
    }

    private MerchantResp getMerchantByWhiteResp(Page<SystemMerchantWhiteList> whiteListPage, HttpServletRequest request, MerchantByWhiteDto merchantByWhiteDto) {
        MerchantResp merchantResp = new MerchantResp();
        //拷贝属性data
        List<MerchantByWhiteVo> collect = whiteListPage.getRecords().stream().map(iter -> {
            MerchantByWhiteVo merchant = new MerchantByWhiteVo();
            BeanUtils.copyProperties(iter,merchant);
            //手动将创建时间拷贝一下，类型不一样
            merchant.setCreatedAt(iter.getCreatedAt()+"");
            //再通过商户id去查询商户表，将code和name字段补齐
            SystemMerchant byId = systemMerchantService.getById(iter.getMerchantId());
            merchant.setCode(byId.getCode());
            merchant.setName(byId.getName());
            return merchant;
        }).collect(Collectors.toList());
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + whiteListPage.getPages());
        if (whiteListPage.getPages()>whiteListPage.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (whiteListPage.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) whiteListPage.getCurrent());
        merchantResp.setData(collect);
        if (whiteListPage.getTotal() != 0){
            merchantResp.setFrom((int) whiteListPage.getCurrent());
        }
        merchantResp.setLast_page((int) whiteListPage.getPages());
        merchantResp.setPer_page(merchantByWhiteDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        if (whiteListPage.getTotal() != 0){
            merchantResp.setTo((int) whiteListPage.getTotal());
        }
        merchantResp.setTotal((int) whiteListPage.getTotal());
        return merchantResp;
    }

    private MerchantResp getMerchantByBrandResp(Page<SystemMerchantBankCard> bankCardPage, HttpServletRequest request,MerchantByBrandDto merchantDto) {
        MerchantResp merchantResp = new MerchantResp();
        //拷贝属性data
        List<MerchantByBrandVo> collect = bankCardPage.getRecords().stream().map(iter -> {
            MerchantByBrandVo merchant = new MerchantByBrandVo();
            merchant.setMerchantId(iter.getMerchantId().intValue());
            merchant.setMbId(iter.getMbId().intValue());
            merchant.setAcc_name(iter.getAccName());
            merchant.setBank_code(iter.getBankCode());
            merchant.setBank_name(iter.getBankName());
            merchant.setBranch(iter.getBranch());
            merchant.setCard_number(iter.getCardNumber());
            merchant.setCreated_at(iter.getCreatedAt() + "");
            merchant.setStatus(iter.getStatus());
            //用iter的MerchantId去商户表查询剩余两个字段信息
            SystemMerchant byId = systemMerchantService.getById(iter.getMerchantId());
            merchant.setCode(byId.getCode());
            merchant.setName(byId.getName());
            return merchant;
        }).collect(Collectors.toList());
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + bankCardPage.getPages());
        if (bankCardPage.getPages()>bankCardPage.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (bankCardPage.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) bankCardPage.getCurrent());
        merchantResp.setData(collect);
        if (bankCardPage.getTotal() != 0){
            merchantResp.setFrom((int) bankCardPage.getCurrent());
        }
        merchantResp.setLast_page((int) bankCardPage.getPages());
        merchantResp.setPer_page(merchantDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        if (bankCardPage.getTotal() != 0){
            merchantResp.setTo((int) bankCardPage.getTotal());
        }
        merchantResp.setTotal((int) bankCardPage.getTotal());
        return merchantResp;
    }

    private Map<String, MerchantByCreateVo> getMerchantByCreateVo(SystemMerchant merchantId , MerchantBodyDto merchantBodyDto) {
        //拷贝存在的属性
        MerchantByCreateVo merchant = new MerchantByCreateVo();
        BeanUtils.copyProperties(merchantBodyDto,merchant);
        //补充属性
        BeanUtils.copyProperties(merchantId,merchant);
        merchant.setTpi_settled(true);
        merchant.setUse_qr_pairing_code(true);

        Map<String,MerchantByCreateVo> map = new HashMap<>();
        map.put("data",merchant);
        return map;
    }

    private MerchantResp getMerchantResp(Page<MerchantDataDto> merchantData, Totals totals, HttpServletRequest request, MerchantDto merchantDto) {
        MerchantResp merchantResp = new MerchantResp();
        //
        //获取当前接口的url
        String url = URLUtils.getCurrentURL(request);
        //拼接url
        merchantResp.setFirst_page_url(url + "?page=1");
        merchantResp.setLast_page_url(url + "?page=" + merchantData.getPages());
        if (merchantData.getPages()>merchantData.getCurrent()){
            merchantResp.setNext_page_url(url + "?page=" + (merchantData.getCurrent()+1));
        }
        merchantResp.setPath(url);
        //保存其他信息
        merchantResp.setCurrent_page((int) merchantData.getCurrent());
        merchantResp.setData(merchantData.getRecords());
        if (merchantData.getTotal() != 0){
            merchantResp.setFrom((int) merchantData.getCurrent());
        }
        merchantResp.setLast_page((int) merchantData.getPages());
        merchantResp.setPer_page(merchantDto.getRp()+ "");
        merchantResp.setPrev_page_url(null);
        merchantResp.setSubtotals(totals);
        if (merchantData.getTotal() != 0){
            merchantResp.setTo((int) merchantData.getTotal());
        }
        merchantResp.setTotal((int) merchantData.getTotal());
        merchantResp.setTotals(totals);
        return merchantResp;
    }

    private Totals getTotals(List<MerchantDataDto> merchantData) {
        Totals totals = new Totals();
        BigDecimal availableBalance = BigDecimal.ZERO;
        BigDecimal depositOutstandingBalance= BigDecimal.ZERO;
        BigDecimal currentBalance= BigDecimal.ZERO;
        BigDecimal holdBalance= BigDecimal.ZERO;
        BigDecimal frozenBalance= BigDecimal.ZERO;
        BigDecimal todayTrFee= BigDecimal.ZERO;
        //遍历merchant
        for (MerchantDataDto merchantDatum : merchantData) {
            //将每个商户的余额相加
            if (merchantDatum.getAvailableBalance() != null){
                availableBalance = availableBalance.add(merchantDatum.getAvailableBalance());
            }if (merchantDatum.getDepositOutstandingBalance() != null){
                depositOutstandingBalance = depositOutstandingBalance.add(merchantDatum.getDepositOutstandingBalance());
            }if (merchantDatum.getCurrentBalance() != null){
                currentBalance = currentBalance.add(merchantDatum.getCurrentBalance());
            }if (merchantDatum.getHoldBalance() != null){
                holdBalance = holdBalance.add(merchantDatum.getHoldBalance());
            }if (merchantDatum.getFrozenBalance() != null){
                frozenBalance = frozenBalance.add(merchantDatum.getFrozenBalance());
            }if (merchantDatum.getTodayTrFee() != null){
                todayTrFee = todayTrFee.add(merchantDatum.getTodayTrFee());
            }
        }
        //将totals数据存储
        totals.setCurrentBalance(currentBalance);
        totals.setAvailableBalance(availableBalance);
        totals.setFrozenBalance(frozenBalance);
        totals.setHoldBalance(holdBalance);
        totals.setTodayTrFee(todayTrFee);
        totals.setDepositOutstandingBalance(depositOutstandingBalance);
        return totals;
    }

}
