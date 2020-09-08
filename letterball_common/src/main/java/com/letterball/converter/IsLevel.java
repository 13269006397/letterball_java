package com.letterball.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.NumberUtils;

import java.text.ParseException;

/**
 * Integer and string converter
 *
 * @author Jiaju Zhuang
 */
public class IsLevel implements Converter<Integer> {

    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    // 导入 字符转码值
    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) throws ParseException {
        if (null == cellData.getStringValue()){
            return 0;
        }else if (cellData.getStringValue().equals("高级讲师")) {
            return 1;
        }else if (cellData.getStringValue().equals("首席讲师")) {
            return 2;
        }else {
            return 99;
        }
    }

    // 导出 码值转字符
    @Override
    public CellData convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        //1 高级讲师 2 首席讲师 else 未知
        if (value == null) {
            return new CellData("");
        } else if (value == 1) {
            return new CellData("高级讲师");
        } else if (value == 2) {
            return new CellData("首席讲师");
        } else {
            return new CellData("未知");
        }
    }

}
