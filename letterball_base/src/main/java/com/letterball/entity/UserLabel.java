package com.letterball.entity;

import lombok.Data;

/**
 * 用户标签关联表
 */

@Data
public class UserLabel {

    //用户id
    private String userid;

    //标签id
    private String labelid;
}