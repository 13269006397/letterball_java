package com.letterball.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Recruit;
import com.letterball.entity.ResponseBase;
import com.letterball.service.RecruitService;
import com.letterball.vo.RecruitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 招聘-岗位信息
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController extends BaseService {

    @Autowired
    RecruitService recruitService;

    /**
     * 查询招聘信息
     */
    @RequestMapping("/search/recommend")
    public ResponseBase findRecommendList(@RequestBody RecruitVO recruitVO){
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = Constants.COMM_DATA_ZERO;
        PageHelper.startPage(recruitVO.getPage(),recruitVO.getLimit());
        List<Recruit> findSearchList = recruitService.findRecommendList(recruitVO);
        total = ((Page<Recruit>)findSearchList).getTotal();
        resultMap.put(Constants.COMM_QUERY_RESP_ITEM,findSearchList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL,total);
        return setResultSuccessData(resultMap);
    }
}
