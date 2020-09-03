package com.letterball.mapper;

import com.letterball.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface TeacherMapper {

    int deleteByPrimaryKey(String id);


    int insert(Teacher record);


    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKey(Teacher record);

    //  讲师列表
    List<Teacher> findTeacherList(HashMap<String, Object> map);

    //  修改讲师
    int updateEduTeacher(Teacher record);
}