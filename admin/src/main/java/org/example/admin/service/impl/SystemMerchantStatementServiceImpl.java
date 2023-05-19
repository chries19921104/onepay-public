package org.example.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.dto.SystemMerchantStatementSearchDTO;
import org.example.common.entity.SystemMerchantStatement;
import org.example.admin.service.SystemMerchantStatementService;
import org.example.admin.mapper.SystemMerchantStatementMapper;
import org.example.common.vo.SystemMerchantStatementVO;
import org.springframework.stereotype.Service;

/**
* @author 26708
* @description 针对表【system_merchant_statement(商户对账单表)】的数据库操作Service实现
* @createDate 2023-05-19 15:19:36
*/
@Service
public class SystemMerchantStatementServiceImpl extends ServiceImpl<SystemMerchantStatementMapper, SystemMerchantStatement>
    implements SystemMerchantStatementService{

    /**
     * 分页查询
     * @param systemMerchantStatementVOS
     * @param dto
     * @return
     */
    @Override
    public Page<SystemMerchantStatementVO> search(Page<SystemMerchantStatementVO> systemMerchantStatementVOS, SystemMerchantStatementSearchDTO dto) {
        return this.baseMapper.search(systemMerchantStatementVOS,dto);
    }
}




