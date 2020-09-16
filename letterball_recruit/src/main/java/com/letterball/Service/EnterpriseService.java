package com.letterball.Service;

import com.letterball.entity.Enterprise;
import com.letterball.vo.EnterpriseVO;

import java.util.List;

public interface EnterpriseService {

    List<Enterprise> findAllEnterprise(EnterpriseVO vo);

}
