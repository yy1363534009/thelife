package com.yuyue.thelife.security.request;

import com.yuyue.thelife.security.service.enums.LoginMethod;
import lombok.Data;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 21:43
 * @Description:
 */
@Data
public class RegisterRequest {

    private String username;

    private String password;

    private LoginMethod loginMethod;

    private String verificationCode;

}
