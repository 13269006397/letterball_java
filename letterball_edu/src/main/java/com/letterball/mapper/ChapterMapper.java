package com.letterball.mapper;

import com.letterball.entity.Chapter;

public interface ChapterMapper {

    int deleteByPrimaryKey(String id);

    int insert(Chapter record);

    int insertSelective(Chapter record);

    Chapter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Chapter record);

    int updateByPrimaryKey(Chapter record);
}