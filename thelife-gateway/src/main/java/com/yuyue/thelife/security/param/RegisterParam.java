package com.yuyue.thelife.security.param;

import com.yuyue.thelife.security.enums.LoginMethod;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 21:43
 * @Description:
 */
@Data
public class RegisterParam implements Serializable {

    private static final long serialVersionUID = 6816486975187206757L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, message = "用户名至少6位")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码至少6位")
    private String password;

    /**
     * 登录方式
     */
    @NotNull(message = "登录方式不能为空")
    private LoginMethod loginMethod;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码格式不正确")
    private String verificationCode;

}
