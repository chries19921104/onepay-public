package org.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.service.SystemMerchantService;
import org.example.common.base.MerchantByBrandResp;
import org.example.common.base.MerchantData;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.common.dto.*;
import org.example.common.entity.SystemMerchant;
import org.example.common.entity.SystemMerchantBankCard;
import org.example.common.entity.SystemMerchantWhiteList;
import org.example.common.utils.URLUtils;
import org.example.common.vo.*;
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
@RequestMapping()
public class SystemMerchantController {

    @Autowired
    private SystemMerchantService systemMerchantService;

    //http://localhost:8088/api/sh100/simple?with=currency,agent_id&with=currency,PG100_ID（选择商户）
    //http://localhost:8088/api/sh100/simple?with=currency,agent_id
    @ApiOperation(value = "有关商户的一些选项列表查询接口，注意不同的请求需要判断with的内容。")
    @GetMapping("/sh100/simple")
    public List<MerchantByAgentByGroupVo> getMerchant(@RequestParam("with") List<String> with) {
        if (with.size() == 1 && "currency,agent_id".equals(with.get(0))){
            //走的第二个请求
            return systemMerchantService.getMerchantByAgent();
        }
        //查询出所有商户资料，相关表为system_merchant
        return systemMerchantService.getMerchant();
    }

    //http://localhost:8088/api/agent/simple(选择代理)
    @ApiOperation(value = "有关代理的一些选项列表查询接口")
    @GetMapping("/agent/simple")
    public List<AgentsVo> getAgents() {
        //查询出所有代理信息，相关表为system_agents
        //返回数据[{id: 1, display_id: "System", username: "System", full_name: "System??\哈嚕", belong_id: null,…},…]
        return systemMerchantService.getAgents();
    }

    //http://localhost:8088/api/pg100/simple?with=currency,model(选择账户群组)
    @GetMapping("/pg100/simple")
    @ApiOperation(value = "有关群组的一些选项列表查询接口")
    public List<AgentsByCardGroupVo> getGroup(String with) {
        //返回的数据类型：{PG100_ID: 1, PG100_name: "DEV-THB", currency: "THB"}
        //里面牵涉表，system_bank_card_group
        if ("currency,model".equals(with)){
            return systemMerchantService.getGroup();
        }
        return null;
    }

    //http://localhost:8088/api/bk100/simple?status=1
    @ApiOperation(value = "有关银行的一些选项列表查询接口")
    @GetMapping("/bk100/simple")
    public List<BrankVo> getBrank(Integer status) {
        return systemMerchantService.getBranks(status);
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
    public MerchantResp selectMerchant(MerchantDto merchantDto, HttpServletRequest request) {
        //data
        Page<MerchantData> merchantData = systemMerchantService.selectData(merchantDto);
        //totals
        Totals totals = getTotals(merchantData.getRecords());
        //其他信息MerchantResp
        return getMerchantResp(merchantData,totals,request,merchantDto);
    }

    //http://localhost:8088/api/sh100
    @ApiOperation(value = "商户资讯-商户列表-新增接口")
    @PostMapping("/sh100")
    public Map<String,MerchantByCreateVo> merchantCreate(@RequestBody MerchantBodyDto merchantBodyDto){
        //将接收的部分信息存贮在merchant表中
        Long merchantId = systemMerchantService.saveMerchant(merchantBodyDto);
        //存储商户与银行对应的数据
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
    public MerchantByBrandResp selectMerchant(MerchantByBrandDto merchantDto, HttpServletRequest request){
        //data
        Page<SystemMerchantBankCard> bankCardPage = systemMerchantService.selectBrandData(merchantDto);
        //MerchantByBrandResp
        return getMerchantByBrandResp(bankCardPage,request,merchantDto);
    }

    //http://localhost:8088/api/sh120/16
    @PostMapping("sh120/{id}")
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
    public MerchantByBrandResp selectMerchantWhite(MerchantByWhiteDto merchantByWhiteDto, HttpServletRequest request){
        //data
        Page<SystemMerchantWhiteList> whiteListPage = systemMerchantService.getMerchantByWhite(merchantByWhiteDto);
        //MerchantByBrandResp
        return getMerchantByWhiteResp(whiteListPage,request,merchantByWhiteDto);
    }

    //http://localhost:8088/api/sh130
    @PostMapping("/sh130")
    @ApiOperation(value = "商户资讯-白名单-新增接口")
    public Map<String,WhiteCreateVo> updateBankCount(@RequestBody WhiteBodyDto whiteBodyDto){
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
    @PostMapping("sh130/{id}")
    @ApiOperation(value = "商户资讯-白名单-更新接口")
    public Map<String,Boolean> updateBankCount(@RequestBody MerchantByWhiteVo merchantByWhiteVo){
        Map<String,Boolean> map = new HashMap<>();
        //更新状态
        systemMerchantService.updateWhite(merchantByWhiteVo);
        map.put("success",true);
        return map;
    }

    private MerchantByBrandResp getMerchantByWhiteResp(Page<SystemMerchantWhiteList> whiteListPage, HttpServletRequest request, MerchantByWhiteDto merchantByWhiteDto) {
        MerchantByBrandResp merchantResp = new MerchantByBrandResp();
        //拷贝属性data
        List<MerchantByWhiteVo> collect = whiteListPage.getRecords().stream().map(iter -> {
            MerchantByWhiteVo merchant = new MerchantByWhiteVo();
            BeanUtils.copyProperties(iter,merchant,"whiteId","creator","ip","status","type");
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

    private MerchantByBrandResp getMerchantByBrandResp(Page<SystemMerchantBankCard> bankCardPage, HttpServletRequest request,MerchantByBrandDto merchantDto) {
        MerchantByBrandResp merchantResp = new MerchantByBrandResp();
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

    private Map<String, MerchantByCreateVo> getMerchantByCreateVo(Long merchantId , MerchantBodyDto merchantBodyDto) {
        //拷贝存在的属性
        MerchantByCreateVo merchant = new MerchantByCreateVo();
        BeanUtils.copyProperties(merchantBodyDto,merchant);
        //补充属性
        merchant.setMerchantId(Long.valueOf(merchantId).intValue());
        merchant.setTpi_settled(true);
        merchant.setUse_qr_pairing_code(true);
//        merchant.setCreated_at();
//        merchant.setCreated_man();
//        merchant.setUpdated_at();
//        merchant.setUpdated_man();
//        merchant.setPassword();
//        merchant.setUser_id();
        Map<String,MerchantByCreateVo> map = new HashMap<>();
        map.put("data",merchant);
        return map;
    }

    private MerchantResp getMerchantResp(Page<MerchantData> merchantData, Totals totals, HttpServletRequest request, MerchantDto merchantDto) {
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

    private Totals getTotals(List<MerchantData> merchantData) {
        Totals totals = new Totals();
        BigDecimal availableBalance = null;
        BigDecimal depositOutstandingBalance= null;
        BigDecimal currentBalance= null;
        BigDecimal holdBalance= null;
        BigDecimal frozenBalance= null;
        BigDecimal todayTrFee= null;
        //遍历merchant
        for (MerchantData merchantDatum : merchantData) {
            //将每个商户的余额相加
            availableBalance = availableBalance.add(merchantDatum.getAvailableBalance());
            depositOutstandingBalance = depositOutstandingBalance.add(merchantDatum.getDepositOutstandingBalance());
            currentBalance = currentBalance.add(merchantDatum.getCurrentBalance());
            holdBalance = holdBalance.add(merchantDatum.getHoldBalance());
            frozenBalance = frozenBalance.add(merchantDatum.getFrozenBalance());
            todayTrFee = todayTrFee.add(merchantDatum.getTodayTrFee());
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
