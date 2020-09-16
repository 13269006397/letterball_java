package com.letterball.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Course;
import com.letterball.entity.CourseDescription;
import com.letterball.entity.ResponseBase;
import com.letterball.mapper.CourseDescriptionMapper;
import com.letterball.mapper.CourseMapper;
import com.letterball.Service.CourseService;
import com.letterball.vo.CourseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl extends BaseService implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CourseDescriptionMapper courseDescriptionMapper;


    /**
     * 新增课程信息
     * @param courseVO
     * @return
     */
    @Override
    @Transactional
    public ResponseBase addCourse(CourseVO courseVO) {


        // 1.增加课程主表(course)信息
        Course addCourseModel = courseVO.getAddCourseModel();

        Date dataNow = new Date();
        Course course = new Course();
        BeanUtils.copyProperties(addCourseModel, course);
        String id = "kc" + UUID.randomUUID().toString().replace("-", "").substring(0,16);
        course.setId(id);
        //设置未删除
        course.setIsDeleted("0");
        course.setStatus(addCourseModel.getStatus());
        course.setGmtCreate(dataNow);
        course.setGmtModified(dataNow);
        int addCourse = courseMapper.addCourse(course);
        if (addCourse <= 0){
            return setResultError(Constants.ADD_ERROR);
        }

        // 2.增加课程 简介表(Description)信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(id);
        courseDescription.setDescription(addCourseModel.getDescription());
        courseDescription.setGmtCreate(dataNow);
        courseDescription.setGmtModified(dataNow);
        int addDescription = courseDescriptionMapper.addCourseSelective(courseDescription);
        if (addDescription <= 0){
            return setResultError(Constants.ADD_ERROR);
        }

        return setResultSuccessMsg(Constants.ADD_SUCCESS);
    }

    /**
     * 查询课程列表
     * @param courseVO
     * @return
     */
    @Override
    public ResponseBase courseList(CourseVO courseVO) {

        HashMap<String, Object> requestParams = new HashMap<>();
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = 0;
        if (null != courseVO.getTitle()){
            requestParams.put(Constants.SYS_TITLE, courseVO.getTitle());
        }
        if (null != courseVO.getName()){
            requestParams.put(Constants.SEARCH_NAME, courseVO.getName());
        }

        PageHelper.startPage(courseVO.getPage(), courseVO.getLimit());
        List<Course> courseList = courseMapper.getCourseList(requestParams);
        Page<Course> page = (Page<Course>) courseList;
        total = page.getTotal();

        // 返回数据
        resultMap.put(Constants.COMM_QUERY_RESP_ITEM, courseList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL, total);

        return setResultSuccessData(resultMap);
    }
}
