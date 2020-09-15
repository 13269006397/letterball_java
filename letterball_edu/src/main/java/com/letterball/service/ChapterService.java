package com.letterball.service;

import com.letterball.entity.ResponseBase;
import com.letterball.vo.CourseVO;

public interface ChapterService {

    ResponseBase getChapterTree(CourseVO courseVO);

}
