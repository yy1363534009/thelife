package com.yuyue.thelife.dao;

import com.alibaba.fastjson.JSON;
import com.yuyue.thelife.security.dao.SysUserDao;
import com.yuyue.thelife.security.enums.LoginMethod;
import com.yuyue.thelife.security.model.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: yuyue
 * @Date: 2020/12/13 16:37
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserDaoTest {

    @Resource(name = "sysUserDao")
    private SysUserDao sysUserDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("16621171657");
        sysUser.setPassword(passwordEncoder.encode("yuyue520"));
        sysUser.setLoginMethod(LoginMethod.MOBILE);
        sysUserDao.insert(sysUser);
        System.out.println("insert ok");
    }

    @Test
    public void test2() {
        SysUser sysUser = sysUserDao.queryByUsername("17521104110");
        System.out.println(JSON.toJSONString(sysUser));
    }

}
