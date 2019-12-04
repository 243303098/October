package com.example.mapper;

import com.example.model.QuartzTask;
import com.example.util.MyMapper;

import java.util.List;

public interface QuartzTaskMapper extends MyMapper<QuartzTask> {

    List<QuartzTask> selectBy(QuartzTask quartzTask);

}