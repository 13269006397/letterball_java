package com.letterball.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

//  StringToString
public class IsPermission implements Converter<String> {
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        return cellData.getStringValue();
    }

    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        //01男 02女 03未知
        if (value == null) {
            return new CellData("");
        } else if (value.equals("01")) {
            return new CellData("管理员");
        } else if (value.equals("02")) {
            return new CellData("普通用户");
        } else if (value.equals("03")) {
            return new CellData("Vip用户");
        } else {
            return new CellData("未知");
        }
    }
}