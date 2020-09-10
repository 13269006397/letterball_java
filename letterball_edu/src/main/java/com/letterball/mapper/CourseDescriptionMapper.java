package com.letterball.mapper;

import com.letterball.entity.CourseDescription;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDescriptionMapper {

    int deleteByPrimaryKey(String id);

    int insert(CourseDescription record);

    int addCourseSelective(CourseDescription record);

    CourseDescription selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseDescription record);

    int updateByPrimaryKeyWithBLOBs(CourseDescription record);

    int updateByPrimaryKey(CourseDescription record);
}