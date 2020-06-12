package com.letterball.entity;

import lombok.Data;

/**
 * 问题 标签关联表
 */
@Data
public class ProblemLabel {

    private String problemid;

    private String labelid;

}