package com.letterball.controller;

import com.alibaba.excel.util.IoUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.letterball.entity.ResponseBase;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/pdf")
@RestController
public class testController {

    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font keyfont;
    private static Font textfont;
    // 最大宽度
    private static int maxWidth = 520;
    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            textfont = new Font(bfChinese, 10, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String company = "华林小额贷款公司";
    private static String orgName = "杭州市";
    private static String city = "杭州市";
    private static String supervOrgName = "杭州";
    private static String Hao = "20209022";
    private static String JC = "证件资历、申报材料";
    private static String JCSJ = "2020年8月25日";
    private static String TGCL = "身份证、驾照、行驶本";
    private static String JCZZZ = "奥特之母";
    private static String JCZCY = "迪迦奥特曼、迪迦他老舅、赛文他老姐、奥特之父";



    @GetMapping("/download")
    public void download(HttpServletResponse response){
        try ( ServletOutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/octet-stream");
            response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
            response.setHeader("pragma","no-cache");

            response.addHeader("Content-Disposition","attachement;filename=gsdgf.pdf");

            // 1.新建document对象
            Document document = new Document(PageSize.A4.rotate());// 建立一个Document对象

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
            String nowTime = sdf.format(new Date());

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
            String nowDate = sdf1.format(new Date());

            String filePath = "D:/opt/filePath/";
            String fileName = company+"-现场检查通知书("+nowTime+").pdf";

            // 2.建立一个书写器(Writer)与document对象关联
            PdfWriter.getInstance(document, outputStream);

            // 3.打开文档
            document.open();
            document.addTitle("现场检查通知书");// 标题
            document.addAuthor("YWB");// 作者
            document.addSubject("YWB pdf sample");// 主题
            document.addKeywords("KeyWords");// 关键字
            document.addCreator("YWB");// 创建者

            // 4.向文档中添加内容
            // 段落
            Paragraph paragraph = new Paragraph(orgName + "人民政府", titlefont);
            paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
            paragraph.setIndentationLeft(12); //设置左缩进
            paragraph.setIndentationRight(12); //设置右缩进
            paragraph.setFirstLineIndent(24); //设置首行缩进
            paragraph.setLeading(15f); //行间距
            paragraph.setSpacingBefore(5f); //设置段落上空白
            paragraph.setSpacingAfter(10f); //设置段落下空白


            Paragraph tital = new Paragraph("现场检查通知书", titlefont);
            tital.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
            tital.setIndentationLeft(12); //设置左缩进
            tital.setIndentationRight(12); //设置右缩进
            tital.setFirstLineIndent(24); //设置首行缩进
            tital.setLeading(5f); //行间距
            tital.setSpacingBefore(5f); //设置段落上空白
            tital.setSpacingAfter(10f); //设置段落下空白

            Paragraph companyName = new Paragraph(company+":", headfont);
            companyName.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            companyName.setIndentationLeft(50); //设置左缩进
            //companyName.setIndentationRight(500); //设置右缩进
            companyName.setFirstLineIndent(24); //设置首行缩进
            companyName.setLeading(30f); // 与上行间距
            companyName.setSpacingBefore(5f); //设置段落上空白
            companyName.setSpacingAfter(10f); //设置段落下空白

            Paragraph tital1 = new Paragraph("依据《"+city+"小额贷款公司监督管理暂行方法》("+supervOrgName+"政办发[2014]"+Hao+"号)，特派出检查组对你公司进行现场检查。", headfont);
            tital1.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            tital1.setIndentationLeft(80); //设置左缩进
            tital1.setIndentationRight(80); //设置右缩进
            tital1.setFirstLineIndent(24); //设置首行缩进
            tital1.setLeading(15f); //行间距
            tital1.setSpacingBefore(15f); //设置段落上空白
            tital1.setSpacingAfter(10f); //设置段落下空白

            Paragraph body1 = new Paragraph("检查内容: "+ JC, headfont);
            body1.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            body1.setIndentationLeft(80); //设置左缩进
            body1.setIndentationRight(80); //设置右缩进
            body1.setFirstLineIndent(24); //设置首行缩进
            body1.setLeading(13f); //行间距
            body1.setSpacingBefore(5f); //设置段落上空白
            body1.setSpacingAfter(5f); //设置段落下空白

            Paragraph body2 = new Paragraph("检查时间: "+ JCSJ, headfont);
            body2.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            body2.setIndentationLeft(80); //设置左缩进
            body2.setIndentationRight(80); //设置右缩进
            body2.setFirstLineIndent(24); //设置首行缩进
            body2.setLeading(15f); //行间距
            body2.setSpacingBefore(5f); //设置段落上空白
            body2.setSpacingAfter(5f); //设置段落下空白

            Paragraph body3 = new Paragraph("提供材料清单: "+ TGCL, headfont);
            body3.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            body3.setIndentationLeft(80); //设置左缩进
            body3.setIndentationRight(80); //设置右缩进
            body3.setFirstLineIndent(24); //设置首行缩进
            body3.setLeading(15f); //行间距
            body3.setSpacingBefore(5f); //设置段落上空白
            body3.setSpacingAfter(5f); //设置段落下空白

            Paragraph body4 = new Paragraph("请你公司积极配合现场检查，并提供必要的工作条件。"+ TGCL, headfont);
            body4.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            body4.setIndentationLeft(80); //设置左缩进
            body4.setIndentationRight(80); //设置右缩进
            body4.setFirstLineIndent(24); //设置首行缩进
            body4.setLeading(15f); //行间距
            body4.setSpacingBefore(5f); //设置段落上空白
            body4.setSpacingAfter(5f); //设置段落下空白

            Paragraph body5 = new Paragraph("检查组组长："+ JCZZZ, headfont);
            body5.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            body5.setIndentationLeft(80); //设置左缩进
            body5.setIndentationRight(80); //设置右缩进
            body5.setFirstLineIndent(24); //设置首行缩进
            body5.setLeading(15f); //行间距
            body5.setSpacingBefore(5f); //设置段落上空白
            body5.setSpacingAfter(5f); //设置段落下空白

            Paragraph body6 = new Paragraph("检查组成员："+ JCZCY, headfont);
            body6.setAlignment(0); //设置文字居中 0靠左   1，居中     2，靠右
            body6.setIndentationLeft(80); //设置左缩进
            body6.setIndentationRight(80); //设置右缩进
            body6.setFirstLineIndent(24); //设置首行缩进
            body6.setLeading(15f); //行间距
            body6.setSpacingBefore(5f); //设置段落上空白
            body6.setSpacingAfter(5f); //设置段落下空白

            Paragraph body7 = new Paragraph(orgName + "人民政府", headfont);
            body7.setAlignment(2); //设置文字居中 0靠左   1，居中     2，靠右
            body7.setIndentationLeft(80); //设置左缩进
            body7.setIndentationRight(80); //设置右缩进
            body7.setFirstLineIndent(24); //设置首行缩进
            body7.setLeading(100f); //行间距
            body7.setSpacingBefore(5f); //设置段落上空白
            body7.setSpacingAfter(5f); //设置段落下空白

            Paragraph body8 = new Paragraph(nowDate, headfont);
            body8.setAlignment(2); //设置文字居中 0靠左   1，居中     2，靠右
            body8.setIndentationLeft(80); //设置左缩进
            body8.setIndentationRight(80); //设置右缩进
            body8.setFirstLineIndent(24); //设置首行缩进
            body8.setLeading(20f); //行间距
            body8.setSpacingBefore(5f); //设置段落上空白
            body8.setSpacingAfter(5f); //设置段落下空白


            document.add(paragraph);
            document.add(tital);
            document.add(companyName);
            document.add(tital1);
            document.add(body1);
            document.add(body2);
            document.add(body3);
            document.add(body4);
            document.add(body5);
            document.add(body6);
            document.add(body7);
            document.add(body8);


            // 5.关闭文档
            document.close();



//            // 输入到页面
//            InputStream in = null;
//
//            // 文件流
//            in =new FileInputStream(filePath+fileName);
//
//            byte[] bytes = new byte[in.available()];
//            in.read(bytes);
//
//            return ResponseEntity.ok().body(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return null;
    }
}
