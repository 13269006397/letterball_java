package com.letterball.service.impl;

import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Chapter;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.Video;
import com.letterball.mapper.ChapterMapper;
import com.letterball.mapper.VideoMapper;
import com.letterball.service.ChapterService;
import com.letterball.vo.ChapterVO;
import com.letterball.vo.CourseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChapterServiceImpl extends BaseService implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 获得章节树
     * @return
     */
    @Override
    public ResponseBase getChapterTree(CourseVO courseVO) {
        // 最终封装类
        List<ChapterVO> chapterVOList = new ArrayList<>();
        HashMap<String, Object> requestMap = new HashMap<>();
        // 获取当前课程id
        String courseId = courseVO.getCourseId();
        requestMap.put(Constants.COURSE_ID, courseId);

        // 1.0获得所有章节
        List<Chapter> allChapter = chapterMapper.findAllChapter(requestMap);
        // 获得所有小节
        List<Video> allVideo = videoMapper.findAllVideo(requestMap);

        // 2.0 组装章节小节树
        for (int i = 0; i < allChapter.size(); i++) {
            // 获得每个章节
            Chapter chapter = allChapter.get(i);
            ChapterVO chapterVO = new ChapterVO();
            // 拼装一级结构
            BeanUtils.copyProperties(chapter, chapterVO);
            chapterVOList.add(chapterVO);

            for (int m = 0; m < allVideo.size(); m++) {
                // 获得每个小节
                Video video = allVideo.get(m);
                if (video.getChapterId().equals(chapter.getId())){
                    chapterVOList.get(i).getChildren().add(video);
                }
            }
        }

        return setResultSuccessData(chapterVOList);
    }
}
