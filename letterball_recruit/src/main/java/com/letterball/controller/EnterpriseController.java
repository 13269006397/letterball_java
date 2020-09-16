package com.letterball.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Enterprise;
import com.letterball.entity.ResponseBase;
import com.letterball.Service.EnterpriseService;
import com.letterball.vo.EnterpriseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 招聘-公司信息
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController extends BaseService {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 查询公司
     * @param enterpriseVO
     * @return
     */
    @RequestMapping("/findEnterpriseList")
    public ResponseBase findEnterpriseList(@RequestBody EnterpriseVO enterpriseVO){
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = Constants.COMM_DATA_ZERO;
        PageHelper.startPage(enterpriseVO.getPage(),enterpriseVO.getLimit());
        List<Enterprise> findSearchList = enterpriseService.findAllEnterprise(enterpriseVO);
        total = ((Page<Enterprise>)findSearchList).getTotal();
        resultMap.put(Constants.COMM_QUERY_RESP_ITEM,findSearchList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL,total);
        return setResultSuccessData(resultMap);
    }
}
