package com.letterball.Service.impl;

import com.letterball.common.Constants;
import com.letterball.entity.Problem;
import com.letterball.mapper.ProblemMapper;
import com.letterball.Service.ProblemService;
import com.letterball.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemMapper problemMapper;


    @Override
    public List<Problem> findProblem(ProblemVO problemVO) {

        HashMap<String, Object> requestParmas = new HashMap<>();

        if (!StringUtils.isEmpty(problemVO.getLabelId())){
            requestParmas.put(Constants.SEARCH_LABEL_ID,problemVO.getLabelId());
        }
        return problemMapper.findProblem(requestParmas);
    }
}
