package com.letterball.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

//  StringToString
public class IsDelete implements Converter<String> {
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
        if (null == cellData.getStringValue()){
            return "未知";
        }else if (cellData.getStringValue().equals("冻结")) {
            return "1";
        }else if (cellData.getStringValue().equals("正常")) {
            return "0";
        }else {
            return "99";
        }
    }

    @Override
    public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        //01男 02女 03未知
        if (value == null) {
            return new CellData("");
        } else if (value.equals("01")) {
            return new CellData("正常");
        } else if (value.equals("02")) {
            return new CellData("冻结");
        } else if (value.equals("1")) {
            return new CellData("冻结");
        } else if (value.equals("0")) {
            return new CellData("正常");
        }else {
            return new CellData("未知");
        }
    }
}