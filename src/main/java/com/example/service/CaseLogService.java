package com.example.service;

import com.example.model.CaseLog;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/27 21:33
 * @Description:
 */
public interface CaseLogService extends IService<CaseLog>{

    CaseLog getLastLog(Integer caseId);

    List<CaseLog> selectByIDIn(List<Integer> caseIdList);
}
