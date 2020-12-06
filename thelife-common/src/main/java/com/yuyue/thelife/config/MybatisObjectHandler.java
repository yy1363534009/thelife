package com.yuyue.thelife.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: yuyue
 * @Date: 2020/12/3 23:55
 * @Description: mybatisplus 自动填充
 */
@Component
public class MybatisObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName(CREATE_TIME, new Date(), metaObject);
        setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }

}
