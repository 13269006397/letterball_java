package com.letterball.service;

import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.vo.UserVO;

public interface UserService {

    User selectUserByMobile(UserVO userVO);

    ResponseBase userLogin(UserVO userVO);

}
