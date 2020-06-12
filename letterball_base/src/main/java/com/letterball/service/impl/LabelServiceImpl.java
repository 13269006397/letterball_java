package com.letterball.service.impl;

import com.letterball.common.Constants;
import com.letterball.entity.Label;
import com.letterball.mapper.LabelMapper;
import com.letterball.service.LabelService;
import com.letterball.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public List<Label> selectAllLabel() {
        return labelMapper.selectAllLabel();
    }

    @Override
    public Label findLabelById(LabelVO labelVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put(Constants.SERCH_DATA_ID,labelVO.getId());
        return labelMapper.findLabelById(requestParams);
    }

    @Override
    public List<Label> findSearch(LabelVO labelVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        if (!StringUtils.isEmpty(labelVO.getLabelName())){
            requestParams.put(Constants.SEARCH_LABEL_NAME,labelVO.getLabelName());
        }
        if (!StringUtils.isEmpty(labelVO.getState())){
            requestParams.put(Constants.SEARCH_STATE,labelVO.getState());
        }
        if (!StringUtils.isEmpty(labelVO.getRecommend())){
            requestParams.put(Constants.SEARCH_RECOMMEND,labelVO.getRecommend());
        }
        return labelMapper.findSearch(requestParams);
    }

    @Override
    public void addLabel(Label label) {
        if (!StringUtils.isEmpty(label.getId())){
            labelMapper.addLabel(label);
        }
    }

    @Override
    public void updateLabelById(Label label) {
        String id = label.getId();
        if (!StringUtils.isEmpty(id)){
            labelMapper.updateLabelById(label);
        }
    }

    @Override
    public void deleteLabelById(Label label) {
        String id = label.getId();
        if (!StringUtils.isEmpty(id)){
            labelMapper.deleteLabelById(id);
        }
    }
}
