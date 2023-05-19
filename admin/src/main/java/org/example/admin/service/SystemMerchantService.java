package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.base.MerchantData;
import org.example.common.dto.*;
import org.example.common.entity.SystemMerchantBankCard;
import org.example.common.entity.SystemMerchantWhiteList;
import org.example.common.vo.*;
import org.example.common.entity.SystemMerchant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
* system_merchant Service 接口
* </p>
*
* @author Administrator
* @since 2023-05-16 14:57:07
*/
@Service
public interface SystemMerchantService extends IService<SystemMerchant> {

    /**
     * 选择账户群组
     * @return
     */
    List<AgentsByCardGroupVo> getGroup();

    /**
     * 选择商户
     * @return
     */
    List<MerchantByAgentByGroupVo> getMerchant();

    /**
     * 选择代理
     * @return
     */
    List<AgentsVo> getAgents();

    /**
     * 商户条件查询返回data信息
     * @param merchantDto
     * @return
     */
    Page<MerchantData> selectData(MerchantDto merchantDto);

    /**
     * 银行信息
     * @param status
     * @return
     */
    List<BrankVo> getBranks(Integer status);

    /**
     * 新增商户信息
     * @param merchantBodyDto
     */
    Long saveMerchant(MerchantBodyDto merchantBodyDto);

    /**
     * 银行账户条件查询返回data信息
     * @param merchant
     * @return
     */
    Page<SystemMerchantBankCard> selectBrandData(MerchantByBrandDto merchant);

    /**
     * 银行账户更新
     * @param merchant
     */
    void updateStatus(MerchantByBrandVo merchant);

    /**
     * 选择商户代理
     * @return
     */
    List<MerchantByAgentByGroupVo> getMerchantByAgent();

    //商户资讯-白名单-查询接口
    Page<SystemMerchantWhiteList> getMerchantByWhite(MerchantByWhiteDto merchantByWhiteDto);

    //商户资讯-白名单-新增接口
    SystemMerchantWhiteList saveWhite(WhiteBodyDto whiteBodyDto);

    /**
     * 白名单更新
     * @param merchantByWhiteVo
     */
    void updateWhite(MerchantByWhiteVo merchantByWhiteVo);
}