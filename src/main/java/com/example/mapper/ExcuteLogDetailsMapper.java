package com.example.mapper;

import com.example.model.ExcuteLogDetails;
import com.example.util.MyMapper;

import java.util.List;

public interface ExcuteLogDetailsMapper extends MyMapper<ExcuteLogDetails> {

    ExcuteLogDetails getLastExcuteLog();

    List<ExcuteLogDetails> selectBy(ExcuteLogDetails excuteLogDetails);
}