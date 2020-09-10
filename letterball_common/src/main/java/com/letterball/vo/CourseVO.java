package com.letterball.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.letterball.converter.IsDelete;
import com.letterball.converter.IsLevel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CourseVO {

    private Integer page;

    private Integer limit;

    // 课程分类
    private String id;

    private String title;

    private String parentId;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

    //讲师信息

    @ExcelProperty(value = "讲师名称", index = 1)
    private String name;

    @ExcelProperty(value = "讲师简介", index = 2)
    private String intro;

    @ExcelProperty(value = "讲师资历", index = 3)
    private String career;

    @ExcelProperty(value = "讲师头衔", converter = IsLevel.class, index = 4)
    private Integer level;

    @ExcelProperty(value = "讲师头像", index = 5)
    private String avatar;

    private String isDeleted;

    //课程信息

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    // 课程简介

    private String description;

    // 小节视频

    private String courseId;

    private String chapterId;

    private String videoSourceId;

    private String videoOriginalName;

    private Long playCount;

    private Byte isFree;

    private Float duration;

    private Long size;


}
