package com.letterball.service;

import com.letterball.entity.Recruit;
import com.letterball.vo.RecruitVO;

import java.util.List;

public interface RecruitService {

    List<Recruit> findRecommendList(RecruitVO recruitVO);

}
