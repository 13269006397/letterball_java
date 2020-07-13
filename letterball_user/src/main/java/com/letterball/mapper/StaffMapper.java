package com.letterball.mapper;

import com.letterball.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffMapper {

    int deleteByPrimaryKey(String id);

    int insert(Staff record);

    int insertStaff(Staff record);

    Staff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);
}