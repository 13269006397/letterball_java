package com.letterball.Service;

import com.letterball.entity.Problem;
import com.letterball.vo.ProblemVO;

import java.util.List;

public interface ProblemService {

    List<Problem> findProblem(ProblemVO problemVO);

}
