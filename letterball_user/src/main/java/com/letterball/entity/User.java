package com.letterball.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.letterball.converter.IsDelete;
import com.letterball.converter.IsPermission;
import com.letterball.converter.IsSex;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @ExcelProperty(value = "用户id", index = 0)
    private String id;

    @ExcelProperty(value = "用户编号", index =1)
    private String idNumber;

    @ExcelProperty(value = "手机号", index =2)
    private String mobile;

    // 不导出注解
    @ExcelIgnore
    private String password;

    @ExcelProperty(value = "年龄", index =3)
    private String age;

    @ExcelProperty(value = "昵称", index =4)
    private String nickName;

    // 多引一个转换class 做码值转换
    @ExcelProperty(value = "性别", converter = IsSex.class, index =5)
    private String sex;

    @ExcelProperty(value = "生日", index =6)
    private Date birthday;

    @ExcelIgnore
    private String avatar;

    @ExcelProperty(value = "邮箱", index =7)
    private String email;

    @ExcelProperty(value = "注册时间", index =8)
    private Date regTime;

    @ExcelProperty(value = "修改时间", index =9)
    private Date updateTime;

    @ExcelProperty(value = "最后登陆时间", index =10)
    private Date lastTime;

    @ExcelProperty(value = "在线时长", index =11)
    private Long onLine;

    @ExcelProperty(value = "兴趣", index =12)
    private String interest;

    @ExcelProperty(value = "个性", index =13)
    private String personality;

    @ExcelProperty(value = "粉丝数", index =14)
    private Integer fansCount;

    @ExcelProperty(value = "关注数", index =15)
    private Integer followCount;

    @ExcelProperty(value = "是否冻结", converter = IsDelete.class, index =16)
    private String isDelete;

    //权限表数据
    @ExcelProperty(value = "权限", converter = IsPermission.class, index =17)
    private String permission;

    @ExcelIgnore
    private String fileCode;

}