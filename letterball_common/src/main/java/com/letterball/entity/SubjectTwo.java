package com.letterball.entity;

import lombok.Data;

/**
 * 二级分类
 */
@Data
public class SubjectTwo {

    private String id;

    private String title;

    private String parentId;

}
