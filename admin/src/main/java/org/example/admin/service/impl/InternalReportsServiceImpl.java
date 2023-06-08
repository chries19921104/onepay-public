package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.BankAccountListDto;
import org.example.admin.dto.MerchantDto;
import org.example.admin.mapper.InternalReportsMapper;
import org.example.admin.mapper.SystemLogLastPageMapper;
import org.example.admin.mapper.SystemSelectOptionConfigMapper;
import org.example.admin.service.InternalReportsService;
import org.example.admin.vo.BankAccountListVo;
import org.example.common.base.CommResp;
import org.example.common.base.GetNoResp;
import org.example.common.base.MerchantResp;
import org.example.common.base.Totals;
import org.example.common.entity.SystemLogLastPage;
import org.example.common.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public MerchantResp getBankAccountList(HttpServletRequest request, BankAccountListDto bankAccountListDto) {
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
            //其他信息MerchantResp
            return getMerchantResp(bankAccountListVoPage,null,request,bankAccountListDto);
        }
        return GetNoResp.getNoBankAccountListResp(request,bankAccountListDto.getRp());
    }

    @Override
    public CommResp getTransactionScreenRecords(String altId, Integer rp, Integer page) {
        SystemLogLastPage systemLogLastPage = systemLogLastPageMapper.selectOne(new LambdaQueryWrapper<SystemLogLastPage>()
                .eq(SystemLogLastPage::getCommandId, altId));

        return CommResp.data(systemLogLastPage);
    }

    private MerchantResp getMerchantResp(Page<BankAccountListVo> bankAccountListVoPage, Totals totals, HttpServletRequest request, BankAccountListDto bankAccountListDto) {
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
        merchantResp.setPer_page(bankAccountListDto.getRp()+ "");
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
