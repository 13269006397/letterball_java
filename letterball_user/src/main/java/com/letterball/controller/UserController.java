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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseBase addUser(@RequestBody UserVO userVO) {
        return userService.addUser(userVO);
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

    @ApiOperation("上传文件")
    @PostMapping(value = "/uploading")
    public void uploadFile(@RequestParam("filename") MultipartFile file, HttpServletResponse resp) {
        System.out.println("接收到的文件数据为：" + file);
        if (file.isEmpty()) {
            System.out.println("上传文件为空");
        }
        // 获取文件全名a.py
        String fileName = file.getOriginalFilename();
        // 文件上传路径<br>        ]
        String filePath = "D:/myProject/opt/workspace/";
        System.out.println("文件路径:" + filePath);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取文件名
        String prefixName = fileName.substring(0, fileName.lastIndexOf("."));
        // 解决中文问题,liunx 下中文路径,图片显示问题
        //fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);

        System.out.println(dest.getParentFile());
        try {
            if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
            file.transferTo(dest);
            ServletOutputStream out=resp.getOutputStream();
            OutputStreamWriter ow=new OutputStreamWriter(out,"UTF-8");
            ow.write(filePath+fileName);
            ow.flush();
            ow.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
