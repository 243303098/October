package com.example.mapper;

import com.example.model.ExcuteLog;
import com.example.util.MyMapper;

import java.util.List;

public interface ExcuteLogMapper extends MyMapper<ExcuteLog> {

    List<ExcuteLog> selectBy(ExcuteLog excuteLog);
}