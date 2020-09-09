package com.letterball.service.impl;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.Subject;
import com.letterball.entity.SubjectOne;
import com.letterball.entity.SubjectTwo;
import com.letterball.mapper.SubjectMapper;
import com.letterball.service.SubjectService;
import com.letterball.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectServiceImpl extends BaseService implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public ResponseBase findSubjectList() {
        HashMap<String, Object> subject1 = new HashMap<>();
        subject1.put("type","01");
        HashMap<String, Object> subject2 = new HashMap<>();
        subject2.put("type", "02");

        // 一级课程分类list
        List<Subject> subjectList = subjectMapper.selectSubjectList(subject1);
        // 二级课程分类list
        List<Subject> subjects2List = subjectMapper.selectSubjectList(subject2);
        // 封装树形结构
        List<SubjectOne> finalSubjectList = new ArrayList<>();


        // 封装一级分类
        for (int i = 0; i < subjectList.size(); i++) {
            // 单个一级分类
            Subject subjectOne = subjectList.get(i);
            // 树形结构 放一级分类
            SubjectOne subjectOne1 = new SubjectOne();
            BeanUtils.copyProperties(subjectOne, subjectOne1);
            finalSubjectList.add(subjectOne1);

            // 二级分类 拿到单个二级分类parentId 和 一级分类id 做对比
            // 如果相同 就放进finalSubjectList 中组装
            for (int m = 0; m < subjects2List.size(); m++) {
                // 单个二级分类
                Subject subjectTwo2 = subjects2List.get(m);
                if (subjectTwo2.getParentId().equals(subjectOne.getId())){
                    SubjectTwo subjectTwo = new SubjectTwo();
                    BeanUtils.copyProperties(subjectTwo2, subjectTwo);
                    finalSubjectList.get(i).getChildren().add(subjectTwo);
                }
            }
        }
        return setResultSuccessData(finalSubjectList);
    }

    /**
     * 查询一级分类
     * @return
     */
    @Override
    public ResponseBase getOneSubjectList() {

        HashMap<String, Object> subject1 = new HashMap<>();
        subject1.put("type","01");

        // 一级课程分类list
        List<Subject> subjectList = subjectMapper.selectSubjectList(subject1);

        return setResultSuccessData(subjectList);
    }

    /**
     * 获得二级分类
     * @return
     */
    @Override
    public ResponseBase getTwoSubjectList() {
        HashMap<String, Object> subject2 = new HashMap<>();
        subject2.put("type", "02");

        // 二级课程分类list
        List<Subject> subjectList = subjectMapper.selectSubjectList(subject2);

        return setResultSuccessData(subjectList);
    }

    /**
     * 新增课程分类
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase saveSubject(UserVO userVO) {
        if (null != userVO){
            String id = UUID.randomUUID().toString().replace("-", "").substring(0,16);
            Subject subject = new Subject();
            subject.setId(id);
            subject.setTitle(userVO.getTitle());
            subject.setSort(userVO.getSort());
            subject.setParentId(userVO.getParentId());
            subject.setGmtCreate(new Date());
            subject.setGmtModified(new Date());
            try{
                subjectMapper.insertSelective(subject);
            }catch (Exception e){
                return setResultSuccessMsg(Constants.ADD_ERROR);
            }
        }
        return setResultSuccessMsg(Constants.ADD_SUCCESS);
    }

    /**
     * 删除课程分类
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase deleSubject(UserVO userVO) {
        if (null != userVO.getId()){
            try{
                subjectMapper.deleteByPrimaryKey(userVO.getId());
            }catch (Exception e){
                return setResultError(Constants.DELETE_ERROR);
            }
        }
        return setResultSuccessMsg(Constants.DELETE_SUCCESS);
    }
}
