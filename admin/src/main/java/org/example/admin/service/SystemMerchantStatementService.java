package org.example.admin.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.common.dto.SystemMerchantStatementSearchDTO;
import org.example.common.entity.SystemMerchantStatement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.vo.SystemMerchantStatementVO;

/**
* @author 26708
* @description 针对表【system_merchant_statement(商户对账单表)】的数据库操作Service
* @createDate 2023-05-19 15:19:36
*/
public interface SystemMerchantStatementService extends IService<SystemMerchantStatement> {

    // 分页查询
    Page<SystemMerchantStatementVO> search(Page<SystemMerchantStatementVO> systemMerchantStatementVOS, SystemMerchantStatementSearchDTO dto);
}
