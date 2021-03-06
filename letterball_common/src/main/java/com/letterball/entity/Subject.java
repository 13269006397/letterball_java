package com.letterball.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Subject {

    private String id;

    private String title;

    private String parentId;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

}