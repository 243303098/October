package com.example.service.impl;

import com.example.mapper.UIElementMapper;
import com.example.model.UIElement;
import com.example.service.UIElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/28 11:35
 * @Description:
 */

@Service
public class UIElementServiceImpl extends BaseService<UIElement> implements UIElementService {

    @Autowired
    private UIElementMapper uiElementMapper;


    @Override
    public List<UIElement> getUIElementBy(UIElement uiElement) {
        return uiElementMapper.getUIElementBy(uiElement);
    }

    @Override
    public List<UIElement> getAllUIElement() {
        return uiElementMapper.selectAll();
    }
}
