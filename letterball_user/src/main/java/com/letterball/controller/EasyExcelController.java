package com.letterball.controller;

import com.alibaba.excel.EasyExcel;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/easyExcel")
@RestController
public class EasyExcelController extends BaseService {


    @Autowired
    private UserMapper userMapper;

    //  poi导出 查数据 查表头 拼Excel Response流给前端
    //  easyExcel导出 查数据 实体加注解 Response流给前端
    //  上传 接受文件 配置路径 文件转换inputStream流 newFile(FilePath) 写到指定文件
    //  下载 找到filePath路径 new File 流 给前端

    /**
     *  EasyExcel 写excel操作 (导出)
     */
    @RequestMapping("/writeExcel")
    public ResponseBase writeExcelTest(HttpServletResponse response){

        HashMap<String, Object> requestParams = new HashMap<>();
        List<User> userList = userMapper.findUserList(requestParams);

        String fileName = "讲师信息表.xlsx";

        // 直接写入固定地址
        // String path = "D:/opt/filePath/";
        // String filePath = path + fileName;
        // EasyExcel.write(filePath, User.class).sheet("用户列表").doWrite(userList);


        // 响应内容格式
        // resp.setContentType("application/vnd.ms-excel");
        // response.setContentType("application/octet-stream");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName));

        try {
            // 向前端写入文件流流
            EasyExcel.write(response.getOutputStream(), User.class).sheet("用户列表").doWrite(userList);
        } catch (IOException e) {
            return setResultError(Constants.UPLOAD_ERROR);
        }
        return null;
    }

}

