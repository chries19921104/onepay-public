package org.example.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.dto.MerchantDataDto;
import org.example.common.dto.*;
import org.example.common.entity.SystemMerchantBankCard;
import org.example.common.entity.SystemMerchantOperateLog;
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
     * 选择商户
     * @return
     */
    List<MerchantByAgentByGroupVo> getMerchant();

    /**
     * 商户条件查询返回data信息
     * @param merchantDto
     * @return
     */
    Page<MerchantDataDto> selectData(MerchantDto merchantDto);

    /**
     * 新增商户信息
     * @param merchantBodyDto
     */
    SystemMerchant saveMerchant(MerchantBodyDto merchantBodyDto);

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

    //商户资讯-白名单-查询接口
    Page<SystemMerchantWhiteList> getMerchantByWhite(MerchantByWhiteDto merchantByWhiteDto);

    //商户资讯-白名单-新增接口
    SystemMerchantWhiteList saveWhite(WhiteBodyDto whiteBodyDto);

    /**
     * 白名单更新
     * @param merchantByWhiteVo
     */
    void updateWhite(MerchantByWhiteVo merchantByWhiteVo);

    //商户列表-详情-商户资讯-重置密码接口
    String rechargePassword(Long id);

    //商户列表-详情-商户资讯-重置2FA接口
    void recharge2FA(Long id);

    //商户列表-详情-Log-搜索接口
    Page<SystemMerchantOperateLog> getMerchantByLog(MerchantByLogDto merchantByLogDto);

    //商户资讯-商户列表-单个详情
    MerchantVo selectMerchantById(Long id);

    //商户资讯-商户列表-单个详情编辑
    void updateMerchant(MerchantDto1 merchantDto1);
}