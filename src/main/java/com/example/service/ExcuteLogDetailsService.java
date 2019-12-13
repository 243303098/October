package com.example.service;

import com.example.model.ExcuteLogDetails;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 16:40
 * @Description:
 */
public interface ExcuteLogDetailsService extends IService<ExcuteLogDetails>{

    ExcuteLogDetails getLastExcuteLog();

    List<ExcuteLogDetails> selectBy(ExcuteLogDetails excuteLogDetails);

}
