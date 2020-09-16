package com.letterball.Service;

import com.letterball.entity.Label;
import com.letterball.vo.LabelVO;

import java.util.List;

public interface LabelService {

    /**
     * 查询所有标签
     */
    List<Label> selectAllLabel();

    /**
     * 根据Id查询标签
     */
    Label findLabelById(LabelVO labelVO);

    /**
     * 条件查询
     */
    List<Label> findSearch(LabelVO labelVO);

    /**
     * 新增标签
     */
    void addLabel(Label label);

    /**
     * 根据Id修改标签
     */
    void updateLabelById(Label label);

    /**
     * 删除标签
     * @param label
     */
    void deleteLabelById(Label label);
}
