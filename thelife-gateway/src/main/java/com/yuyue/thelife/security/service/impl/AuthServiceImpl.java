package com.yuyue.thelife.security.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.yuyue.thelife.exception.enums.AuthExceptionEnum;
import com.yuyue.thelife.exception.exception.AuthException;
import com.yuyue.thelife.security.config.SecurityProperties;
import com.yuyue.thelife.security.consts.WeChatConst;
import com.yuyue.thelife.security.dao.SysUserDao;
import com.yuyue.thelife.security.dao.SysUserDetailDao;
import com.yuyue.thelife.security.dto.JwtUser;
import com.yuyue.thelife.security.dto.User;
import com.yuyue.thelife.security.dto.UserDetail;
import com.yuyue.thelife.security.enums.LoginMethod;
import com.yuyue.thelife.security.model.SysUser;
import com.yuyue.thelife.security.model.SysUserDetail;
import com.yuyue.thelife.security.param.AuthUserParam;
import com.yuyue.thelife.security.param.RegisterParam;
import com.yuyue.thelife.security.param.WeChatAuthUserParam;
import com.yuyue.thelife.security.result.AuthResult;
import com.yuyue.thelife.security.result.WeChatAuthCode2SessionResult;
import com.yuyue.thelife.security.security.TokenProvider;
import com.yuyue.thelife.security.service.AuthService;
import com.yuyue.thelife.utils.HttpClientHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 17:13
 * @Description: 用户授权接口实现类
 */
@Service("authService")
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource(name = "tokenProvider")
    private TokenProvider tokenProvider;

    @Resource
    private SecurityProperties properties;

    @Resource(name = "sysUserDao")
    private SysUserDao sysUserDao;

    @Resource(name = "sysUserDetailDao")
    private SysUserDetailDao sysUserDetailDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private HttpClientHelper httpClientHelper;

    @Value("${wechat.AppID}")
    private String wechatAppid;

    @Value("${wechat.AppSecret}")
    private String wechatSecret;

    @Value("${wechat.defaultPwd}")
    private String wechatDefaultPwd;

    /**
     * pc登录
     *
     * @param authUserParam
     * @return
     */
    @Override
    public AuthResult login(AuthUserParam authUserParam) {
        logger.info(authUserParam.toString());
        // 构建未认证token令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserParam.getUsername(), authUserParam.getPassword());
        // 未认证token令牌交给AuthenticationManager，通过自定义UserDetailsServiceImpl完成认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 将认证传递给Security上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户信息
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 构建jwt令牌
        String token = tokenProvider.createToken(authentication);
        // 封装返回信息
        AuthResult result = new AuthResult();
        result.setToken(token);
        result.setUserInfo(jwtUser);
        return result;
    }

    /**
     * 微信登录
     *
     * @param weChatAuthUserParam
     * @return
     */
    @Override
    public AuthResult wechatLogin(WeChatAuthUserParam weChatAuthUserParam) {
        logger.info("微信登录:weChatAuthUserParam={}", weChatAuthUserParam.toString());
        // 获取用户唯一标识微信小程序openId，相当于用户名username,用户名表中查看openId是否注册，如果未注册就自动注册，因为微信小程序password不存，就指定。
        WeChatAuthCode2SessionResult weChatAuthCode2SessionResult = getWeChatAuthCode2SessionResult(weChatAuthUserParam.getCode());
        SysUser sysUser = sysUserDao.queryByUsername(weChatAuthCode2SessionResult.getOpenid());
        if (sysUser == null) {
            // 当前微信小程序用户不存在，创建新用户
            sysUser = new SysUser();
            sysUser.setUsername(weChatAuthCode2SessionResult.getOpenid());
            sysUser.setPassword(passwordEncoder.encode(wechatDefaultPwd));
            sysUser.setLoginMethod(LoginMethod.WECHAT);
            SysUserDetail sysUserDetail = new SysUserDetail();
            BeanUtil.copyProperties(weChatAuthUserParam.getUserDetail(), sysUserDetail);
            register(sysUser, sysUserDetail);
        }
        // 构建未认证token令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(weChatAuthCode2SessionResult.getOpenid(), wechatDefaultPwd);
        // 未认证token令牌交给AuthenticationManager，通过自定义UserDetailsServiceImpl完成认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 将认证传递给Security上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取用户信息
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        // 构建jwt令牌
        String token = tokenProvider.createToken(authentication);
        // 封装返回信息
        AuthResult result = new AuthResult();
        result.setToken(token);
        result.setUserInfo(jwtUser);
        logger.info("微信登录结束");
        return result;
    }

    /**
     * 注册用户
     *
     * @param registerParam
     */
    @Override
    public void register(RegisterParam registerParam) {
        logger.info("注册开始-当前用户：RegisterRequest={}", registerParam.toString());
        SysUser sysUser = new SysUser();
        sysUser.setUsername(registerParam.getUsername());
        sysUser.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        sysUser.setLoginMethod(registerParam.getLoginMethod());
        SysUserDetail sysUserDetail = new SysUserDetail();
        register(sysUser, sysUserDetail);
        logger.info("注册开始-结束");
    }

    /**
     * security框架自定义完成用户名密码校验逻辑
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserDao.queryByUsername(username);
        SysUserDetail sysUserDetail = sysUserDetailDao.getByUserId(sysUser.getId());
        if (sysUser == null) {
            throw new AuthException(AuthExceptionEnum.USERNAMEPASSWORD_NOTFOUND);
        }
        if (!sysUser.isEnabled()) {
            throw new AuthException(AuthExceptionEnum.USER_ISENABLED);
        }
        User user = new User();
        UserDetail userDetail = new UserDetail();
        BeanUtil.copyProperties(sysUser, user);
        BeanUtil.copyProperties(sysUserDetail, userDetail);
        JwtUser jwtUser = new JwtUser(user, userDetail, null);
        return jwtUser;
    }

    /**
     * 获取用户唯一标识OpenID和会话密钥session_key结果
     *
     * @param code
     * @return
     */
    private WeChatAuthCode2SessionResult getWeChatAuthCode2SessionResult(String code) {
        HashMap<String, Object> paramMap = new HashMap<>(4);
        paramMap.put(WeChatConst.OPENID_PARAM_KEY_APPID, wechatAppid);
        paramMap.put(WeChatConst.OPENID_PARAM_KEY_SECRET, wechatSecret);
        paramMap.put(WeChatConst.OPENID_PARAM_KEY_JSCODE, code);
        paramMap.put(WeChatConst.OPENID_PARAM_KEY_GRANTTYPE, WeChatConst.OPENID_PARAM_VALUE_GRANTTYPE);
        String responseStr = httpClientHelper.get(WeChatConst.OPENID_URL, paramMap, null);
        WeChatAuthCode2SessionResult weChatAuthCode2SessionResult = JSON.parseObject(responseStr, WeChatAuthCode2SessionResult.class);
        logger.info("微信登录:weChatAuthCode2SessionResult={}", weChatAuthCode2SessionResult.toString());
        return weChatAuthCode2SessionResult;
    }

    /**
     * 统一注册
     *
     * @param sysUser
     * @param sysUserDetail
     */
    @Transactional(rollbackOn = Exception.class)
    public void register(SysUser sysUser, SysUserDetail sysUserDetail) {
        logger.info("注册用户：sysUser={},sysUserDetail={}", sysUser.toString(), sysUserDetail);
        Date now = new Date();
        SysUser user = sysUserDao.queryByUsername(sysUser.getUsername());
        if (user != null) {
            throw new AuthException(AuthExceptionEnum.USER_EXIST);
        }
        sysUser.setCreateTime(now);
        sysUser.setUpdateTime(now);
        //用户入库
        sysUserDao.insert(sysUser);
        sysUserDetail.setUserId(sysUser.getId());
        if (StringUtils.isEmpty(sysUserDetail.getNickName())) {
            sysUserDetail.setNickName(sysUser.getUsername());
        } else {
            sysUserDetail.setNickName(sysUserDetail.getNickName());
        }
        sysUserDetail.setCreateTime(now);
        sysUserDetail.setUpdateTime(now);
        //用户详细入库
        sysUserDetailDao.insert(sysUserDetail);
        logger.info("注册SysUser={}", JSON.toJSONString(sysUser));
        logger.info("注册SysUserDetail={}", JSON.toJSONString(sysUserDetail));
        logger.info("注册结束");
    }

}
