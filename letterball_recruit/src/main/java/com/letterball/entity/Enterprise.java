package com.letterball.entity;

import lombok.Data;

@Data
public class Enterprise {

    //ID
    private String id;

    //企业名称
    private String name;

    //企业简介
    private String summary;

    //企业地址
    private String address;

    //标签列表  用逗号分隔
    private String labels;

    //坐标  经度，纬度
    private String coordinate;

    //是否热门  1：热门 0：不热门
    private String ishot;

    //LOGO
    private String logo;

    //职位数
    private Integer jobcount;

    //URL
    private String url;

}