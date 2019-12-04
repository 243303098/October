package com.example.service.impl;

import com.example.mapper.CaseLogDetailMapper;
import com.example.model.CaseLogDetail;
import com.example.service.CaseLogDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/27 21:39
 * @Description:
 */
@Service
public class CaseLogDetailServiceImpl extends BaseService<CaseLogDetail> implements CaseLogDetailService {

    @Autowired
    private CaseLogDetailMapper caseLogDetailMapper;

    @Override
    public int saveByList(List<CaseLogDetail> caseLogDetailList) {
        return caseLogDetailMapper.insertForeach(caseLogDetailList);
    }

    @Override
    public List<CaseLogDetail> selectByCaseId(Integer caseid) {
        return caseLogDetailMapper.selectByCaseId(caseid);
    }
}
