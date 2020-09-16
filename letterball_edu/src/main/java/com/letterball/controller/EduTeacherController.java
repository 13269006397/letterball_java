package com.letterball.controller;

import com.letterball.entity.ResponseBase;
import com.letterball.entity.Teacher;
import com.letterball.Service.EduTeacherService;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  讲师信息
 */

@RestController
@RequestMapping("/eudService")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 获取讲师列表
     */
    @RequestMapping("/eduTeacherList")
    public ResponseBase findTeacherList(@RequestBody UserVO userVO){
        return eduTeacherService.findTeacherList(userVO);
    }

    /**
     * 修改讲师信息
     */
    @RequestMapping("/updateEduTeacher")
    public ResponseBase updateEduTeacher(@RequestBody Teacher teacher){
        return eduTeacherService.updateEduTeacher(teacher);
    }

    /**
     * 根据id查询讲师信息
     */
    @RequestMapping("/findTeacherById")
    public ResponseBase findTeacherById(@RequestBody UserVO userVO){
        return eduTeacherService.findTeacherById(userVO);
    }

}
