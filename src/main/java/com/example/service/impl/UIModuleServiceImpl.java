package com.example.service.impl;

import com.example.mapper.UIModuleMapper;
import com.example.model.UIModule;
import com.example.service.UIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/30 17:44
 * @Description:
 */

@Service
public class UIModuleServiceImpl extends BaseService<UIModule> implements UIModuleService {

    @Autowired
    private UIModuleMapper uiModuleMapper;

    @Override
    public List<UIModule> getUIModuleBy(UIModule uiModule) {
        return uiModuleMapper.selectBy(uiModule);
    }

    @Override
    public List<UIModule> getUIModuleByIdIn(List<Integer> idList) {
        return uiModuleMapper.selectByIdIn(idList);
    }
}
