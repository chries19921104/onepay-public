package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.MonthlyAdjustmentsDto;
import org.example.admin.mapper.SystemAgentsMapper;
import org.example.admin.mapper.SystemMerchantMapper;
import org.example.admin.mapper.SystemMonthlyAdjustmentsMapper;
import org.example.admin.service.SystemMonthlyAdjustmentsService;
import org.example.admin.vo.MonthlyAdjustmentsVo;
import org.example.common.base.CommResp;
import org.example.common.entity.SystemAgents;
import org.example.common.entity.SystemMerchant;
import org.example.common.entity.SystemMonthlyAdjustments;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SystemMonthlyAdjustmentsServiceImal extends ServiceImpl<SystemMonthlyAdjustmentsMapper, SystemMonthlyAdjustments> implements SystemMonthlyAdjustmentsService {
    @Autowired
    private SystemMonthlyAdjustmentsMapper systemMonthlyAdjustmentsMapper;

    @Autowired
    private SystemMerchantMapper systemMerchantMapper;

    @Autowired
    private SystemAgentsMapper systemAgentsMapper;


    @Override
    public CommResp getAdjustmentsAll(MonthlyAdjustmentsDto monthlyAdjustmentsDto) {

        LambdaQueryWrapper<SystemMonthlyAdjustments> MAlqw=new LambdaQueryWrapper<>();
//        Page<SystemMonthlyAdjustments> page = new Page<>(monthlyAdjustmentsDto.getPage(),monthlyAdjustmentsDto.getRp());

//        if(monthlyAdjustmentsDto.getAgentId()!=null){
//            SystemAgents systemAgents = systemAgentsMapper.selectById(monthlyAdjustmentsDto.getAgentId());
//            if(systemAgents==null){
//                return CommResp.data(null);
//            }
//            List<SystemMerchant> systemMerchantList = systemMerchantMapper.selectList(Mlqw.eq(SystemMerchant::getAgentId, systemAgents.getAgentId()));
//            if(systemMerchantList==null||systemMerchantList.isEmpty()){
//                return CommResp.data(null);
//            }
//            List<SystemMonthlyAdjustments> systemMonthlyAdjustmentsList=new ArrayList<>();
//            Page<SystemMonthlyAdjustments> systemMonthlyAdjustmentsPage=null;
//            for(int i=0;i<systemMerchantList.size();i++){
//                systemMonthlyAdjustmentsPage = systemMonthlyAdjustmentsMapper.selectPage(page,
//                        MAlqw.eq(SystemMonthlyAdjustments::getMerchantId, systemMerchantList.get(i).getMerchantId()));
//
//                List<SystemMonthlyAdjustments> systemMonthlyAdjustments = systemMonthlyAdjustmentsPage.getRecords();
//
//                systemMonthlyAdjustmentsList.addAll(systemMonthlyAdjustments);
//            }
//
//            List<MonthlyAdjustmentsVo> monthlyAdjustmentsVoList = systemMonthlyAdjustmentsList.stream().map(iter -> {
//                MonthlyAdjustmentsVo monthlyAdjustmentsVo = new MonthlyAdjustmentsVo();
//                BeanUtils.copyProperties(iter,monthlyAdjustmentsVo);
//
//                for(int i=0;i<systemMerchantList.size();i++){
//                    if(monthlyAdjustmentsVo.getMerchantId()==systemMerchantList.get(i).getMerchantId()){
//                        monthlyAdjustmentsVo.setMerchantName(systemMerchantList.get(i).getName());
//                        monthlyAdjustmentsVo.setCurrency(systemMerchantList.get(i).getCurrency());
//                        break;
//                    }
//                }
//                monthlyAdjustmentsVo.setAgentId(systemAgents.getDisplayId());
//                monthlyAdjustmentsVo.setAgentName(systemAgents.getFullName());
//
//                return monthlyAdjustmentsVo;
//            }).collect(Collectors.toList());
//            Map<String,Object> map=new HashMap<>();
//            map.put("current_page",systemMonthlyAdjustmentsPage.getCurrent());
//            map.put("total",systemMonthlyAdjustmentsPage.getTotal());
//            map.put("Pages",systemMonthlyAdjustmentsPage.getPages());
//            map.put("data",monthlyAdjustmentsVoList);
//
//            return CommResp.data(map);
//
//        }else if(monthlyAdjustmentsDto.getAgentId()==null&&monthlyAdjustmentsDto.getBelongId()!=null){
//            List<SystemAgents> systemAgentsList = systemAgentsMapper.selectList(Alqw
//                    .eq(SystemAgents::getBelongId, monthlyAdjustmentsDto.getBelongId()));
//            List<SystemMerchant> systemMerchantList=new ArrayList<>();
//            for(int i=0;i<systemAgentsList.size();i++){
//                List<SystemMerchant> systemMerchantList1 = systemMerchantMapper.selectList(Mlqw
//                        .eq(SystemMerchant::getAgentId, systemAgentsList.get(i).getAgentId()));
//                systemMerchantList.addAll(systemMerchantList1);
//            }
//            List<SystemMonthlyAdjustments> systemMonthlyAdjustmentsList=new ArrayList<>();
//            Page<SystemMonthlyAdjustments> systemMonthlyAdjustmentsPage=null;
//            for(int i=0;i<systemMerchantList.size();i++){
//                systemMonthlyAdjustmentsPage = systemMonthlyAdjustmentsMapper.selectPage(page,
//                        MAlqw.eq(SystemMonthlyAdjustments::getMerchantId, systemMerchantList.get(i).getMerchantId()));
//
//                List<SystemMonthlyAdjustments> systemMonthlyAdjustments = systemMonthlyAdjustmentsPage.getRecords();
//
//                systemMonthlyAdjustmentsList.addAll(systemMonthlyAdjustments);
//            }
//            List<MonthlyAdjustmentsVo> monthlyAdjustmentsVoList = systemMonthlyAdjustmentsList.stream().map(iter -> {
//                MonthlyAdjustmentsVo monthlyAdjustmentsVo = new MonthlyAdjustmentsVo();
//                BeanUtils.copyProperties(iter, monthlyAdjustmentsVo);
//
//                for(int i=0;i<systemMerchantList.size();i++){
//                    if(monthlyAdjustmentsVo.getMerchantId()==systemMerchantList.get(i).getMerchantId()){
//                        monthlyAdjustmentsVo.setMerchantName(systemMerchantList.get(i).getName());
//                        monthlyAdjustmentsVo.setCurrency(systemMerchantList.get(i).getCurrency());
//                        for(int j=0;j<systemAgentsList.size();j++){
//                            if(systemAgentsList.get(j).getAgentId()==systemMerchantList.get(i).getAgentId()){
//                                monthlyAdjustmentsVo.setAgentId(systemAgentsList.get(j).getDisplayId());
//                                monthlyAdjustmentsVo.setAgentName(systemAgentsList.get(j).getFullName());
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                }
//                return monthlyAdjustmentsVo;
//            }).collect(Collectors.toList());
//            Map<String,Object> map=new HashMap<>();
//            map.put("current_page",systemMonthlyAdjustmentsPage.getCurrent());
//            map.put("total",systemMonthlyAdjustmentsPage.getTotal());
//            map.put("Pages",systemMonthlyAdjustmentsPage.getPages());
//            map.put("data",monthlyAdjustmentsVoList);
//
//            return CommResp.data(map);
//        }else{
//
//
//
//
//        }


        if(monthlyAdjustmentsDto.getCurrency()!=null&&!monthlyAdjustmentsDto.getCurrency().isEmpty()){
            MAlqw.eq(SystemMonthlyAdjustments::getCurrency,monthlyAdjustmentsDto.getCurrency());
        }else if(monthlyAdjustmentsDto.getCurrency()==null||monthlyAdjustmentsDto.getCurrency().isEmpty()){
            return CommResp.FAIL("币别不能为空");
        }
        if(monthlyAdjustmentsDto.getYear()!=null){
            MAlqw.eq(SystemMonthlyAdjustments::getYear,monthlyAdjustmentsDto.getYear());
        }
        if(monthlyAdjustmentsDto.getMonth()!=null){
            MAlqw.eq(SystemMonthlyAdjustments::getMonth,monthlyAdjustmentsDto.getMonth());
        }
        if(monthlyAdjustmentsDto.getMerchantId()!=null){
            MAlqw.eq(SystemMonthlyAdjustments::getMerchantId,monthlyAdjustmentsDto.getMerchantId());
        }
        //使用MP中自带的分页插件，放入获取的当前页和每页显示条数
        Page<SystemMonthlyAdjustments> page = new Page<>(monthlyAdjustmentsDto.getPage(),monthlyAdjustmentsDto.getRp());
        //查询放入page和查询条件lqw
        Page<SystemMonthlyAdjustments> systemMonthlyAdjustmentsPage =systemMonthlyAdjustmentsMapper.selectPage(page,MAlqw);
        //获取查询结果
        List<SystemMonthlyAdjustments> systemMonthlyAdjustments = systemMonthlyAdjustmentsPage.getRecords();


        if(systemMonthlyAdjustments==null||systemMonthlyAdjustments.isEmpty()){
            return CommResp.data(null);
        }

        if(monthlyAdjustmentsDto.getAgentId()!=null){

            List<MonthlyAdjustmentsVo> collect = systemMonthlyAdjustments.stream()
                    .filter(iter->{
                        SystemMerchant systemMerchant = systemMerchantMapper.selectById(iter.getMerchantId());
                        return systemMerchant.getAgentId()== monthlyAdjustmentsDto.getAgentId();
                    })
                    .map(iter -> {
                        MonthlyAdjustmentsVo monthlyAdjustmentsVo = new MonthlyAdjustmentsVo();
                        SystemMerchant systemMerchant = systemMerchantMapper.selectById(iter.getMerchantId());

                        BeanUtils.copyProperties(iter, monthlyAdjustmentsVo);
                        SystemAgents systemAgents = systemAgentsMapper.selectById(systemMerchant.getAgentId());

                        monthlyAdjustmentsVo.setAgentId(systemAgents.getDisplayId());
                        monthlyAdjustmentsVo.setAgentName(systemAgents.getFullName());
                        monthlyAdjustmentsVo.setMerchantId(systemMerchant.getMerchantId());
                        monthlyAdjustmentsVo.setMerchantName(systemMerchant.getName());
                        monthlyAdjustmentsVo.setCurrency(systemMerchant.getCurrency());

                        return monthlyAdjustmentsVo;
            }).collect(Collectors.toList());

            Page<MonthlyAdjustmentsVo> page1=new Page<>();

            page1.setRecords(collect);
            page1.setTotal(collect.size());
            page1.setCurrent(systemMonthlyAdjustmentsPage.getCurrent());
            page1.setPages(systemMonthlyAdjustmentsPage.getPages());

            Map<String,Object> map=new HashMap<>();
            map.put("current_page",page1.getCurrent());
            map.put("total",page1.getTotal());
            map.put("Pages",page1.getPages());
            map.put("data",collect);
            return CommResp.data(map);
        }else if(monthlyAdjustmentsDto.getAgentId()==null&&monthlyAdjustmentsDto.getBelongId()!=null){
            List<MonthlyAdjustmentsVo> collect = systemMonthlyAdjustments.stream().map(iter -> {
                MonthlyAdjustmentsVo monthlyAdjustmentsVo = new MonthlyAdjustmentsVo();
                List<SystemAgents> systemAgentsList = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                        .eq(SystemAgents::getBelongId, monthlyAdjustmentsDto.getBelongId()));
                SystemMerchant systemMerchant = systemMerchantMapper.selectById(iter.getMerchantId());
                for (int i = 0; i < systemAgentsList.size(); i++) {
                    if (systemMerchant.getAgentId() == systemAgentsList.get(i).getAgentId()) {
                        BeanUtils.copyProperties(iter, monthlyAdjustmentsVo);
                        monthlyAdjustmentsVo.setAgentId(systemAgentsList.get(i).getDisplayId());
                        monthlyAdjustmentsVo.setAgentName(systemAgentsList.get(i).getFullName());
                        monthlyAdjustmentsVo.setMerchantId(systemMerchant.getMerchantId());
                        monthlyAdjustmentsVo.setMerchantName(systemMerchant.getName());
                        monthlyAdjustmentsVo.setCurrency(systemMerchant.getCurrency());
                        return monthlyAdjustmentsVo;
                    }
                }
                return null;
            }).collect(Collectors.toList());
            Map<String,Object> map=new HashMap<>();
            map.put("current_page",systemMonthlyAdjustmentsPage.getCurrent());
            map.put("total",systemMonthlyAdjustmentsPage.getTotal());
            map.put("Pages",systemMonthlyAdjustmentsPage.getPages());
            map.put("data",collect);
            return CommResp.data(map);
        }else{
            List<MonthlyAdjustmentsVo> collect = systemMonthlyAdjustments.stream().map(iter -> {
                MonthlyAdjustmentsVo monthlyAdjustmentsVo = new MonthlyAdjustmentsVo();
                SystemMerchant systemMerchant = systemMerchantMapper.selectById(iter.getMerchantId());
                SystemAgents systemAgents = systemAgentsMapper.selectById(systemMerchant.getAgentId());


                BeanUtils.copyProperties(iter,monthlyAdjustmentsVo);

                monthlyAdjustmentsVo.setAgentId(systemAgents.getDisplayId());
                monthlyAdjustmentsVo.setAgentName(systemAgents.getFullName());
                monthlyAdjustmentsVo.setMerchantId(systemMerchant.getMerchantId());
                monthlyAdjustmentsVo.setMerchantName(systemMerchant.getName());
                monthlyAdjustmentsVo.setCurrency(systemMerchant.getCurrency());

                return monthlyAdjustmentsVo;
            }).collect(Collectors.toList());
            Map<String,Object> map=new HashMap<>();
            map.put("current_page",systemMonthlyAdjustmentsPage.getCurrent());
            map.put("total",systemMonthlyAdjustmentsPage.getTotal());
            map.put("Pages",systemMonthlyAdjustmentsPage.getPages());
            map.put("data",collect);
            return CommResp.data(map);
        }
    }
}
