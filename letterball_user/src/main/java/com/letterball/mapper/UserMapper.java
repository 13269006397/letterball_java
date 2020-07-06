package com.letterball.mapper;

import com.letterball.entity.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Repository
@Transactional
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据手机号获取用户
     * @param map
     * @return
     */
    User selectUserByMobile(HashMap<String,Object> map);

    /**
     * 根据用户id查询用户
     * @param map
     * @return
     */
    User findUserById(HashMap<String,Object> map);

    int updateUserById(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    List<User> findUserList(HashMap<String,Object> map);

    void deleteUserById(String id);
}