package com.letterball.mapper;

import com.letterball.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityMapper {

    int deleteByPrimaryKey(String id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}