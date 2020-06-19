package com.letterball.service.impl;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.mapper.UserMapper;
import com.letterball.service.UserService;
import com.letterball.utils.RedisUtils;
import com.letterball.utils.TokenUtils;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private TokenUtils tokenUtils;

    /**
     * 根据手机号查询用户
     *
     * @param userVO
     * @return
     */
    @Override
    public User selectUserByMobile(UserVO userVO) {
        HashMap<String, Object> requestmap = new HashMap<>();
        String mobile = userVO.getPhoneNumber();
        if (!StringUtils.isEmpty(mobile)) {
            requestmap.put(Constants.SEARCH_MOBILE, mobile);
        }
        return userMapper.selectUserByMobile(requestmap);
    }

    /**
     * 用户登录验证
     *
     * @param userVO
     * @return
     */
    @Override
    public ResponseBase userLogin(UserVO userVO) {
        User user = selectUserByMobile(userVO);
        HashMap<String, Object> resultParams = new HashMap<>();
        //账号不存在
        if (StringUtils.isEmpty(user)) {
            return setResultError(Constants.ERROR_LOGIN_USER_NAME);
        }
        //验证码登录
        if (!StringUtils.isEmpty(userVO.getVfCode())) {
            String key = redisUtils.getKey(userVO.getPhoneNumber());
            if (key.equals(userVO.getVfCode())) {
                //生成Token
                String token = tokenUtils.token(user.getMobile(), user.getPassword());
                resultParams.put(Constants.SEARCH_LOGIN_TOKEN, token);
                return setResult(Constants.SUCCESS, Constants.SUCCESS_LOGIN, resultParams);
            }
            return setResultError(Constants.ERROR_QCORE_);
        }
        //密码登录
        if (!StringUtils.isEmpty(userVO.getPassword())) {

            //账号存在 密码错误
            if (!StringUtils.isEmpty(user) && !user.getPassword().equals(userVO.getPassword())) {
                return setResultError(Constants.ERROR_LOGIN_PWD);
            }
            //密码正确
            if (user.getPassword().equals(userVO.getPassword())) {
                //生成Token
                String token = tokenUtils.token(user.getMobile(), user.getPassword());
                resultParams.put(Constants.SEARCH_LOGIN_TOKEN, token);
                return setResult(Constants.SUCCESS, Constants.SUCCESS_LOGIN, resultParams);
            }
        }
        return setResultError(Constants.ERROR_LOGIN_ERROR);
    }
}
