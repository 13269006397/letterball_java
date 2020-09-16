package com.letterball.Service;

import com.letterball.entity.ResponseBase;
import com.letterball.vo.UserVO;


public interface SubjectService {

    // 课程分类列表
    ResponseBase findSubjectList();

    // 一级课程分类
    ResponseBase getOneSubjectList();

    // 二级课程分类
    ResponseBase getTwoSubjectList();

    // 保存课程分类
    ResponseBase saveSubject(UserVO userVO);

    // 删除课程分类
    ResponseBase deleSubject(UserVO userVO);

}
