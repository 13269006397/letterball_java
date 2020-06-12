package com.letterball.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Problem;
import com.letterball.entity.ResponseBase;
import com.letterball.service.ProblemService;
import com.letterball.vo.ProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController extends BaseService {

    @Autowired
    private ProblemService problemService;

    /**
     * 问题列表查询
     * @param problemVO
     * @return
     */
    @RequestMapping("/search/problem")
    public ResponseBase findProblem(@RequestBody ProblemVO problemVO){

        HashMap<String, Object> resultMap = new HashMap<>();
        long total = Constants.COMM_DATA_ZERO;
        PageHelper.startPage(problemVO.getPage(),problemVO.getLimit());
        List<Problem> findSearchList = problemService.findProblem(problemVO);
        total = ((Page<Problem>)findSearchList).getTotal();

        resultMap.put(Constants.COMM_QUERY_RESP_ITEM,findSearchList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL,total);
        return setResultSuccessData(resultMap);

    }


}
