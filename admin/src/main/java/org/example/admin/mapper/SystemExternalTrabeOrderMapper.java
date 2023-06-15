package org.example.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.admin.dto.SystemExternalTrabeOrderDto;
import org.example.admin.vo.SystemExternalTrabeOrderVo;

import java.util.List;

@Mapper
public interface SystemExternalTrabeOrderMapper {

    List<SystemExternalTrabeOrderVo> getAll(SystemExternalTrabeOrderDto dto);
}
