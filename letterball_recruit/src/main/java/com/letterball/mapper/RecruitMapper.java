package com.letterball.mapper;

import com.letterball.entity.Recruit;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface RecruitMapper {

    List<Recruit> findRecommendList(HashMap<String,Object> map);

    int deleteByPrimaryKey(String id);

    int insert(Recruit record);

    int insertSelective(Recruit record);

    Recruit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Recruit record);

    int updateByPrimaryKey(Recruit record);
}