package org.example.admin.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProfileInfoVo {
    private Long id;
    private String username;
    private String fullName;
    private Long roleId;
    private String roleName;
    private boolean superAdmin;
    private int status;
    private boolean totpActived;
    private List<Map<String, String>> permissions;
    private boolean isTester;
    private String qrpayType;
    private String currency;
    private List<String> allCurrency;
    /**
     * 延伸权限
     */
    private boolean penguin;
}
