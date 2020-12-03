package com.yuyue.thelife.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @author: yuyue
 * @create: 2020-11-29 15:10:27
 */
@Data
public class AuthUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

    @Override
    public String toString() {
        return "{username=" + username + ", password=" + password + "}";
    }

}
