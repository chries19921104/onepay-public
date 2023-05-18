package org.example.admin.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.admin.mapper.AdminsMapper;
import org.example.admin.service.AdminsService;
import org.example.common.base.CommResp;
import org.example.common.dto.AdminsDTO;
import org.example.common.entity.Admins;
import org.example.common.utils.BaseContext;
import org.example.common.vo.AdminsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
* <p>
* admins Service 接口实现
* </p>
*
* @author zhangmi
* @since 2023-05-11 16:46:06
*/
@Service

@Slf4j
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements AdminsService {

    @Autowired
    private AdminsMapper adminsMapper;
    @Value("${token.key}")
    private String key;

    @Override

    public CommResp login(Admins admins) {
        Admins one = this.query().eq("username", admins.getUsername()).one();
        //验证账户是否存在,并且是否可用
        if (one==null){
            return CommResp.FAIL("账户不存在");
        }
        Integer status = one.getStatus();
        if (status!=1){
            return CommResp.FAIL("账户不可用");
        }
        //加密密码
        String password = SecureUtil.md5(admins.getPassword() + one.getParam());
        //判断加密密码与账户密码是否匹配
        if (!password.equals(one.getPassword())){
            return CommResp.FAIL("密码错误");
        }
        //获取jwt
        HashMap<String,Object> payload =new HashMap<>();
        //
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 10);
        //签发时间
        payload .put(JWTPayload.ISSUED_AT,now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload .put("id",one.getId());
        payload .put("username",one.getUsername());
        String token = JWTUtil.createToken( payload , key.getBytes());
        AdminsVO adminsVO=new AdminsVO();
        BeanUtils.copyProperties(one,adminsVO);
        adminsVO.setToken(token);
        return CommResp.data(adminsVO);
    }

    /**
     * 注册
     * @param adminsDTO
     * @return
     */

    @Override
    public CommResp register(AdminsDTO adminsDTO) {
        //方法中0 为id 1为username
        Long id = BaseContext.getCurrent();
        Admins byId = this.getById(id);
        //清空ThreadLocal
        BaseContext.remove();

        Admins admins=new Admins();
        BeanUtils.copyProperties(adminsDTO,admins);
        admins.setCurrency(adminsDTO.getCurrency().toString());
        //创建人
        admins.setCreator(byId.getUsername());
        //创建时间
        admins.setCreatedAt(LocalDateTime.now());
        //创建盐
        String param = UUID.fastUUID().toString();
        admins.setParam(param);
        admins.setPassword(SecureUtil.md5(adminsDTO.getPassword()+ param));
        //设置超级管理员
        admins.setSuperAdmin(0);
        admins.setStatus(1);
        admins.setIsTester(1);
        admins.setHidden(0);
        this.save(admins);

        return CommResp.data(admins);
    }

    /**
     * 修改
     * @param adminsDTO
     * @return
     */

    @Override
    public CommResp update(AdminsDTO adminsDTO) {
        //取出token判断是否正确
        //根据token取出id
        Long id = getId(adminsDTO.getToken());
        String username = getUsername(adminsDTO.getToken());
        //判断是否为超级管理员
        Admins admins = this.getById(id);
        if (admins==null){
            return  CommResp.FAIL("用户不存在或者Token失效");
        }
        if (admins.getSuperAdmin()==1){
            return CommResp.FAIL("该用户为超级管理员，不可被修改");
        }
        admins.setUpdatedAt(LocalDateTime.now());
        admins.setCreator(username);
        if (!adminsDTO.getPassword().isEmpty()){
            admins.setPassword(SecureUtil.md5(adminsDTO.getPassword()+admins.getParam()));
        }
        if (!adminsDTO.getUsername().isEmpty()){
            admins.setUsername(adminsDTO.getUsername());
        }
        if (adminsDTO.getStatus()!=null){
            admins.setStatus(adminsDTO.getStatus());
        }
        boolean b = this.updateById(admins);
        return CommResp.data(b);
    }

    /**
     * 删除
     * @param adminsDTO
     * @return
     */

    @Override
    public CommResp delete(AdminsDTO adminsDTO) {
        Admins admins = this.getById(adminsDTO.getId());
        if (admins==null){
            return CommResp.FAIL("用户不存在");
        }
        if (adminsDTO.getSuperAdmin()==1&&admins.getSuperAdmin()!=1){
            this.removeById(admins.getId());
            return CommResp.data("删除成功");
        }
        return CommResp.FAIL("权限不够删除");
    }

    //获取
    private Long getId(String token){
       return Long.valueOf(JWTUtil.parseToken(token).getPayload().getClaim("id").toString());
    }
    private String getUsername(String token){
       return JWTUtil.parseToken(token).getPayload().getClaim("username").toString();
    }

}