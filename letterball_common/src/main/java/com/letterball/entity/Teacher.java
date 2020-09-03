package com.letterball.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Teacher {

    private String id;

    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

}