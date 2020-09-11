package com.letterball.mapper;

import com.letterball.entity.Video;

public interface VideoMapper {

    int deleteByPrimaryKey(String id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);
}