package org.example.admin.service.impl;

import cn.hutool.core.util.PageUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.micrometer.core.instrument.binder.BaseUnits;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.example.admin.dto.AgentDto;
import org.example.admin.mapper.AdminsMapper;
import org.example.admin.mapper.SystemAgentsMapper;
import org.example.admin.service.SystemAgentsService;
import org.example.admin.vo.BankGroupVo;
import org.example.common.base.CommResp;
import org.example.common.entity.Admins;
import org.example.common.entity.SystemAgents;
import org.example.admin.vo.AgentsVo;
import org.example.common.utils.BaseContext;
import org.example.common.utils.PageUtils;
import org.example.common.utils.RandomStringGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* <p>
* system_agents Service 接口实现
* </p>
*
* @author Administrator
* @since 2023-05-25 10:49:41
*/
@Service
@Transactional
@Slf4j
public class SystemAgentsServiceImpl extends ServiceImpl<SystemAgentsMapper, SystemAgents> implements SystemAgentsService {

    @Autowired
    private SystemAgentsMapper systemAgentsMapper;

    @Autowired
    private AdminsMapper adminsMapper;

    private RandomStringGenerator randomStringGenerator;

    /**
     * 选择代理
     * @return
     */
    @Override
    public List<AgentsVo> getAgents() {
        List<SystemAgents> systemAgents = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                .eq(SystemAgents::getStatus, 1));
        List<AgentsVo> collect = systemAgents.stream().map(iter -> {
            AgentsVo agentsVo = new AgentsVo();
            BeanUtils.copyProperties(iter, agentsVo);
            return agentsVo;
        }).collect(Collectors.toList());
        return collect;
    }

    //查询所有数据或条件查询
    @Override
    public CommResp getAgentsAll(AgentDto agentDto) {
//        List<SystemAgents> systemAgentsList=systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
//                .eq(SystemAgents::getAgentId,agentDto.getAgentId())
//                .eq(SystemAgents::getBelongId,agentDto.getBelongId())
//                .eq(SystemAgents::getStatus,agentDto.getStatus())
//                .eq(SystemAgents::getFullName,agentDto.getFullName()));


        LambdaQueryWrapper<SystemAgents> lqw=new LambdaQueryWrapper<>();

        //判断查询所需要的条件
        if(agentDto.getAgentId()!=null && agentDto.getAgentId()!=0){
            lqw.eq(SystemAgents::getAgentId,agentDto.getAgentId());
        }
        if(agentDto.getDisplayId()!=null){
            lqw.eq(SystemAgents::getDisplayId,agentDto.getDisplayId());
        }
        if(agentDto.getBelongId()!=null){
            lqw.eq(SystemAgents::getBelongId,agentDto.getBelongId());
        }
        if(agentDto.getStatus()!=null){
            lqw.eq(SystemAgents::getStatus,agentDto.getStatus());
        }
        if(agentDto.getFullName()!=null){
            lqw.eq(SystemAgents::getFullName,agentDto.getFullName());
        }

        //使用MP中自带的分页插件，放入获取的当前页和每页显示条数
        Page<SystemAgents> page = new Page<>(agentDto.getPage(),agentDto.getRp());
        //查询放入page和查询条件lqw
        Page<SystemAgents> systemAgentsPage = systemAgentsMapper.selectPage(page, lqw);
        //获取查询结果
        List<SystemAgents> systemAgentsList = systemAgentsPage.getRecords();

        //查询的结果为空直接返回
        if(systemAgentsList.size()==0){
            return CommResp.data(null);
        }

        //把查询的结果复制进返回的实体类中，再加入集合里
        List<AgentsVo> agentsVoList=systemAgentsList.stream().map(iter -> {
            AgentsVo agentsVo=new AgentsVo();
            BeanUtils.copyProperties(iter,agentsVo);
            return agentsVo;
        }).collect(Collectors.toList());

        Map<String,Object> map=new HashMap<>();

        map.put("current_page",systemAgentsPage.getCurrent());
        map.put("total",systemAgentsPage.getTotal());
        map.put("Pages",systemAgentsPage.getPages());
        map.put("data",agentsVoList);

        return CommResp.data(map);
    }

    //查询无身份的数据
    @Override
    public CommResp getAgentsNoIdentity(AgentDto agentDto) {
        QueryWrapper<SystemAgents> qw=new QueryWrapper<>();
        //数据库要查询为空的字段
        qw.isNull("identity");

        Page<SystemAgents> page = new Page<>(agentDto.getPage(),agentDto.getRp());
        Page<SystemAgents> systemAgentsPage = systemAgentsMapper.selectPage(page, qw);
        List<SystemAgents> systemAgentsList = systemAgentsPage.getRecords();

        //查询的结果为空直接返回
        if(systemAgentsList.size()==0){
            return null;
        }

        //把查询的结果复制进返回的实体类中，再加入集合里
        List<AgentsVo> agentsVoList=systemAgentsList.stream().map(iter -> {
            AgentsVo agentsVo=new AgentsVo();
            BeanUtils.copyProperties(iter,agentsVo);
            return agentsVo;
        }).collect(Collectors.toList());

        Map<String,Object> map=new HashMap<>();

        map.put("current_page",systemAgentsPage.getCurrent());
        map.put("total",systemAgentsPage.getTotal());
        map.put("Pages",systemAgentsPage.getPages());
        map.put("data",agentsVoList);

        return CommResp.data(map);
    }

    //代理-代理列表-新增
    @Override
    public CommResp InsertAgent(AgentDto agentDto) {

        if(agentDto.getUsername()==null||agentDto.getUsername().isEmpty()){
            return CommResp.FAIL("代理账号不能为空");
        }
        if(agentDto.getDisplayId()==null||agentDto.getDisplayId().isEmpty()){
            return CommResp.FAIL("代理ID不能为空");
        }
        if(agentDto.getFullName()==null||agentDto.getFullName().isEmpty()){
            return CommResp.FAIL("代理名称不能为空");
        }
        if(agentDto.getStatus()==null||!(agentDto.getStatus()==0||agentDto.getStatus()==1)){
            return CommResp.FAIL("状态必须为启用或停用");
        }

        if(agentDto.getIdentity()!=null && agentDto.getBelongId()==null && agentDto.getIdentity()==2){
            return CommResp.FAIL("所属总代不能为空");
        }else if(agentDto.getIdentity()!=null && agentDto.getBelongId()!=null && agentDto.getIdentity()==2){
            List<SystemAgents> systemAgentsList = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                    .eq(SystemAgents::getAgentId, agentDto.getBelongId()));
            if(systemAgentsList==null || systemAgentsList.isEmpty()){
                return CommResp.FAIL("所属总代不存在");
            }
        }else if(agentDto.getIdentity()!=null && agentDto.getIdentity()==1){
            agentDto.setBelongId(null);
        }

        SystemAgents systemAgents=new SystemAgents();

        List<SystemAgents> systemAgentsList1 = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                .eq(SystemAgents::getDisplayId, agentDto.getDisplayId()));
        List<SystemAgents> systemAgentsList2 = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                .eq(SystemAgents::getUsername, agentDto.getUsername()));
        if(systemAgentsList1!=null&&!systemAgentsList1.isEmpty()){
            return CommResp.FAIL("代理ID已存在，请重新输入！");
        }
        if(systemAgentsList2!=null&&!systemAgentsList2.isEmpty()){
            return CommResp.FAIL("用户名已存在，请重新输入！");
        }

        systemAgents.setDisplayId(agentDto.getDisplayId());
        systemAgents.setFullName(agentDto.getFullName());
        systemAgents.setIdentity(agentDto.getIdentity());
        systemAgents.setUsername(agentDto.getUsername());

        //生成密码并加密
        String s =randomStringGenerator.generateRandomString();
        String s1 = SecureUtil.md5(s);

        systemAgents.setPassword(s1);
        systemAgents.setStatus(agentDto.getStatus());

        //判断新增用户是代理还是总代，是代理就添加对应的总代
        if(agentDto.getIdentity()!=null && agentDto.getIdentity()==2){
            systemAgents.setBelongId(agentDto.getBelongId());
        }

        //获取登陆管理员的id并查询对应数据
        Long userId = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(userId);

        systemAgents.setCreatedAt(LocalDateTime.now());
        systemAgents.setUpdatedAt(LocalDateTime.now());
        //把管理员的用户名添加进创建者和更新者中
        systemAgents.setCreator(admins.getUsername());
        systemAgents.setUpdater(admins.getUsername());

        int insert = systemAgentsMapper.insert(systemAgents);
        //判断是否插入成功
        if(insert!=1){
            return CommResp.FAIL("添加失败");
        }
        systemAgents.setPassword(s);

        return CommResp.data(systemAgents);
    }

    //代理-代理列表-详情
    @Override
    public CommResp getAgentData(Long id) {
        //查询对应id数据
        SystemAgents systemAgents = systemAgentsMapper.selectById(id);
        //判断是否为空
        if(systemAgents==null){
            return CommResp.FAIL("没有这个数据");
        }else{
            AgentsVo agentsVo= new AgentsVo();
            BeanUtils.copyProperties(systemAgents,agentsVo);
            return CommResp.data(agentsVo);
        }
    }

    //代理-代理列表-详情-编辑
    @Override
    public CommResp updateAgent(Long id,AgentDto agentDto) {
        SystemAgents systemAgents=systemAgentsMapper.selectById(id);

        //判断身份是否为空
        if(systemAgents.getIdentity()==null){
            List<SystemAgents> systemAgentsList = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>()
                    .eq(SystemAgents::getAgentId, agentDto.getBelongId()));
            if(systemAgentsList==null||systemAgentsList.isEmpty()){
                return CommResp.FAIL("所属总代不存在");
            }
            BeanUtils.copyProperties(agentDto,systemAgents);
        }else{
            //身份不为空，则把身份和所属总代改为null
            BeanUtils.copyProperties(agentDto,systemAgents);
            systemAgents.setIdentity(null);
            systemAgents.setBelongId(null);
        }

        if(agentDto.getFullName()!=null&&agentDto.getFullName().length()>255){
            return CommResp.FAIL("代理名称过长");
        }
        if(agentDto.getPassword()!=null&&agentDto.getPassword().length()<6){
            return CommResp.FAIL("密码太短了，至少六位");
        }
        if(agentDto.getStatus()==null||!(agentDto.getStatus()==1||agentDto.getStatus()==0)){
            return CommResp.FAIL("状态必须为启用或停用");
        }

        Long current = BaseContext.getCurrent();
        Admins admins = adminsMapper.selectById(current);

        systemAgents.setAgentId(id);
        systemAgents.setUpdater(admins.getUsername());
        systemAgents.setUpdatedAt(LocalDateTime.now());

        int i = systemAgentsMapper.updateById(systemAgents);
        if(i!=1){
            return CommResp.FAIL("修改失败");
        }

        AgentsVo agentsVo=new AgentsVo();
        BeanUtils.copyProperties(systemAgents,agentsVo);
        return CommResp.data(agentsVo);

    }

    //代理-代理列表-详情-重置密码
    @Override
    public CommResp updateAgentPassword(Long id) {
        //生成密码并加密
        String s =randomStringGenerator.generateRandomString();
        String s1 = SecureUtil.md5(s);

        SystemAgents systemAgents=new SystemAgents();
        systemAgents.setAgentId(id);
        systemAgents.setPassword(s1);

        int i = systemAgentsMapper.updateById(systemAgents);
        if(i!=1){
            return null;
        }
        Map<String,String> map=new HashMap<>();
        map.put("password",s);

        return CommResp.data(map);
    }

    @Override
    public CommResp getAgentDataSelect(Long id) {
        SystemAgents systemAgents = systemAgentsMapper.selectById(id);



        return null;
    }


}