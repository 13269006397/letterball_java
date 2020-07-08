package com.letterball.mapper;

import com.letterball.entity.FileReport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface FileReportMapper {

    int insert(FileReport record);

    int insetFile(FileReport fileReport);

    List<FileReport> findFileReport(String userId);

    void deleteFileById(String userId);

    void deleteFilesById(HashMap<String,Object> map);
}