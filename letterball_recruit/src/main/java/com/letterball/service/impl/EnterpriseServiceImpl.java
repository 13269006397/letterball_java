package com.letterball.service.impl;

import com.letterball.common.Constants;
import com.letterball.entity.Enterprise;
import com.letterball.mapper.EnterpriseMapper;
import com.letterball.service.EnterpriseService;
import com.letterball.vo.EnterpriseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public List<Enterprise> findAllEnterprise(EnterpriseVO vo) {
        HashMap<String, Object> requestParams = new HashMap<>();
        if (!StringUtils.isEmpty(vo.getIshot())){
            requestParams.put(Constants.SEARCH_IS_HOT,vo.getIshot());
        }
        return enterpriseMapper.findAllEnterprise(requestParams);
    }
}
