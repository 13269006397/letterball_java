package com.letterball.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.ResponseBase;
import com.letterball.entity.Label;
import com.letterball.service.LabelService;
import com.letterball.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 标签业务处理
 */
@RestController
@RequestMapping("/label")
public class LabelController extends BaseService {

    @Autowired
    private LabelService labelService;


    /**
     * 查询所有标签
     */
    @RequestMapping("/findAllLabel")
    public ResponseBase findAllLabel(){
        List<Label> labels = labelService.selectAllLabel();
        return setResultSuccessData(labels);
    }

    /**
     * 根据Id查询标签
     * @param labelVO
     * @return
     */
    @RequestMapping("/findLabelById")
    public ResponseBase findLabelById(@RequestBody LabelVO labelVO){
        Label label = labelService.findLabelById(labelVO);
        if (null != label){
            return setResultSuccessData(label);
        }else {
        return setResultError(Constants.FIND_NULL);
        }
    }

    /**
     * 条件查询
     * @param labelVO
     * @return
     */
    @RequestMapping("/findSearch")
    public ResponseBase findSearch(@RequestBody LabelVO labelVO){
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = Constants.COMM_DATA_ZERO;
        PageHelper.startPage(labelVO.getPage(),labelVO.getLimit());
        List<Label> findSearchList = labelService.findSearch(labelVO);
        total = ((Page<Label>)findSearchList).getTotal();

        resultMap.put(Constants.COMM_QUERY_RESP_ITEM,findSearchList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL,total);
        return setResultSuccessData(findSearchList);
    }

    /**
     * 新增标签
     * @param label
     * @return
     */
    @RequestMapping("/addLabel")
    public ResponseBase addLabel(@RequestBody Label label){
        try {
            labelService.addLabel(label);
        }catch (Exception e){
            return setResultError(Constants.ADD_ERROR);
        }
        return setResultSuccessMsg(Constants.ADD_SUCCESS);
    }

    /**
     * 修改标签
     * @param label
     * @return
     */
    @RequestMapping("/updateLabelById")
    public ResponseBase updateLabelById(@RequestBody Label label){
        try {
            labelService.updateLabelById(label);
        }catch (Exception e){
            return setResultError(Constants.UPDATE_ERROR);
        }
        return setResultSuccessMsg(Constants.UPDATE_SUCCESS);
    }

    /**
     * 删除标签
     */
    @RequestMapping("/deleteLabelById")
    public ResponseBase deleteLabelById(@RequestBody Label label){
        try {
            labelService.deleteLabelById(label);
        }catch (Exception e){
            return setResultError(Constants.DELETE_ERROR);
        }
        return setResultSuccessMsg(Constants.DELETE_SUCCESS);
    }

}
