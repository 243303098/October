package com.example.service.impl;

import com.example.mapper.UICaseMapper;
import com.example.model.UICase;
import com.example.service.UICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/15 13:52
 * @Description:
 */
@Service
public class UICaseServiceImpl extends BaseService<UICase> implements UICaseService {

    @Autowired
    private UICaseMapper uiCaseMapper;

    @Override
    public List<UICase> getUICaseBy(UICase uiCase) {
        return uiCaseMapper.selectBy(uiCase);
    }

    @Override
    public List<UICase> getUICaseByIdIn(List<Integer> list) {
        return uiCaseMapper.selectByIdIn(list);
    }
}
