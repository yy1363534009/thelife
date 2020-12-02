package com.yuyue.thelife.wechat.request.base;

import lombok.Data;

/**
 * @Description:
 * @Auther: yuyue
 * @create: 2020-11-24 23:11:22
 */
@Data
public class WeChatUserInfo {

    /**
     * app显示名 例："唔~~ 起飞"
     */
    private String nickName;

    /**
     * 性别 例：1→男，
     */
    private Integer gender;

    /**
     * 头像地址 例："https://thirdwx.qlogo.cn/mmopen/vi_32/qkkxmNycW0lWzSQiaKdeV26WTEEibMf6n0kRSvVRWJEEElMHJj3n6GRibUnuu2T9T48f73RYWpMxOg96MPKH08aog/132"
     */
    private String avatarUrl;

    /**
     * 市 例："Qiqihar"
     */
    private String city;

    /**
     * 省 例："Heilongjiang"
     */
    private String province;

    /**
     * 国家 例子："China"
     */
    private String country;

    /**
     * 语言 例："zh_CN"
     */
    private String language;

}
