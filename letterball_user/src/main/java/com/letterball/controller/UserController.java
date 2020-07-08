package com.letterball.controller;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.service.UserService;
import com.letterball.utils.NumberUtils;
import com.letterball.utils.RedisUtils;
import com.letterball.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
            String mobile = userVO.getMobile();
            //生成验证码
            String vfCode = new NumberUtils().randomCode();
            redisUtils.setKeyS(mobile, vfCode, 60);
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
    public ResponseBase addUser(@ModelAttribute @Valid UserVO userVO, @RequestParam(name = "files", required = false) MultipartFile[] files) {
        return userService.addUser(userVO,files);
    }

    /**
     * 根据id查询用户
     */
    @PostMapping("/findUserById")
    public ResponseBase findUserById(@RequestBody UserVO userVO) {
        return userService.findUserById(userVO);
    }

    /**
     * 查询用户列表
     * @param userVO
     * @return
     */
    @PostMapping("/findUserList")
    public  ResponseBase findUserList(@RequestBody UserVO userVO) {
        return userService.findUserList(userVO);
    }


    /**
     * 查询用户列表
     * @param userVO
     * @return
     */
    @PostMapping("/deleteUserById")
    public  ResponseBase deleteUserById(@RequestBody UserVO userVO) {
        return userService.deleteUserById(userVO);
    }


    /**
     * 修改用户状态
     * @param userVO
     * @return
     */
    @PostMapping("/updateUserStatus")
    public ResponseBase updateUserStatus(@RequestBody UserVO userVO){
        return userService.updateUserStatus(userVO);
    }

    /**
     * 删除修改页面附件
     * @param userVO
     * @return
     */
    @PostMapping("/deleteFilesById")
    public  ResponseBase deleteFilesById(@RequestBody UserVO userVO) {
        return userService.deleteFilesById(userVO);
    }


}
