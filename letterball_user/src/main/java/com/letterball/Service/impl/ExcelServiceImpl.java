package com.letterball.Service.impl;

import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.User;
import com.letterball.mapper.UserMapper;
import com.letterball.Service.ExcelService;
import com.letterball.utils.ColumnUtils;
import com.letterball.vo.UserVO;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import static com.letterball.utils.DateUtils.dateToString;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ColumnUtils columnUtils;

    @Override
    public ResponseBase uploadExcel(MultipartFile file) {
        return null;
    }

    /**
     * excel导出
     * @param userVO
     * @return ResponseBase
     */
    @Override
    public ResponseBase downLoadExcel(UserVO userVO, HttpServletResponse response) {

        // 查询用户列表
        HashMap<String, Object> requestParams = new HashMap<>();
        if (!StringUtils.isEmpty(userVO.getMobile())) {
            requestParams.put(Constants.SEARCH_MOBILE, userVO.getMobile());
        }
        if (!StringUtils.isEmpty(userVO.getNickName())) {
            requestParams.put(Constants.SEARCH_NICK_NAME, userVO.getNickName());
        }
        if (!StringUtils.isEmpty(userVO.getPermission())) {
            requestParams.put(Constants.SEARCH_PERMISSION, userVO.getPermission());
        }
        if (!StringUtils.isEmpty(userVO.getIsDelete())) {
            requestParams.put(Constants.SEARCH_IS_DELETE, userVO.getIsDelete());
        }
        List<User> userList = userMapper.findUserList(requestParams);


        // 1. 创建excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2. 创建sheet页
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        String fileName = "用户信息表"  + ".xls";//设置要导出的文件的名字

        // headers表示excel表中第一行的表头
        List headersColumnName = columnUtils.getColumnName(20,"select * from tb_user");

        // 3. sheet.createRow 创建行
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headersColumnName.size();i++){
            // 4. row.createCell(?) 创建列 写入数据
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString((String) headersColumnName.get(i));
            cell.setCellValue(text);
        }

        // excel数据转码
        HashMap<String, Object> map = new HashMap<>();
        map.put("01","男");
        map.put("02","女");

        HashMap<String, Object> userStatus = new HashMap<>();
        userStatus.put("01", "正常");
        userStatus.put("02", "冻结");

        //在表中存放查询到的数据放入对应的列  1行
        int rowNum = 1;
        for (User user : userList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(user.getId()== null ? "":user.getId());
            row1.createCell(1).setCellValue(user.getMobile()== null ? "":user.getMobile());
            row1.createCell(2).setCellValue(user.getPassword()== null ? "":user.getPassword() );
            row1.createCell(3).setCellValue(user.getNickName()== null ? "":user.getNickName());
            row1.createCell(4).setCellValue(user.getSex()== null ? "": (String) map.get(user.getSex()));
            row1.createCell(5).setCellValue(user.getBirthday()== null ? "":dateToString(user.getBirthday(),"yyyy年MM月dd日"));
            row1.createCell(6).setCellValue(user.getAvatar()== null ? "":user.getAvatar());
            row1.createCell(7).setCellValue(user.getEmail()== null ? "":user.getEmail());
            row1.createCell(8).setCellValue(user.getRegTime()== null ? "":dateToString(user.getRegTime(),"yyyy年MM月dd日"));
            row1.createCell(9).setCellValue(user.getUpdateTime()== null ? "":dateToString(user.getUpdateTime(),"yyyy年MM月dd日"));
            row1.createCell(10).setCellValue(user.getLastTime()== null ? "":dateToString(user.getLastTime(),"yyyy年MM月dd日"));
            row1.createCell(11).setCellValue((user.getOnLine())== null ? 0:user.getOnLine());
            row1.createCell(12).setCellValue(user.getInterest()== null ? "":user.getInterest());
            row1.createCell(13).setCellValue(user.getPersonality()== null ? "":user.getPersonality());
            row1.createCell(14).setCellValue(user.getFansCount()== null ? 0:user.getFansCount());
            row1.createCell(15).setCellValue(user.getFollowCount()== null ? 0:user.getFollowCount());
            row1.createCell(16).setCellValue(user.getIsDelete()== null ? "": (String) userStatus.get(user.getIsDelete()));
            row1.createCell(17).setCellValue(user.getIdNumber()== null ? "":user.getIdNumber());
            row1.createCell(18).setCellValue(user.getAge()== null ? "":user.getAge());
            row1.createCell(19).setCellValue(user.getFileCode()== null ? "":user.getFileCode());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName));

        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
