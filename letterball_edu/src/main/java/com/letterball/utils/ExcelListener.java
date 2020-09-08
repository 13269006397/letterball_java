package com.letterball.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.letterball.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * excel监听类
 */
@Slf4j
@Repository
public class ExcelListener extends AnalysisEventListener<Teacher> {

    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 2;
    public static List<Teacher> list = new ArrayList<>();


    // 一行一行读excel数据
    @Override
    public void invoke(Teacher teacher, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(teacher));
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
        list.add(teacher);
    }

    // 读取表头数据
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData(){
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
    }

    public List<Teacher> getDatas() {
        return list;
    }

    public void setDatas(List<Teacher> datas) {
        this.list = datas;
    }

}
