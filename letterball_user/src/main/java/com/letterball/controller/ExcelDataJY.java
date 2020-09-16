package com.letterball.controller;

import com.letterball.common.Constants;
import com.letterball.entity.ExcelErrorRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/excel")
@RestController
public class ExcelDataJY {

    /**
     * 数据校验
     * @param file
     * @return
     */
    // 1. 文件 获得inputSteam输入流
    // 2. 将文件 new XSSFWorkbook 生成Excel数据
    // 3. 获得sheet页
    // 4. sheet.getRow(?) 获得第几行 数据
    // 5. row.getCell(?) 获得行中某列数据
    // 6. getStringValue 提取列中数据
    @PostMapping("/readExcel")
    public HashMap readExcel(MultipartFile file) {
        Workbook workbook = null;
        Sheet sheet = null;
        Row row = null;

        HashMap<String, Object> resultMap = new HashMap<>();
        HashMap<String, String> allMap = new HashMap<>();

        try{
            InputStream in = file.getInputStream();
            workbook = new XSSFWorkbook(in);

            // 1.获取Excel表单
            sheet = workbook.getSheetAt(0);

            for(int rowNum = 7; rowNum < 33; rowNum++) {
                //获得第一行
                row = sheet.getRow(rowNum);

                //第三列数据
                Cell cell1 = row.getCell(2);
                //第四列数据
                Cell cell2 = row.getCell(3);
                //第五列数据
                Cell cell3 = row.getCell(4);

                allMap.put("C"+(rowNum+1), getStringValue(cell1));
                allMap.put("D"+(rowNum+1), getStringValue(cell2));
                allMap.put("E"+(rowNum+1), getStringValue(cell3));
            }

        }catch (IOException e){
            resultMap.put("error", "数据读取错误");
            return resultMap;

        }
        // 2.数据校验
        // 2.1非空校验

        // 模拟获取需要非空校验得数据
        List<String> nullList = Arrays.asList("E8", "E11", "C8", "D8", "E8", "C15", "D15", "E15", "E16", "C17", "D17", "E17", "E18", "C19",
                "D19", "E19", "C20", "D20", "E20", "E21", "E22", "E23", "E24", "E25", "E26", "C28", "D28", "C29", "D29", "C30", "D30",
                "C31", "D31", "C32", "D32", "C33", "D33");

        // 填充数据
        ArrayList<String> notNullList = new ArrayList<>();
        notNullList.addAll(nullList);

        // 组装报错信息
        ArrayList<ExcelErrorRecord> errorList = new ArrayList<>();

        for (int i = 0; i < notNullList.size(); i++) {
            if (allMap.get(notNullList.get(i)) == null ||  "".equals(allMap.get(notNullList.get(i)))){
                ExcelErrorRecord err = new ExcelErrorRecord();
                err.setErrorMsg(notNullList.get(i) + Constants.CELL_NOT_NULL);
                errorList.add(err);
            }
        }

        // 2.2 合计校验
        for (int i = 12; i < 23; i++) {
            if (null != allMap.get("C"+i) && null != allMap.get("D"+i) && null != allMap.get("E"+i)){
                if ((Integer.parseInt(allMap.get("C"+i))) + (Integer.parseInt(allMap.get("D"+i))) != (Integer.parseInt(allMap.get("E"+i)))){
                    ExcelErrorRecord err = new ExcelErrorRecord();
                    err.setErrorMsg("E"+ i + Constants.SUM_ERR);
                    errorList.add(err);
                }
            }
        }

        // 2.3 其它校验规则 其中：本年度累计跨省收购不良资产投资额 > 0 从事跨省收购不良资产 为1  否则 为0
        if (null != allMap.get("E18") && Integer.parseInt(allMap.get("E18")) > 0 ){
            if (null == allMap.get("E10") || !allMap.get("E10").equals("1")){
                ExcelErrorRecord err = new ExcelErrorRecord();
                err.setErrorMsg(Constants.E10_E18_ERR);
                errorList.add(err);
            }
        }
        if (null != allMap.get("E18") && Integer.parseInt(allMap.get("E18")) <= 0 ){
            if (null == allMap.get("E10") || !allMap.get("E10").equals("0")){
                ExcelErrorRecord err = new ExcelErrorRecord();
                err.setErrorMsg(Constants.E10_E18_ERR);
                errorList.add(err);
            }
        }

        resultMap.put("success", Constants.SUCCESS_SEARCH);
        resultMap.put("data", allMap);
        resultMap.put("errorList", errorList);
        return resultMap;
    }


    /**
     * 获取单元格的值的字符串
     * @param cell 单元格对象
     * @return cell单元格的值的字符串
     */
    private static String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        CellType cellType = CellType.forInt(cell.getCellType());
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                double value = cell.getNumericCellValue();
                return String.valueOf(Math.round(value));
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }


}
