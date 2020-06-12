package com.letterball.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * 问题
 */

@Data
public class Problem {

    //id
    private String id;

    //标题
    private String title;

    //创建日期
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    //修改日期
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    //用户ID
    private String userid;

    //昵称
    private String nickname;

    //浏览量
    private Long visits;

    //点赞数
    private Long thumbup;

    //回复数
    private Long reply;

    //是否解决
    private String solve;

    //回复人昵称
    private String replyname;

    //回复日期
    private Date replytime;

    //内容
    private String content;

}