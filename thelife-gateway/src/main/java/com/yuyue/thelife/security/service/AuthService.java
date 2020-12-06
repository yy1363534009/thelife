package com.yuyue.thelife.security.service;

import com.yuyue.thelife.result.JsonRestResponseVo;
import com.yuyue.thelife.security.request.RegisterRequest;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 20:25
 * @Description:
 */
public interface AuthService {

    JsonRestResponseVo register(RegisterRequest registerRequest);

}
