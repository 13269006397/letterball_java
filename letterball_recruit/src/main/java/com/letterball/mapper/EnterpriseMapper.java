package com.letterball.mapper;

import com.letterball.entity.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface EnterpriseMapper {

    List<Enterprise> findAllEnterprise(HashMap<String,Object> map);

    int deleteByPrimaryKey(String id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);
}