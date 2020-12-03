package com.yuyue.thelife.model;

import com.baomidou.mybatisplus.annotation.*;
import com.yuyue.thelife.base.dto.BaseDto;
import com.yuyue.thelife.enums.SwitchType;
import lombok.Data;

import java.util.Date;

/**
 * @author: yuyue
 * @create 2020/12/3 15:24
 */
@Data
@TableName("t_jobSearch_info")
public class JobSearch extends BaseDto {

    /**
     * 主键
     */
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 开关
     */
    private SwitchType enable;

    /**
     * 过期时间
     */
    private Date expiredTIme;


}
