package com.letterball.service;

import com.letterball.entity.ResponseBase;
import com.letterball.entity.Teacher;
import com.letterball.vo.UserVO;

public interface EduTeacherService {

    //  讲师列表
    ResponseBase findTeacherList(UserVO userVO);

    //  修改讲师
    ResponseBase updateEduTeacher(Teacher teacher);

}
