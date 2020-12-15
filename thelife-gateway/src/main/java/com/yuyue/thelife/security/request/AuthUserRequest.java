package com.yuyue.thelife.security.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-29 15:10:27
 */
@Data
public class AuthUserRequest {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码对应的uuid
     */
    private String uuid = "";

    @Override
    public String toString() {
        return "{username=" + username + ", password=" + password + "}";
    }

}
