package com.letterball.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecruitVO {

    //ID
    private String id;

    //职位名称
    private String jobName;

    //薪资范围
    private String salary;

    //经验要求
    private String condition;

    //学历要求
    private String education;

    //任职方式
    private String type;

    //办公地址
    private String addrss;

    //企业ID
    private String eid;

    //创建日期
    private Date createTime;

    //状态  0:关闭 1:开启  2:推荐
    private String state;

    //网址
    private String url;

    //标签
    private String label;

    //职位描述
    private String content1;

    //职位要求
    private String content2;

    private int page;

    private int limit;

}