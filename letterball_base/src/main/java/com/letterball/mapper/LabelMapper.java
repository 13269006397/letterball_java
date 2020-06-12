package com.letterball.mapper;

import com.letterball.entity.Label;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface LabelMapper {

    List<Label> selectAllLabel();

    Label findLabelById(HashMap<String,Object> map);

    int addLabel(Label label);

    void updateLabelById(Label label);

    int deleteLabelById(String id);

    List<Label> findSearch(HashMap<String,Object> map);

}