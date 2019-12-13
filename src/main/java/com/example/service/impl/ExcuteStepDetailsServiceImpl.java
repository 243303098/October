package com.example.service.impl;

import com.example.mapper.ExcuteStepDetailsMapper;
import com.example.model.ExcuteStepDetails;
import com.example.service.ExcuteStepDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 16:45
 * @Description:
 */
@Service
public class ExcuteStepDetailsServiceImpl extends BaseService<ExcuteStepDetails> implements ExcuteStepDetailsService {

    @Autowired
    private ExcuteStepDetailsMapper excuteStepDetailsMapper;

    @Override
    public int saveByList(List<ExcuteStepDetails> excuteStepDetailsList) {
        return excuteStepDetailsMapper.insertForeach(excuteStepDetailsList);
    }

    @Override
    public List<ExcuteStepDetails> selectBy(ExcuteStepDetails excuteStepDetails) {
        return excuteStepDetailsMapper.selectBy(excuteStepDetails);
    }
}
