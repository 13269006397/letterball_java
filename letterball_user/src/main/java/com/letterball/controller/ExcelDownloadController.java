package com.letterball.controller;

import com.letterball.entity.ResponseBase;
import com.letterball.service.ExcelService;
import com.letterball.vo.BaseVO;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * POI Excel导出 导入
 */
@RestController
@RequestMapping("/excel")
public class ExcelDownloadController {

    @Autowired
    private ExcelService excelService;

    /**
     * excel导入
     * @param file
     * @return ResponseBase
     */
    @RequestMapping("/uploadExcel")
    public ResponseBase uploadExcel(MultipartFile file){

        return null;
    }

    /**
     * excel导出
     * @param userVO
     * @return ResponseBase
     */
    @RequestMapping("/downLoadExcel")
    public ResponseBase downLoadExcel(@RequestBody UserVO userVO, HttpServletResponse response){

        return excelService.downLoadExcel(userVO,response);
    }

}
