package com.example.service.impl;

import com.example.mapper.CaseLogMapper;
import com.example.model.CaseLog;
import com.example.service.CaseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/27 21:35
 * @Description:
 */
@Service
public class CaseLogServiceImpl extends BaseService<CaseLog> implements CaseLogService {

    @Autowired
    private CaseLogMapper caseLogMapper;

    @Override
    public CaseLog getLastLog(Integer caseId) {
        return caseLogMapper.getLastCaseLog(caseId);
    }

    @Override
    public List<CaseLog> selectByIDIn(List<Integer> caseIdList) {
        return caseLogMapper.selectByIdIn(caseIdList);
    }
}
