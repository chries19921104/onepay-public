package org.example.admin.service.impl;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.example.admin.dto.AgentDto;
import org.example.admin.mapper.AdminsMapper;
import org.example.admin.mapper.SystemAgentsMapper;
import org.example.admin.service.SystemAgentsService;
import org.example.admin.vo.BankGroupVo;
import org.example.common.entity.Admins;
import org.example.common.entity.SystemAgents;
import org.example.admin.vo.AgentsVo;
import org.example.common.utils.BaseContext;
import org.example.common.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<AgentsVo> getAgentsAll(AgentDto agentDto) {
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
            return null;
        }

        //把查询的结果复制进返回的实体类中，再加入集合里
        List<AgentsVo> agentsVoList=systemAgentsList.stream().map(iter -> {
            AgentsVo agentsVo=new AgentsVo();
            BeanUtils.copyProperties(iter,agentsVo);
            return agentsVo;
        }).collect(Collectors.toList());

        return agentsVoList;
    }

    //查询无身份的数据
    @Override
    public List<AgentsVo> getAgentsNoIdentity(AgentDto agentDto) {
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

        return agentsVoList;
    }

    //代理-代理列表-新增
    @Override
    public boolean InsertAgent(AgentDto agentDto) {

        SystemAgents systemAgents=new SystemAgents();

//        List<SystemAgents> systemAgentsList1 = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>().eq(SystemAgents::getDisplayId, agentDto.getDisplayId()));
//        List<SystemAgents> systemAgentsList2 = systemAgentsMapper.selectList(new LambdaQueryWrapper<SystemAgents>().eq(SystemAgents::getUsername, agentDto.getUsername()));
//        if(systemAgentsList1!=null&&!systemAgentsList1.isEmpty()&&systemAgentsList2!=null&&!systemAgentsList2.isEmpty()){
//            return false;
//        }
        System.out.println(123);
        systemAgents.setDisplayId(agentDto.getDisplayId());
        systemAgents.setFullName(agentDto.getFullName());
        systemAgents.setIdentity(agentDto.getIdentity());
        systemAgents.setUsername(agentDto.getUsername());
        systemAgents.setPassword(agentDto.getPassword());
        systemAgents.setStatus(agentDto.getStatus());
        //判断新增用户是代理还是总代，是代理就添加对应的总代
        if(agentDto.getIdentity()==2){
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
            return false;
        }

        Map<String,Object> map=new HashMap<>();
        map.put("username",agentDto.getUsername());
        //根据username查找对应的数据
        List<SystemAgents> systemAgentsList = systemAgentsMapper.selectByMap(map);

        //判断是否为总代，是总代就把自己的id添加进所属总代字段中
        if(agentDto.getIdentity()==1){
            //获取数据并把总代的代理ID变为自己的ID
            systemAgents=systemAgentsList.get(0);
            systemAgents.setBelongId(systemAgents.getAgentId());
            int i = systemAgentsMapper.updateById(systemAgents);
            //判断是否修改成功
            if(i!=1){
                return false;
            }
        }
        return true;
    }

}