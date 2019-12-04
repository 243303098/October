package com.example.service;

import com.example.model.CaseLogDetail;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/27 21:38
 * @Description:
 */
public interface CaseLogDetailService extends IService<CaseLogDetail>{

    int saveByList(List<CaseLogDetail> caseLogDetailList);

    List<CaseLogDetail> selectByCaseId(Integer caseid);

}
