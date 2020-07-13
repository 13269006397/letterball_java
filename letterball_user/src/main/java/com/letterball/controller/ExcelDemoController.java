package com.letterball.controller;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.Staff;
import com.letterball.entity.User;
import com.letterball.mapper.StaffMapper;
import com.letterball.service.UserService;
import com.letterball.utils.ExcelUtil;
import com.letterball.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/excel")
@RestController
public class ExcelDemoController extends BaseService {

    @Autowired
    private UserService userService;

    @Autowired
    private StaffMapper staffMapper;

    /**
     * 读excel数据(<1000)
     */
    @PostMapping("/readExcel1000Row")
    public ResponseBase readExcel1000Row(@RequestBody UserVO userVO){


        String fileName = "王松的性能测试表.xlsx";
        String path = "D:/opt/filePath/";

        String filePath = path + fileName;

        Staff staff = new Staff();

        ArrayList<String> idList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> mobileList = new ArrayList<>();


        try {
            // 读取Excel的数据
            List<Object> ExcelList = ExcelUtil.readLessThan1000Row(filePath);
            

        }catch (Exception e){
            return setResultError(Constants.DOWNLOAD_ERROR);
        }
        return setResultSuccessMsg(Constants.DOWNLOAD_SUCCESS);

    }


    /**
     * 导出用户数据
     */
    @PostMapping("/DownLoadUserList")
    public ResponseBase DownLoadUserList(@RequestBody UserVO userVO){

        String fileName = "王松的性能测试表.xlsx";
        String path = "D:/opt/filePath/";

        String filePath = path + fileName;


        try {
            List<User> usersList = userService.findUsersList(userVO);

            List<List<Object>> data = new ArrayList<>();
            for (int i = 0; i < usersList.size(); i++) {
                data.add(Arrays.asList(usersList.get(i).getId(),
                        usersList.get(i).getNickName(),
                        usersList.get(i).getMobile()));
            }
            System.err.println(data);
            List<String> head = Arrays.asList("编号", "用户名", "手机号");
            ExcelUtil.writeBySimple(filePath,data,head);


        }catch (Exception e){
            return setResultError(Constants.UPLOAD_ERROR);
        }
        return setResultSuccessMsg(Constants.UPLOAD_SUCCESS);
    }



    //把list内容逐个取出来放进User实体类中
    public static <T> void listToModel(List<Object> list, T t) throws Exception {
        Field[] fields = t.getClass().getDeclaredFields();
        if (list.size() != fields.length) {
            return;
        }
        for (int k = 0, len = fields.length; k < len; k++) {
            // 根据属性名称,找寻合适的set方法
            String fieldName = fields[k].getName();
            String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1);
            Method method = null;
            Class<?> clazz = t.getClass();
            try {
                method = clazz.getMethod(setMethodName, new Class[] { list.get(k).getClass() });
                System.out.println("list.get("+k+").getClass():"+list.get(k).getClass());
            } catch (SecurityException e1) {
                e1.printStackTrace();
                return;
            } catch (NoSuchMethodException e1) {
                String newMethodName = "set" + fieldName.substring(0, 1).toLowerCase()
                        + fieldName.substring(1);
                try {
                    method = clazz.getMethod(newMethodName, new Class[] { list.get(k).getClass() });
                } catch (SecurityException e) {
                    e.printStackTrace();
                    return;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (method == null) {
                return;
            }
            method.invoke(t, new Object[] { list.get(k) });
        }
    }
}

