package org.example.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.admin.conf.interceptor.NoAuthorization;
import org.example.admin.service.*;
import org.example.admin.vo.ProfileInfoVo;
import org.example.common.entity.*;
import org.example.common.utils.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
@Api(value = "权限获取", tags = "权限获取")
public class ProfileInfo {

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private SystemAdminRolesService adminRolesService;

    @Autowired
    private SystemAdminRolePermsService adminRolePermsService;

    @Autowired
    private SystemSelectOptionConfigService selectOptionConfigService;

    @Autowired
    private SystemCurrencyService currencyService;

    @GetMapping("/profile")
    @ApiOperation(value = "权限获取info接口")
    public ProfileInfoVo info(){
        // 获取登录用户id
        Long userId = BaseContext.getCurrent();
        // 查询用户信息
        Admins admins = adminsService.getById(userId);
        // 获取role_id
        Long roleId = admins.getRoleId();
        // 查询role表 用户与角色是1对1关系
        SystemAdminRoles adminRoles = adminRolesService.getOne(new LambdaQueryWrapper<SystemAdminRoles>()
                .eq(SystemAdminRoles::getId, roleId)
                .eq(SystemAdminRoles::getStatus, 1));
        // 判断是否是超级管理员
        boolean isSuperAdmin = adminRoles.getSuperAdmin() == 1 ? true : false;
        String permissionIdsStr = isSuperAdmin ? "all" : adminRoles.getPermissionIds();
        // 权限列表
        ArrayList<Map<String, String>> permissions = new ArrayList<>();
        if (!isSuperAdmin){
            // 获取对应权限id
            String[] permissionIds = permissionIdsStr.split(",");
            // 查询所有权限
            List<SystemAdminRolePerms> adminRolePerms = adminRolePermsService.listByIds(Arrays.asList(permissionIds));
            // 获取权限字符串数组
            ArrayList<String> permissionsStr = new ArrayList<>();
            adminRolePerms.forEach(item ->{
                permissionsStr.add(item.getPermission());
            });
            // 切割权限
            permissionsStr.forEach(item -> {
                String[] split = item.split(".");
                HashMap<String, String> hashMap = new HashMap<>();
                if (hashMap.containsKey(split[0])){
                    // 页面对应字符串
                    String s = hashMap.get(split[0]);
                    hashMap.put(split[0], s + split[1]);
                }else {
                    hashMap.put(split[0], split[1]);
                }
                permissions.add(hashMap);
            });
        }
        // 以qrpay_type为key 以1为值缓存，未命中则使用type + 1 查询content
        SystemSelectOptionConfig selectOptionConfig = selectOptionConfigService.getOne(new LambdaQueryWrapper<SystemSelectOptionConfig>()
                .eq(SystemSelectOptionConfig::getType, "qrpay_type")
                .eq(SystemSelectOptionConfig::getNum, 1));
        String qrpay_type = null;
        if (selectOptionConfig != null){
            // 获取content
            qrpay_type = selectOptionConfig.getContent();
        }
        // 获取allCurrency
        List<SystemCurrency> currencyList = currencyService.list(new LambdaQueryWrapper<SystemCurrency>()
                .eq(SystemCurrency::getActive, 1));
        ArrayList<String> allCurrency = new ArrayList<>();
        if (currencyList != null || currencyList.size() > 0){
            currencyList.forEach(currency -> {
                allCurrency.add(currency.getCurrency());
            });
        }
        // 封装返回数据
        ProfileInfoVo profileInfoVo = new ProfileInfoVo();
        profileInfoVo.setId(userId);
        profileInfoVo.setUsername(admins.getUsername());
        profileInfoVo.setFullName(admins.getFullName());
        profileInfoVo.setRoleId(admins.getRoleId());
        profileInfoVo.setRoleName(adminRoles.getName());
        profileInfoVo.setSuperAdmin(isSuperAdmin);
        profileInfoVo.setStatus(admins.getStatus());
        profileInfoVo.setTotpActived(StringUtils.isNotEmpty(admins.getTotpSecret()));
        profileInfoVo.setPermissions(permissions);
        profileInfoVo.setTester(admins.getIsTester() == 1 ? true : false);
        profileInfoVo.setQrpayType(qrpay_type);
        profileInfoVo.setCurrency(admins.getCurrency());
        profileInfoVo.setAllCurrency(allCurrency);
        profileInfoVo.setPenguin(admins.getPenguin() == null ? false : (admins.getPenguin() == 1 ? true : false));
        return profileInfoVo;
    }

}
