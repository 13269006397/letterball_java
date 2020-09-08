package com.letterball.controller;

import com.alibaba.excel.EasyExcel;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.Teacher;
import com.letterball.mapper.TeacherMapper;
import com.letterball.utils.ExcelListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/easyExcel")
@RestController
public class EasyExcelController extends BaseService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ExcelListener excelListener;

    //  poi导出 查数据 查表头 拼Excel Response流给前端
    //  easyExcel导出 查数据 实体加注解 加转释 Response流给前端
    //           导入 读文件流 配监听器 配实体 码值转换 取返回值 存入数据库
    //  上传 接受文件 配置路径 文件转换inputStream流 newFile(FilePath) 写到指定文件
    //  下载 找到filePath路径 new File 流 给前端

    /**
     *  EasyExcel 写excel操作 (导出)
     *  讲师信息表 的excel导出
     */
    @RequestMapping("/writeExcel")
    public ResponseBase writeExcelTest(HttpServletResponse response){

        HashMap<String, Object> requestParams = new HashMap<>();

        List<Teacher> teacherList = teacherMapper.findTeacherList(requestParams);


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
            EasyExcel.write(response.getOutputStream(), Teacher.class).sheet("讲师信息表").doWrite(teacherList);
        } catch (IOException e) {
            return setResultError(Constants.UPLOAD_ERROR);
        }
        return null;
    }

    /**
     * 读操作 easyExcel (导入)
     * 讲师信息表的 导入
     * @return
     */
    @RequestMapping("/readExcel")
    public ResponseBase readExcel(MultipartFile file){

        try {
            // 读取excel数据
            EasyExcel.read(file.getInputStream(), Teacher.class, new ExcelListener()).sheet().doRead();

            // 获取excel数据
            List<Teacher> teachers = excelListener.getDatas();

            // 存入数据库
            for (int i = 0; i < teachers.size(); i++) {
                Teacher teacher = new Teacher();
                teacher.setId(teachers.get(i).getId());
                teacher.setName(teachers.get(i).getName());
                teacher.setIntro(teachers.get(i).getIntro());
                teacher.setCareer(teachers.get(i).getCareer());
                teacher.setLevel(teachers.get(i).getLevel());
                teacher.setAvatar(teachers.get(i).getAvatar());
                teacher.setSort(teachers.get(i).getSort());
                teacher.setIsDeleted(teachers.get(i).getIsDeleted());
                // 配置初始化数据
                teacher.setGmtCreate(new Date());
                teacher.setGmtModified(new Date());
                    teacherMapper.insertTeacher(teacher);
            }
        }catch (IOException e){
            return setResultError(Constants.DOWNLOAD_ERROR);
        }

        return setResultSuccessMsg(Constants.DOWNLOAD_SUCCESS);
    }

}

