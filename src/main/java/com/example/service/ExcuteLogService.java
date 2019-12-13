package com.example.service;

import com.example.model.ExcuteLog;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 14:21
 * @Description:
 */
public interface ExcuteLogService extends IService<ExcuteLog>{

    List<ExcuteLog> selectBy(ExcuteLog excuteLog);
}
