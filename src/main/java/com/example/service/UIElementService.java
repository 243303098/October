package com.example.service;

import com.example.model.UIElement;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/28 11:33
 * @Description:
 */
public interface UIElementService extends IService<UIElement>{

    List<UIElement> getUIElementBy(UIElement uiElement);

    List<UIElement> getAllUIElement();

}
