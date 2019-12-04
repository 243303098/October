package com.example.mapper;

import com.example.model.CaseLog;
import com.example.util.MyMapper;

import java.util.List;

public interface CaseLogMapper extends MyMapper<CaseLog> {

    CaseLog getLastCaseLog(Integer caseId);

    List<CaseLog> selectByIdIn(List<Integer> caseIdList);
}