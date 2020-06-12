package com.letterball.entity;

import lombok.Data;

/**
 * 标签
 */

@Data
public class Label {

    //标签ID
    private String id;

    //标签名称
    private String labelName;

    //状态  0:无效  1:有效
    private String state;

    //使用数量
    private Long count;

    //是否推荐  0:不推荐 1:推荐
    private String recommend;

    //关注数
    private Long fans;

}