package com.letterball.vo;

import com.letterball.entity.Video;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 章节
 */
@Data
public class ChapterVO {

    private String id;

    private String courseId;

    private String title;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

    // 章节整合小节
    private List<Video> children = new ArrayList<>();
}
