package com.example.mapper;

import com.example.model.UIStep;
import com.example.util.MyMapper;

import java.util.List;

public interface UIStepMapper extends MyMapper<UIStep> {

    List<UIStep> selectBy(UIStep uiStep);

    int insertForeach(List<UIStep> list);

    int updateForeach(List<UIStep> list);

    int deleteBymoduleId(Integer moduleId);

}