package org.example.agent.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordVo {
    private String confirmPassword;
    private String newPassword;
    private String oldPassword;

}
