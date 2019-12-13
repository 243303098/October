package com.example.mapper;

import com.example.model.ExcuteStepDetails;
import com.example.util.MyMapper;

import java.util.List;

public interface ExcuteStepDetailsMapper extends MyMapper<ExcuteStepDetails> {

    int insertForeach(List<ExcuteStepDetails> excuteStepDetailsList);

    List<ExcuteStepDetails> selectBy(ExcuteStepDetails excuteStepDetails);

}