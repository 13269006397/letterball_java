package com.letterball.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CourseDescription {

    private String id;

    private Date gmtCreate;

    private Date gmtModified;

    private String description;

}