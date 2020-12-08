package com.yuyue.thelife.security.model;

import com.baomidou.mybatisplus.annotation.*;
import com.yuyue.thelife.security.service.enums.Gender;
import lombok.Data;

import java.util.Date;

/**
 * @Author: yuyue
 * @Date: 2020/12/6 21:06
 * @Description:
 */
@Data
@TableName("sys_user_detail")
public class SysUserDetail {

    /**
     * 主键 用户详细ID
     */
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * app显示名 例："唔~~ 起飞"
     */
    private String nickName;

    /**
     * 性别(0=未知，1=男，2=女，)
     */
    private Gender gender = Gender.unknown;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址 例："https://thirdwx.qlogo.cn/mmopen/vi_32/qkkxmNycW0lWzSQiaKdeV26WTEEibMf6n0kRSvVRWJEEElMHJj3n6GRibUnuu2T9T48f73RYWpMxOg96MPKH08aog/132"
     */
    private String avatarUrl;

    /**
     * 国家 例子："China"
     */
    private String country;

    /**
     * 省 例："Heilongjiang"
     */
    private String province;

    /**
     * 市 例："Qiqihar"
     */
    private String city;

    /**
     * 语言 例："zh_CN"
     */
    private String language;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
