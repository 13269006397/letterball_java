package com.letterball.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Video {

    private String id;

    private String courseId;

    private String chapterId;

    private String title;

    private String videoSourceId;

    private String videoOriginalName;

    private Integer sort;

    private Long playCount;

    private Byte isFree;

    private Float duration;

    private String status;

    private Long size;

    private Long version;

    private Date gmtCreate;

    private Date gmtModified;
}