package com.letterball.vo;

import lombok.Data;

import java.util.Date;


/**
 * 文章
 */
@Data
public class ArticleVO {

    //ID
    private String id;

    //专栏ID
    private String columnId;

    //用户ID
    private String userId;

    //文章标题
    private String title;

    //文章封面
    private String image;

    //发表日期
    private Date createTime;

    //修改日期
    private Date updateTime;

    //是否公开   0：不公开 1：公开
    private String isPublic;

    //是否置顶   0：不置顶 1：置顶
    private String isTop;

    //浏览量
    private Integer visits;

    //点赞数
    private Integer thumbup;

    //评论数
    private Integer comment;

    //审核状态   0：未审核 1：已审核
    private String state;

    //所属频道   关联频道表ID
    private String channelId;

    //URL地址
    private String url;

    //文章类型   0：分享 1：专栏
    private String type;

    //文章内容
    private String content;

    private int limit;

    private int page;

}