package com.letterball.mapper;

import com.letterball.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    /**
     * 根据手机号获取用户
     * @param map
     * @return
     */
    User selectUserByMobile(HashMap<String,Object> map);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}