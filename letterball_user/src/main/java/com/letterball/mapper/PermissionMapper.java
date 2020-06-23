package com.letterball.mapper;

import com.letterball.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {

    int deleteByPrimaryKey(String id);

    int insertUserPermission(Permission permission);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}