package org.example.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.example.admin.dto.SystemExternalTrabeOrderDto;
import org.example.admin.vo.SystemExternalTrabeOrderVo;
import org.example.common.base.MerchantResp;
import org.example.common.entity.SystemExternalTrabeOrder;
import org.springframework.stereotype.Service;


@Service
public interface SystemExternalTrabeOrderService{

    MerchantResp getAll(SystemExternalTrabeOrderDto dto);

//    MerchantResp getAll(SystemExternalTrabeOrderVo externalTrabeOrderVo);
}

