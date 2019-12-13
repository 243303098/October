package com.example.service.impl;

import com.example.mapper.UIStepMapper;
import com.example.model.UIStep;
import com.example.service.UIStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/9 11:44
 * @Description:
 */
@Service
public class UIStepServiceImpl extends BaseService<UIStep> implements UIStepService {

    @Autowired
    private UIStepMapper uiStepMapper;

    @Override
    public List<UIStep> getUIStepBy(UIStep uiStep) {
        return uiStepMapper.selectBy(uiStep);
    }

    @Override
    public int saveByList(List<UIStep> uiStepList) {
        return uiStepMapper.insertForeach(uiStepList);
    }

    @Override
    public int updateByList(List<UIStep> uiStepList) {
        return uiStepMapper.updateForeach(uiStepList);
    }

    @Override
    public int deleteBymoduleId(Integer moduleId) {
        return uiStepMapper.deleteBymoduleId(moduleId);
    }
}
