package com.letterball.mapper;


import com.letterball.entity.UserLabel;

public interface UserLabelMapper {

    int deleteByPrimaryKey(UserLabel userLabel);

    int insert(UserLabel userLabel);

    int insertSelective(UserLabel userLabel);
}