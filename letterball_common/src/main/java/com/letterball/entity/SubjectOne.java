package com.letterball.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// 一级分类
@Data
public class SubjectOne {

    private String id;

    private String title;

    private String parentId;

    // 一个一级分类有多个二级分类
    private List<SubjectTwo> children = new ArrayList<>();

}
