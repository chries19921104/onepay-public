package org.example.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.common.dto.MerchantDataDto;

@Mapper
public interface MerchantDataMapper extends BaseMapper<MerchantDataDto> {
}