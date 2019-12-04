package com.example.mapper;

import com.example.model.CaseLogDetail;
import com.example.util.MyMapper;

import java.util.List;

public interface CaseLogDetailMapper extends MyMapper<CaseLogDetail> {

    int insertForeach(List<CaseLogDetail> caseLogDetailList);

    List<CaseLogDetail> selectByCaseId(Integer caseLogId);

}