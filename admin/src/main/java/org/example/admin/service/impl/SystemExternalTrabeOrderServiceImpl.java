package org.example.admin.service.impl;


import org.example.admin.dto.SystemExternalTrabeOrderDto;
import org.example.admin.mapper.SystemExternalTrabeOrderMapper;
import org.example.admin.service.SystemExternalTrabeOrderService;
import org.example.admin.vo.SystemExternalTrabeOrderVo;
import org.example.common.base.MerchantResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemExternalTrabeOrderServiceImpl implements SystemExternalTrabeOrderService {

    @Autowired
    private SystemExternalTrabeOrderMapper systemExternalTrabeOrderMapper;

    @Override
    public MerchantResp getAll(SystemExternalTrabeOrderDto dto) {
        List<SystemExternalTrabeOrderVo> all = systemExternalTrabeOrderMapper.getAll(dto);
        MerchantResp merchantResp = new MerchantResp();
        merchantResp.setData(all);
        return merchantResp;
    }
}
