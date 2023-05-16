package org.example.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.base.CommResp;
import org.example.common.entity.Aas100;
import org.example.admin.req.Aas100List;
import org.example.admin.mapper.Aas100Dao;
import org.springframework.stereotype.Service;

@Service
public class Aas100Service extends ServiceImpl<Aas100Dao, Aas100> implements IAas100Service{

    public CommResp pageData(Aas100List req) {
        QueryWrapper<Aas100> queryWrapper = new QueryWrapper<>();
        if (req.getId() != null) {
            queryWrapper.eq("id", req.getId());
        }
        Page page = new Page(req.getPage(), req.getLimit());
        IPage pageData = page(page, queryWrapper);
        return CommResp.data(pageData);
    }

}
