package com.example.service.impl;

import com.example.mapper.QuartzTaskMapper;
import com.example.model.QuartzTask;
import com.example.service.QuartzTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/2 17:56
 * @Description:
 */
@Service
public class QuartzTaskServiceImpl extends BaseService<QuartzTask> implements QuartzTaskService {

    @Autowired
    private QuartzTaskMapper quartzTaskMapper;

    @Override
    public List<QuartzTask> getQuartzTask(QuartzTask quartzTask) {
        return quartzTaskMapper.selectBy(quartzTask);
    }
}
