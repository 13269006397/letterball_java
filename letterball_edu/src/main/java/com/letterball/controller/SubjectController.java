package com.letterball.controller;

import com.letterball.entity.ResponseBase;
import com.letterball.Service.SubjectService;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程管理
 */

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 查询课程分类树
     */
    @RequestMapping("/subjectList")
    public ResponseBase getSubjectList(){
        return subjectService.findSubjectList();
    }

    /**
     * 获得一级课程分类
     */
    @RequestMapping("/oneSubjectList")
    public ResponseBase getOneSubjectList(){
        return subjectService.getOneSubjectList();
    }

    /**
     * 获得二级课程分类
     */
    @RequestMapping("/twoSubjectList")
    public ResponseBase getTwoSubjectList(){
        return subjectService.getTwoSubjectList();
    }


    /**
     * 新增课程分类
     */
    @RequestMapping("/saveSubject")
    public ResponseBase saveSubject(@RequestBody UserVO userVO){
        return subjectService.saveSubject(userVO);
    }

    /**
     * 删除课程分类
     */
    @RequestMapping("/deleSubject")
    public ResponseBase deleSubject(@RequestBody UserVO userVO){
        return subjectService.deleSubject(userVO);
    }


}
