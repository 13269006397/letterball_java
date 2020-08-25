package com.letterball.service;


import com.letterball.entity.ResponseBase;
import com.letterball.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Service
public interface ExcelService {

    ResponseBase uploadExcel (MultipartFile file);

    ResponseBase downLoadExcel (UserVO userVO, HttpServletResponse response);


}
