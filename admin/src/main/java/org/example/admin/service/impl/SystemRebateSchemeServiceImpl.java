package org.example.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.dto.RebateSchemeDto;
import org.example.admin.mapper.*;
import org.example.admin.service.SystemRebateSchemeService;
import org.example.admin.vo.AgentsVo;
import org.example.admin.vo.RebateSchemeVo;
import org.example.common.base.CommResp;
import org.example.common.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SystemRebateSchemeServiceImpl extends ServiceImpl<SystemRebateSchemeMapper, SystemRebateScheme> implements SystemRebateSchemeService {

    @Autowired
    private SystemRebateSchemeMapper systemRebateSchemeMapper;

    @Autowired
    private SystemRebateRegularMapper systemRebateRegularMapper;

    @Autowired
    private SystemRebateProgressionMapper systemRebateProgressionMapper;

    @Autowired
    private SystemAgentCommissionSettingsMapper systemAgentCommissionSettingsMapper;

    @Autowired
    private SystemMerchantMapper systemMerchantMapper;

    @Autowired
    private SystemAgentsMapper systemAgentsMapper;


    //代理-返点设置-条件查询
    @Override
    public CommResp getScheme(RebateSchemeDto rebateSchemeDto) {

        LambdaQueryWrapper<SystemRebateScheme> lqw = new LambdaQueryWrapper<>();

        //判断方案名字和方案类型是否为空
        if(rebateSchemeDto.getName()!=null){
            lqw.eq(SystemRebateScheme::getName,rebateSchemeDto.getName());
        }
        if(rebateSchemeDto.getType()!=null){
            lqw.eq(SystemRebateScheme::getType,rebateSchemeDto.getType());
        }

        //使用MP中自带的分页插件，放入获取的当前页和每页显示条数
        Page<SystemRebateScheme> page = new Page<>(rebateSchemeDto.getPage(),rebateSchemeDto.getRp());
        //查询放入page和查询条件lqw
        Page<SystemRebateScheme> systemRebateSchemePage = systemRebateSchemeMapper.selectPage(page, lqw);
        //获取查询结果
        List<SystemRebateScheme> systemRebateSchemeList = systemRebateSchemePage.getRecords();

        if(systemRebateSchemeList.size()==0){
            return CommResp.data(null);
        }

        List<RebateSchemeVo> rebateSchemeVos=systemRebateSchemeList.stream().map(iter -> {
            RebateSchemeVo rebateSchemeVo=new RebateSchemeVo();
            BeanUtils.copyProperties(iter,rebateSchemeVo);

            //判断是什么返点类型，查询对应数据
            if(iter.getType()!=null&&iter.getType()==1){
                LambdaQueryWrapper<SystemRebateProgression> lqw1=new LambdaQueryWrapper<>();
                lqw1.eq(SystemRebateProgression::getRsId,iter.getRsId());
                List<SystemRebateProgression> systemRebateProgressions = systemRebateProgressionMapper.selectList(lqw1);
                rebateSchemeVo.setDetails(systemRebateProgressions);
            }else if(iter.getType()!=null&&iter.getType()==2){
                LambdaQueryWrapper<SystemRebateRegular> lqw1=new LambdaQueryWrapper<>();
                lqw1.eq(SystemRebateRegular::getRsId,iter.getRsId());
                List<SystemRebateRegular> systemRebateRegulars = systemRebateRegularMapper.selectList(lqw1);
                rebateSchemeVo.setDetails(systemRebateRegulars);
            }

            //根据方案类型id查询对应的抽成设定数据
            LambdaQueryWrapper<SystemAgentCommissionSettings> lqw1=new LambdaQueryWrapper<>();
            List<SystemAgentCommissionSettings> systemAgentCommissionSettings =
                    systemAgentCommissionSettingsMapper.selectList(
                            lqw1.and(i->i.eq(SystemAgentCommissionSettings::getBdrsRpId, iter.getRsId())
                            .or().eq(SystemAgentCommissionSettings::getQdrsRpId, iter.getRsId())
                            .or().eq(SystemAgentCommissionSettings::getTwdrsRpId, iter.getRsId())
                            .or().eq(SystemAgentCommissionSettings::getWrsRpId, iter.getRsId()))
                                    .eq(SystemAgentCommissionSettings::getActive,1));

            //根据抽成设定id查询商户资料数据
            LambdaQueryWrapper<SystemMerchant> lqw2=new LambdaQueryWrapper<>();
            List<SystemMerchant> systemMerchantList=new ArrayList<>();
            for(int i=0;i<systemAgentCommissionSettings.size();i++){
                List<SystemMerchant> systemMerchantList1 = systemMerchantMapper.selectList(
                        lqw2.eq(SystemMerchant::getMerchantId, systemAgentCommissionSettings.get(i).getSh100Id())
                );
                for(int j=0;j<systemMerchantList1.size();j++){
                    systemMerchantList.add(systemMerchantList1.get(j));
                }
            }

            //根据商户资料id查询代理数据
            LambdaQueryWrapper<SystemAgents> lqw3=new LambdaQueryWrapper<>();
            HashSet<SystemAgents> systemAgents=new HashSet<>();
            for(int i=0;i<systemMerchantList.size();i++){
                List<SystemAgents> systemAgentsList = systemAgentsMapper.selectList(
                        lqw3.eq(SystemAgents::getAgentId, systemMerchantList.get(i).getAgentId())
                );
                for(int j=0;j<systemAgentsList.size();j++){
                    systemAgents.add(systemAgentsList.get(j));
                }
            }
            rebateSchemeVo.setAgents(systemAgents);


            return rebateSchemeVo;
        }).collect(Collectors.toList());

        Map<String,Object> map=new HashMap<>();
        map.put("current_page",systemRebateSchemePage.getCurrent());
        map.put("total",systemRebateSchemePage.getTotal());
        map.put("Pages",systemRebateSchemePage.getPages());
        map.put("data",rebateSchemeVos);

        return CommResp.data(map);
    }
}
