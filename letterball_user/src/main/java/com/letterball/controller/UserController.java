package com.letterball.controller;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ExcelErrorRecord;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.Service.UserService;
import com.letterball.utils.AliYunOssUtils;
import com.letterball.utils.NumberUtils;
import com.letterball.utils.RedisUtils;
import com.letterball.vo.UserVO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 用户模块
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseService {

    @Resource
    RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AliYunOssUtils aliYunOssUtils;

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
        return userService.addUser(userVO, files);
    }

    /**
     * 根据id查询用户
     */
    @PostMapping("/findUserById")
    public ResponseBase findUserById(@RequestBody UserVO userVO) {
        return userService.findUserById(userVO);
    }

    /**
     * 查询用户列表 Redis
     *
     * @param userVO
     * @return ResponseBase
     */
    @PostMapping("/findUserList")
    public ResponseBase findUserList(@RequestBody UserVO userVO) {
        return userService.findUserList(userVO);
    }

    /**
     * 查询用户列表
     *
     * @param userVO
     * @return ResponseBase
     */
    @PostMapping("/findUserList1")
    public ResponseBase findUserList1(@RequestBody UserVO userVO) {
        return userService.findUserList1(userVO);
    }


    /**
     * 删除用户
     *
     * @param userVO
     * @return ResponseBase
     */
    @PostMapping("/deleteUserById")
    public ResponseBase deleteUserById(@RequestBody UserVO userVO) {
        return userService.deleteUserById(userVO);
    }


    /**
     * 修改用户
     *
     * @param userVO
     * @return ResponseBase
     */
    @PostMapping("/updateUserStatus")
    public ResponseBase updateUserStatus(@RequestBody UserVO userVO) {
        return userService.updateUserStatus(userVO);
    }

    /**
     * 删除修改页面附件
     *
     * @param userVO
     * @return ResponseBase
     */
    @PostMapping("/deleteFilesById")
    public ResponseBase deleteFilesById(@RequestBody UserVO userVO) {
        return userService.deleteFilesById(userVO);
    }

    /**
     * 上传
     */
    @PostMapping("/upAvater")
    public ResponseBase upTest(MultipartFile file){
        HashMap<String, Object> resultMap = new HashMap<>();

        //  用户头像上传路径
        String path = "edu_avatar";

        try {
            String filePath = aliYunOssUtils.AliYunOSSUpload(file, path);
            resultMap.put(Constants.COMM_QUERY_RESP_ITEM, filePath);
            return setResultSuccessData(resultMap);
        } catch (IOException e) {
            return setResultError(Constants.DOWNLOAD_ERROR);
        }
    }

}



