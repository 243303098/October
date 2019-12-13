package com.example.service.impl;

import com.example.mapper.ExcuteLogMapper;
import com.example.model.ExcuteLog;
import com.example.service.ExcuteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 14:22
 * @Description:
 */
@Service
public class ExcuteLogServiceImpl extends BaseService<ExcuteLog> implements ExcuteLogService {

    @Autowired
    private ExcuteLogMapper excuteLogMapper;

    @Override
    public List<ExcuteLog> selectBy(ExcuteLog excuteLog) {
        return excuteLogMapper.selectBy(excuteLog);
    }
}
