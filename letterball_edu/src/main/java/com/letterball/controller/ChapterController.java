package com.letterball.controller;

import com.letterball.entity.ResponseBase;
import com.letterball.service.ChapterService;
import com.letterball.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 获得课程信息
     */
    @RequestMapping("/chapterTree")
    public ResponseBase chapterTree(@RequestBody CourseVO courseVO){
        return chapterService.getChapterTree(courseVO);
    }
}
