package org.example.admin.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.example.common.dto.SystemMerchantStatementSearchDTO;
import org.example.common.entity.SystemMerchantStatement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.common.vo.SystemMerchantStatementVO;

/**
* @author 26708
* @description 针对表【system_merchant_statement(商户对账单表)】的数据库操作Mapper
* @createDate 2023-05-19 15:19:36
* @Entity org.example.common.entity.SystemMerchantStatement
*/
public interface SystemMerchantStatementMapper extends BaseMapper<SystemMerchantStatement> {

    // 分页查询
    Page<SystemMerchantStatementVO> search(Page<SystemMerchantStatementVO> systemMerchantStatementVOS, @Param(value = "dto") SystemMerchantStatementSearchDTO dto);

}




