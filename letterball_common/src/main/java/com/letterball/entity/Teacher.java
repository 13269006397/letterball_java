package com.letterball.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.letterball.converter.IsDelete;
import com.letterball.converter.IsLevel;
import lombok.Data;

import java.util.Date;

@Data
public class Teacher {

    @ExcelProperty(value = "讲师id", index = 0)
    private String id;

    @ExcelProperty(value = "讲师名称", index = 1)
    private String name;

    @ExcelProperty(value = "讲师简介", index = 2)
    private String intro;

    @ExcelProperty(value = "讲师资历", index = 3)
    private String career;

    // 导出 导入得时候 用converter 做 码值和String字符串的转换
    @ExcelProperty(value = "讲师头衔", converter = IsLevel.class, index = 4)
    private Integer level;

    @ExcelProperty(value = "讲师头像", index = 5)
    private String avatar;

    @ExcelProperty(value = "排序", index = 6)
    private Integer sort;

    @ExcelProperty(value = "状态", converter = IsDelete.class, index = 7)
    private String isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

}