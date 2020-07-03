package com.letterball.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;

    private String idNumber;

    private String mobile;

    private String password;

    private String age;

    private String nickName;

    private String sex;

    private Date birthday;

    private String avatar;

    private String email;

    private Date regTime;

    private Date updateTime;

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