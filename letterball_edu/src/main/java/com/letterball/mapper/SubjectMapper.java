package com.letterball.mapper;

import com.letterball.entity.Subject;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SubjectMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Subject record);

    // 课程分类列表
    List<Subject> selectSubjectList(HashMap<String, Object> map);

    int updateByPrimaryKeySelective(Subject record);

}