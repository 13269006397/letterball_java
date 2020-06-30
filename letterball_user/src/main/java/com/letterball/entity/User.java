package com.letterball.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;

    private String mobile;

    private String password;

    private String nickName;

    private String sex;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;

    private String avatar;

    private String email;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date regTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

    private Long onLine;

    private String interest;

    private String personality;

    private Integer fansCount;

    private Integer followCount;

    private String isDelete;

    //权限表数据
    private String permission;

}