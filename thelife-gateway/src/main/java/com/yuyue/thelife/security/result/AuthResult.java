package com.yuyue.thelife.security.result;

import com.yuyue.thelife.security.dto.JwtUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yuyue
 * @Date: 2021/1/3 11:32
 * @Description:
 */
@Data
public class AuthResult implements Serializable {

    private static final long serialVersionUID = -5144704064867179844L;

    private String token;

    private JwtUser userInfo;

}
