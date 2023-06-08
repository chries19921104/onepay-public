package org.example.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.admin.dto.BankAccountListDto;
import org.example.admin.vo.BankAccountListVo;

import java.util.List;

/**
 * @author Txd
 * @since 2023-06-06 16:56:38
 */

@Mapper
public interface InternalReportsMapper {


    List<BankAccountListVo> getBankAccountList(BankAccountListDto bankAccountListDto);
}
