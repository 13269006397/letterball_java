package com.letterball.controller;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.service.UserService;
import com.letterball.utils.NumberUtils;
import com.letterball.utils.RedisUtils;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
public class UserController extends BaseService {

    @Resource
    RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    /**
     * 生成并保存验证码
     */
    @PostMapping("/login/findSetVFCode")
    public ResponseBase findSetVFCode(@RequestBody UserVO userVO) {
        //查询发送验证码手机号是否存在
        User user = userService.selectUserByMobile(userVO);
        if (!StringUtils.isEmpty(user)) {
            String PhoneNumber = userVO.getPhoneNumber();
            //生成验证码
            String vfCode = new NumberUtils().randomCode();
            redisUtils.setKeyS(PhoneNumber, vfCode, 60);
        } else {
            return setResultError(Constants.ERROR_SEND_QCORE);
        }
        return setResultSuccessMsg(Constants.SUCCESS_SEND_QCORE);
    }


    /**
     * 用户登录验证
     */
    @PostMapping("/login/userLogin")
    public ResponseBase userLogin(@RequestBody UserVO userVO) {
        return userService.userLogin(userVO);
    }

    /**
     * 新增用户
     */
    @PostMapping("/login/addUser")
    public ResponseBase addUser (@RequestBody UserVO userVO){
        return userService.addUser(userVO);
    }
}
