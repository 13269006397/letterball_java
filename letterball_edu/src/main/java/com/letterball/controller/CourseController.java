package com.letterball.controller;

import com.letterball.entity.ResponseBase;
import com.letterball.service.CourseService;
import com.letterball.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新增课程
 */
@RequestMapping("/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 新增课程
     */
    @RequestMapping("/addCuorse")
    public ResponseBase addCourse(@RequestBody CourseVO courseVO){

        return courseService.addCourse(courseVO);
    }

    /**
     * 课程list
     */
    @RequestMapping("/courseList")
    public ResponseBase courseList(@RequestBody CourseVO courseVO){

        return courseService.courseList(courseVO);
    }





}
