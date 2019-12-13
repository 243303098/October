package com.example.service;

import com.example.model.ExcuteStepDetails;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 16:44
 * @Description:
 */
public interface ExcuteStepDetailsService extends IService<ExcuteStepDetails>{

    int saveByList(List<ExcuteStepDetails> excuteStepDetailsList);

    List<ExcuteStepDetails> selectBy(ExcuteStepDetails excuteStepDetails);
}
