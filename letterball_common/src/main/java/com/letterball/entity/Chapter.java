package com.letterball.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Chapter {

    private String id;

    private String courseId;

    private String title;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

}