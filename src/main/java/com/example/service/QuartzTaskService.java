package com.example.service;

import com.example.model.QuartzTask;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/2 17:54
 * @Description:
 */
public interface QuartzTaskService extends IService<QuartzTask>{

    List<QuartzTask> getQuartzTask(QuartzTask quartzTask);

}
