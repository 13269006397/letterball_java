package com.letterball.service.impl;

import com.letterball.common.Constants;
import com.letterball.entity.Recruit;
import com.letterball.mapper.RecruitMapper;
import com.letterball.service.RecruitService;
import com.letterball.vo.RecruitVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    RecruitMapper recruitMapper;

    @Override
    public List<Recruit> findRecommendList(RecruitVO recruitVO) {

        HashMap<String, Object> requestParams = new HashMap<>();
        if (!StringUtils.isEmpty(recruitVO.getState())){
            requestParams.put(Constants.SEARCH_STATE,recruitVO.getState());
        }
        return recruitMapper.findRecommendList(requestParams);
    }
}
