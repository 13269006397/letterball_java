package com.letterball.service;


import com.letterball.entity.ResponseBase;
import com.letterball.vo.CourseVO;

public interface CourseService {

    // 新增课程信息
    ResponseBase addCourse(CourseVO courseVO);

    // 查询课程
    ResponseBase courseList(CourseVO courseVO);


}
