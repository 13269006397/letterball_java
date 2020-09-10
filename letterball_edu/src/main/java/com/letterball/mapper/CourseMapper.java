package com.letterball.mapper;

import com.letterball.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CourseMapper {

    int deleteByPrimaryKey(String id);

    int insert(Course record);

    // 新增课程信息主表
    int addCourse(Course record);

    // 查询课程列表
    List<Course> getCourseList(HashMap<String, Object> map);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}