package org.example.merchant.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.base.CommResp;

import org.example.common.entity.Aas100;
import org.example.merchant.mapper.Aas100Dao;
import org.example.merchant.req.Aas100List;
import org.springframework.stereotype.Service;

@Service
public class Aas100ServiceA extends ServiceImpl<Aas100Dao, Aas100> {

    public CommResp pageData(Aas100List req) {

        return CommResp.data(null);
    }

}
