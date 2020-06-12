package com.letterball.mapper;

import com.letterball.entity.ProblemLabel;

public interface ProblemLabelMapper {

    int deleteByPrimaryKey(ProblemLabel problemLabel);

    int insert(ProblemLabel problemLabel);

    int insertSelective(ProblemLabel problemLabel);
}