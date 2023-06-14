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
import org.example.common.utils.BaseContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

@Autowired
private AdminsMapper adminsMapper;


//代理-返点设置-条件查询
@Override
public CommResp getScheme(RebateSchemeDto rebateSchemeDto) {

    LambdaQueryWrapper<SystemRebateScheme> lqw = new LambdaQueryWrapper<>();

    //判断方案名字和方案类型是否为空
    if (rebateSchemeDto.getName() != null) {
        lqw.eq(SystemRebateScheme::getName, rebateSchemeDto.getName());
    }
    if (rebateSchemeDto.getType() != null) {
        lqw.eq(SystemRebateScheme::getType, rebateSchemeDto.getType());
    }

    //使用MP中自带的分页插件，放入获取的当前页和每页显示条数
    Page<SystemRebateScheme> page = new Page<>(rebateSchemeDto.getPage(), rebateSchemeDto.getRp());
    //查询放入page和查询条件lqw
    Page<SystemRebateScheme> systemRebateSchemePage = systemRebateSchemeMapper.selectPage(page, lqw);
    //获取查询结果
    List<SystemRebateScheme> systemRebateSchemeList = systemRebateSchemePage.getRecords();

    if (systemRebateSchemeList.size() == 0) {
        return CommResp.data(null);
    }

    List<RebateSchemeVo> rebateSchemeVos = systemRebateSchemeList.stream().map(iter -> {
        RebateSchemeVo rebateSchemeVo = new RebateSchemeVo();
        BeanUtils.copyProperties(iter, rebateSchemeVo);

        //判断是什么返点类型，查询对应数据
        if (iter.getType() != null && iter.getType() == 1) {
            LambdaQueryWrapper<SystemRebateProgression> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(SystemRebateProgression::getRsId, iter.getRsId());
            List<SystemRebateProgression> systemRebateProgressions = systemRebateProgressionMapper.selectList(lqw1);
            rebateSchemeVo.setDetails(systemRebateProgressions);
        } else if (iter.getType() != null && iter.getType() == 2) {
            LambdaQueryWrapper<SystemRebateRegular> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(SystemRebateRegular::getRsId, iter.getRsId());
            List<SystemRebateRegular> systemRebateRegulars = systemRebateRegularMapper.selectList(lqw1);
            rebateSchemeVo.setDetails(systemRebateRegulars);
        }

        //根据方案类型id查询对应的抽成设定数据
        LambdaQueryWrapper<SystemAgentCommissionSettings> lqw1 = new LambdaQueryWrapper<>();
        List<SystemAgentCommissionSettings> systemAgentCommissionSettings =
                systemAgentCommissionSettingsMapper.selectList(
                        lqw1.and(i -> i.eq(SystemAgentCommissionSettings::getBdrsRpId, iter.getRsId())
                                        .or().eq(SystemAgentCommissionSettings::getQdrsRpId, iter.getRsId())
                                        .or().eq(SystemAgentCommissionSettings::getTwdrsRpId, iter.getRsId())
                                        .or().eq(SystemAgentCommissionSettings::getWrsRpId, iter.getRsId()))
                                .eq(SystemAgentCommissionSettings::getActive, 1));

        //根据抽成设定id查询商户资料数据
        LambdaQueryWrapper<SystemMerchant> lqw2 = new LambdaQueryWrapper<>();
        List<SystemMerchant> systemMerchantList = new ArrayList<>();
        for (int i = 0; i < systemAgentCommissionSettings.size(); i++) {
            List<SystemMerchant> systemMerchantList1 = systemMerchantMapper.selectList(
                    lqw2.eq(SystemMerchant::getMerchantId, systemAgentCommissionSettings.get(i).getSh100Id())
            );
            for (int j = 0; j < systemMerchantList1.size(); j++) {
                systemMerchantList.add(systemMerchantList1.get(j));
            }
        }

        //根据商户资料id查询代理数据
        LambdaQueryWrapper<SystemAgents> lqw3 = new LambdaQueryWrapper<>();
        HashSet<SystemAgents> systemAgents = new HashSet<>();
        for (int i = 0; i < systemMerchantList.size(); i++) {
            List<SystemAgents> systemAgentsList = systemAgentsMapper.selectList(
                    lqw3.eq(SystemAgents::getAgentId, systemMerchantList.get(i).getAgentId())
            );
            for (int j = 0; j < systemAgentsList.size(); j++) {
                systemAgents.add(systemAgentsList.get(j));
            }
        }
        rebateSchemeVo.setAgents(systemAgents);


        return rebateSchemeVo;
    }).collect(Collectors.toList());

    Map<String, Object> map = new HashMap<>();
    map.put("current_page", systemRebateSchemePage.getCurrent());
    map.put("total", systemRebateSchemePage.getTotal());
    map.put("Pages", systemRebateSchemePage.getPages());
    map.put("data", rebateSchemeVos);

    return CommResp.data(map);
}

//代理-返点设置-详情
@Override
public CommResp getSchemeOne(Long id) {

    SystemRebateScheme systemRebateScheme = systemRebateSchemeMapper.selectById(id);
    if (systemRebateScheme == null) {
        return CommResp.data(null);
    }
    RebateSchemeVo rebateSchemeVo = new RebateSchemeVo();
    BeanUtils.copyProperties(systemRebateScheme, rebateSchemeVo);

    //判断是什么返点类型，查询对应数据表
    if (systemRebateScheme.getType() != null && systemRebateScheme.getType() == 1) {
        LambdaQueryWrapper<SystemRebateProgression> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(SystemRebateProgression::getRsId, systemRebateScheme.getRsId());
        List<SystemRebateProgression> systemRebateProgressions = systemRebateProgressionMapper.selectList(lqw1);
        rebateSchemeVo.setDetails(systemRebateProgressions);
    } else if (systemRebateScheme.getType() != null && systemRebateScheme.getType() == 2) {
        LambdaQueryWrapper<SystemRebateRegular> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(SystemRebateRegular::getRsId, systemRebateScheme.getRsId());
        List<SystemRebateRegular> systemRebateRegulars = systemRebateRegularMapper.selectList(lqw1);
        rebateSchemeVo.setDetails(systemRebateRegulars);
    }

    return CommResp.data(rebateSchemeVo);
}

//代理-返点设置-新增
@Override
public CommResp CreateScheme(RebateSchemeDto rebateSchemeDto) {

    //判断名称是否为空或过长
    if (rebateSchemeDto.getName() == null || rebateSchemeDto.getName().isEmpty()) {
        return CommResp.FAIL("方案名称不能为空");
    }

    String name = rebateSchemeDto.getName();
    if (name.length() > 255) {
        return CommResp.FAIL("名称过长");
    }

    //判断名称是否重复
    LambdaQueryWrapper<SystemRebateScheme> lqw = new LambdaQueryWrapper<>();
    List<SystemRebateScheme> systemRebateSchemeList = systemRebateSchemeMapper.selectList(lqw.eq(SystemRebateScheme::getName, name));
    if (systemRebateSchemeList.size() != 0 || !systemRebateSchemeList.isEmpty()) {
        return CommResp.FAIL("该名称已存在");
    }

    //判断类型是否错误
    if (rebateSchemeDto.getType() == null || !(rebateSchemeDto.getType() == 1 || rebateSchemeDto.getType() == 2)) {
        return CommResp.FAIL("方案类型必须是累进式或固定式");
    }
    SystemRebateScheme sRS = new SystemRebateScheme();
    BeanUtils.copyProperties(rebateSchemeDto, sRS);

    //获取登陆管理员的id并查询对应数据
    Long userId = BaseContext.getCurrent();
    Admins admins = adminsMapper.selectById(userId);


    sRS.setCreatedAt(LocalDateTime.now());
    sRS.setUpdatedAt(LocalDateTime.now());
    //把管理员的用户名添加进创建者和更新者中
    sRS.setCreator(admins.getUsername());
    sRS.setUpdater(admins.getUsername());

    int insert = systemRebateSchemeMapper.insert(sRS);
    if (insert != 1) {
        return CommResp.FAIL("新增失败");
    }

    //把具体方案放进数组中
    Object[] details = rebateSchemeDto.getDetails();

    //获取存入的数据
    List<SystemRebateScheme> systemRebateSchemeList1 = systemRebateSchemeMapper.selectList(new LambdaQueryWrapper<SystemRebateScheme>()
            .eq(SystemRebateScheme::getName, name));

    RebateSchemeVo rebateSchemeVo = new RebateSchemeVo();
    BeanUtils.copyProperties(systemRebateSchemeList1.get(0), rebateSchemeVo);

    if (sRS.getType() != null && sRS.getType() == 1) {
        for (int i = 0; i < details.length; i++) {
            //获取每个方案
            LinkedHashMap<String, String> obj = (LinkedHashMap<String, String>) details[i];
            SystemRebateProgression srp = new SystemRebateProgression();
            //获取方案id
            srp.setRsId(rebateSchemeVo.getRsId());
            //获取开始金额
            if(obj.get("from")==null||obj.get("from").length()==0){
                return CommResp.FAIL("开始金额不能为空");
            }
            srp.setFrom(new BigDecimal(String.valueOf(obj.get("from"))));
            //判断是否为空，不为空则获取结束金额
            if(obj.get("to")==null||obj.get("to").length()==0){
                srp.setTo(null);
            }else{
                srp.setTo(new BigDecimal(String.valueOf(obj.get("to"))));
            }
            //获取反点率
            if(obj.get("rate")==null||obj.get("rate").length()==0){
                return CommResp.FAIL("返点率不能为空");
            }
            srp.setRate(new BigDecimal(String.valueOf(obj.get("rate"))));
            srp.setCreatedAt(LocalDateTime.now());
            srp.setUpdatedAt(LocalDateTime.now());

            int insert1 = systemRebateProgressionMapper.insert(srp);
            if (insert1 != 1) {
                return CommResp.FAIL("添加失败");
            }
        }
    }else if (sRS.getType() != null && sRS.getType() == 2) {
            BigDecimal a=null;
            for (int i = 0; i < details.length; i++) {
                LinkedHashMap<String, String> obj = (LinkedHashMap<String, String>) details[i];

                SystemRebateRegular srr = new SystemRebateRegular();

                srr.setRsId(rebateSchemeVo.getRsId());
                //获取固定值，判断是否为空，为空获取上个的值。
                if(obj.get("volume")==null||obj.get("volume").length()==0){
                    srr.setVolume(new BigDecimal(String.valueOf(a)));
                }else{
                    a=new BigDecimal(String.valueOf(obj.get("volume")));
                    srr.setVolume(new BigDecimal(String.valueOf(obj.get("volume"))));
                }
                //获取返点率
                if(obj.get("rate")==null||obj.get("rate").length()==0){
                    return CommResp.FAIL("返点率不能为空");
                }
                srr.setRate(new BigDecimal(String.valueOf(obj.get("rate"))));

                srr.setCreatedAt(LocalDateTime.now());
                srr.setUpdatedAt(LocalDateTime.now());

                int insert1 = systemRebateRegularMapper.insert(srr);
                if (insert1 != 1) {
                    return CommResp.FAIL("添加失败");
                }
            }
        }


        //判断是什么返点类型，查询对应数据表
        if (rebateSchemeVo.getType() != null && rebateSchemeVo.getType() == 1) {
            LambdaQueryWrapper<SystemRebateProgression> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(SystemRebateProgression::getRsId, rebateSchemeVo.getRsId());
            List<SystemRebateProgression> systemRebateProgressions = systemRebateProgressionMapper.selectList(lqw1);
            rebateSchemeVo.setDetails(systemRebateProgressions);
        } else if (rebateSchemeVo.getType() != null && rebateSchemeVo.getType() == 2) {
            LambdaQueryWrapper<SystemRebateRegular> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(SystemRebateRegular::getRsId, rebateSchemeVo.getRsId());
            List<SystemRebateRegular> systemRebateRegulars = systemRebateRegularMapper.selectList(lqw1);
            rebateSchemeVo.setDetails(systemRebateRegulars);
        }
        return CommResp.data(rebateSchemeVo);
    }
}
