package com.example.service.impl;

import com.example.mapper.ExcuteLogDetailsMapper;
import com.example.model.ExcuteLogDetails;
import com.example.service.ExcuteLogDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 16:42
 * @Description:
 */
@Service
public class ExcuteLogDetailsServiceImpl extends BaseService<ExcuteLogDetails> implements ExcuteLogDetailsService {

    @Autowired
    private ExcuteLogDetailsMapper excuteLogDetailsMapper;

    @Override
    public ExcuteLogDetails getLastExcuteLog() {
        return excuteLogDetailsMapper.getLastExcuteLog();
    }

    @Override
    public List<ExcuteLogDetails> selectBy(ExcuteLogDetails excuteLogDetails) {
        return excuteLogDetailsMapper.selectBy(excuteLogDetails);
    }
}
