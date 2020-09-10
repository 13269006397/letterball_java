package com.letterball.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Course {

    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private String name;

    private String subjectName;

    private String subjectParentName;

}