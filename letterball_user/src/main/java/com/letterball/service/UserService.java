package com.letterball.service;

import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.vo.UserVO;

public interface UserService {

    User selectUserByMobile(UserVO userVO);

    ResponseBase userLogin(UserVO userVO);

    ResponseBase addUser(UserVO userVO);

    ResponseBase findUserById(UserVO userVO);

    ResponseBase findUserList(UserVO userVO);

    ResponseBase updateUserStatus(UserVO userVO);

}
