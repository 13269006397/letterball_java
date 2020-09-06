package com.letterball.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.Teacher;
import com.letterball.mapper.TeacherMapper;
import com.letterball.service.EduTeacherService;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class EduTeacherServiceImpl extends BaseService implements EduTeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public ResponseBase findTeacherList(UserVO userVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = 0;
        if (null != userVO.getId()){
            requestParams.put(Constants.SERCH_DATA_ID, userVO.getId());
        }
        if (null != userVO.getName()){
            requestParams.put(Constants.SEARCH_NAME, userVO.getName());
        }

        PageHelper.startPage(userVO.getPage(), userVO.getLimit());
        List<Teacher> teacherList = teacherMapper.findTeacherList(requestParams);
        Page<Teacher> page = (Page<Teacher>) teacherList;
        total = page.getTotal();

        resultMap.put(Constants.COMM_QUERY_RESP_ITEM, teacherList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL, total);
        return setResultSuccessData(resultMap);
    }

    @Override
    public ResponseBase updateEduTeacher(Teacher teacher) {
        try {
            if (null != teacher){
                // 更新时间
                teacher.setGmtModified(new Date());
                teacherMapper.updateEduTeacher(teacher);
                return setResultSuccessMsg(Constants.UPDATE_SUCCESS);
            }
        }catch (Exception e){
            return setResultError(Constants.UPDATE_ERROR);
        }
        return null;
    }

    @Override
    public ResponseBase findTeacherById(UserVO userVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        HashMap<String, Object> resultMap = new HashMap<>();
        Teacher teacher = new Teacher();

        if (!StringUtils.isEmpty(userVO.getId())){
            requestParams.put(Constants.SEARCH_USER_ID, userVO.getId());
            teacher = teacherMapper.findTeacherById(requestParams);

        }
        resultMap.put(Constants.COMM_QUERY_RESP_ITEM, teacher);

        return setResultSuccessData(resultMap);
    }
}
