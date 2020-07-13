package com.letterball.service;

import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User selectUserByMobile(UserVO userVO);

    ResponseBase userLogin(UserVO userVO);

    ResponseBase addUser(UserVO userVO, MultipartFile[] files);

    ResponseBase findUserById(UserVO userVO);

    ResponseBase findUserList(UserVO userVO);

    ResponseBase updateUserStatus(UserVO userVO);

    ResponseBase deleteUserById(UserVO userVO);

    ResponseBase deleteFilesById(UserVO userVO);

    List<User> findUsersList(UserVO userVO);

}
