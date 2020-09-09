package com.letterball.vo;

import com.letterball.entity.Subject;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

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

    private String fileId;

    //页面传输过来的数据

    private int page;

    private int limit;

    //权限
    private String permission;

    private String phoneNumber;

    private String vfCode;

    private String name;

    private String parentId;

    private Integer sort;

    private String title;


}