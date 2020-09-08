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

    @ExcelProperty(value = "用户id")
    private String id;

    @ExcelProperty(value = "用户编号")
    private String idNumber;

    @ExcelProperty(value = "手机号")
    private String mobile;

    @ExcelProperty(value = "密码")
    // 不导出注解
    @ExcelIgnore
    private String password;

    @ExcelProperty(value = "年龄")
    private String age;

    @ExcelProperty(value = "昵称")
    private String nickName;

    // 多引一个转换class 做码值转换
    @ExcelProperty(value = "性别", converter = IsSex.class)
    private String sex;

    @ExcelProperty(value = "生日")
    private Date birthday;

    @ExcelProperty(value = "头像地址")
    private String avatar;

    @ExcelProperty(value = "邮箱")
    private String email;

    @ExcelProperty(value = "注册时间")
    private Date regTime;

    @ExcelProperty(value = "修改时间")
    private Date updateTime;

    @ExcelProperty(value = "最后登陆时间")
    private Date lastTime;

    @ExcelProperty(value = "在线时长")
    private Long onLine;

    @ExcelProperty(value = "兴趣")
    private String interest;

    @ExcelProperty(value = "个性")
    private String personality;

    @ExcelProperty(value = "粉丝数")
    private Integer fansCount;

    @ExcelProperty(value = "关注数")
    private Integer followCount;

    @ExcelProperty(value = "是否冻结", converter = IsDelete.class)
    private String isDelete;

    //权限表数据
    @ExcelProperty(value = "权限", converter = IsPermission.class)
    private String permission;

    @ExcelIgnore
    @ExcelProperty(value = "附件code")
    private String fileCode;

}